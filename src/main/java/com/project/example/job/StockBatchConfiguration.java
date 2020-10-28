package com.project.example.job;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.project.example.domain.Stock;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class StockBatchConfiguration {

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	private final DataSource dataSource; // DataSource DI

	@Autowired
	private JobRepository stockRepository;

	private int chunkSize;

	@Bean
	public JobLauncher stockLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(stockRepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@org.springframework.beans.factory.annotation.Value("${chunkSize:5}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;

	}

	@Bean(name = "stockBatchJob")
	public Job StockJob() throws Exception {
		return jobBuilderFactory.get("stockBatchJob").start(StockStep()).build();
	}

	@Bean
	public Step StockStep() throws Exception {
		return stepBuilderFactory.get("StockStep").<Stock, Stock>chunk(chunkSize).reader(stockItemReader(null))
				.writer(stockItmeWriter()).build();
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Stock> stockItemReader(
			@org.springframework.beans.factory.annotation.Value("#{jobParameters[stock_user_id]}") String stock_user_id)
			throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("stock_user_id", stock_user_id);

		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("stock_regi_date, sum(stock_quantity) as stock_day_sum");
		queryProvider.setFromClause("from stock");
		queryProvider.setWhereClause("where stock_user_id =:stock_user_id");
		queryProvider.setGroupClause("group by stock_regi_date");
		queryProvider.setSortKey("stock_regi_date");

		return new JdbcPagingItemReaderBuilder<Stock>().name("stockItemReader").pageSize(chunkSize).fetchSize(chunkSize)
				.dataSource(dataSource).rowMapper(new BeanPropertyRowMapper<>(Stock.class))
				.queryProvider(queryProvider.getObject()).parameterValues(params).build();
	}

	@Bean
	public JdbcBatchItemWriter<Stock> stockItmeWriter() {
		return new JdbcBatchItemWriterBuilder<Stock>().dataSource(dataSource)
				.sql("insert into Stock_sum(stock_user_id,stock_day_sum, stock_day) values(:stock_user_id, :stock_day_sum, :stock_regi_date)")
				.beanMapped().build();
	}

}
