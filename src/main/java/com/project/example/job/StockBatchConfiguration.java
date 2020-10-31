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
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.HibernateCursorItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.project.example.domain.Stock;
import com.project.example.entity.Stock_sum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@org.springframework.beans.factory.annotation.Value("${chunkSize:2}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;

	}

	@Bean(name = "stockBatchJob")
	public Job StockJob() throws Exception {
		return jobBuilderFactory
				.get("stockBatchJob")
				.start(StockStep())
				.build();
	}

	@Bean
	public Step StockStep() throws Exception {
		return stepBuilderFactory.get("StockStep")
				.<Stock_sum, Stock_sum>chunk(chunkSize)
				.reader(stockItemReader(null))
				.writer(stockItemWriter()).build();
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Stock_sum> stockItemReader(
			@org.springframework.beans.factory.annotation.Value("#{jobParameters[stock_user_id]}") String stock_user_id)
			throws Exception {

		Map<String, Object> params = new HashMap<>();
		params.put("stock_user_id", stock_user_id);

		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("stock_regi_date as stock_day, sum(stock_quantity) as stock_day_sum");
		queryProvider.setFromClause("from stock");
		queryProvider.setWhereClause("where stock_user_id =:stock_user_id");
		queryProvider.setGroupClause("group by stock_day");
		queryProvider.setSortKey("stock_day");

		System.out.print(stock_user_id);
		
		return new JdbcPagingItemReaderBuilder<Stock_sum>().name("stockItemReader")
				.pageSize(chunkSize).fetchSize(chunkSize)
				.dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(Stock_sum.class))
				.queryProvider(queryProvider.getObject())
				.parameterValues(params)
				.build();
		
	}
	
	
//	@Bean
//	@StepScope
//	public JdbcCursorItemReader<Stock> stockItemReader(){
//
//		return new JdbcCursorItemReaderBuilder<Stock>()
//				.fetchSize(chunkSize)
//				.dataSource(dataSource)
//				.rowMapper(new BeanPropertyRowMapper<>(Stock.class))
//				.sql("SELECT tock_regi_date, sum(stock_quantity) as stock_day_sum"
//						+ " from stock group by stock_regi_date")
//				.name("stockItmeWriter")
//				.build();
//	}

	//public HibernateCursorItemReader<Stock> sto
	
	@Bean
	public JdbcBatchItemWriter<Stock_sum> stockItemWriter() {
		return new JdbcBatchItemWriterBuilder<Stock_sum>()
				.dataSource(dataSource)
				//.sql("insert into Stock_sum(stock_user_id,stock_day_sum, stock_day) values(:stock_user_id, :stock_day_sum, :stock_regi_date)")
				.sql("insert into Stock_sum(stock_user_id, stock_day_sum, stock_day) values(:stock_user_id, :stock_day_sum, :stock_day)")				
				.assertUpdates(false)
				.beanMapped()
				.build();
	}
	
//	@Bean
//	public ItemWriter<Stock> stockItmeWriter(){
//		return list ->{
//			for(Stock stock:list) {
//				log.info("Stock data:",stock);
//				System.out.print("Stock data:"+stock);
//			}
//		};
//	}

}
