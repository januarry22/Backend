package com.project.example.job;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.project.example.entity.SaleSum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SalesJobConfiguration {

	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;
//	private final EntityManagerFactory entityManagerFactory;
	
	private static final int chunkSize=5;
	
	@Bean(name="saleBatchJob")
	public Job saleBatchJob() throws Exception{
		return jobBuilderFactory
				.get("saleBatchJob")
				.start(salesStep())
				.build();
	}
	
	@Bean
	public Step salesStep() throws Exception{
		return stepBuilderFactory
				.get("salesStep")
				.<SaleSum,SaleSum>chunk(chunkSize)
				.reader(salesItemReader(null))
				.writer(salesItemWriter())
				.build();
	}
	
	@Bean
	@StepScope
	public JdbcPagingItemReader<SaleSum> salesItemReader(
			@Value("#{jobParameters[localDate]}")String localDate) throws Exception{
		
		Map<String, Object> param = new HashMap<>();
		param.put("localDate", LocalDate.parse(localDate, FORMATTER));
		
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("sale_deal_date, sum(sale_deal_price) as sale_amount");
		queryProvider.setFromClause("from sale");
		queryProvider.setWhereClause("where sale_deal_date =:localDate");
		queryProvider.setGroupClause("group by sale_deal_date");
		queryProvider.setSortKey("sale_deal_date");
		
		return new JdbcPagingItemReaderBuilder<SaleSum>()
				.name("salesItemReader")
				.pageSize(chunkSize)
				.fetchSize(chunkSize)
				.dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(SaleSum.class))
				.queryProvider(queryProvider.getObject())
				.parameterValues(param)
				.build();
		
//		return new JdbcCursorItemReaderBuilder<SaleSum>()
//				.fetchSize(chunkSize)
//				.dataSource(dataSource)
//				.rowMapper(new BeanPropertyRowMapper<>(SaleSum.class))
//				.sql("select sale_deal_date as sale_date, sum(sale_deal_price) as sale_amount "
//						+ "from sale group by sale_date")
//				.name("salesItemReader")
//				.build();
	
	}
	
//	private ItemWriter<Sale_sum> salesItemWriter(){
//		return list -> {
//			for(Sale_sum sum : list) {
//				log.info("Sale sum:", sum);
//				System.out.print(sum);
//			}
//		};
//	}
	
	
	@Bean 
	public JdbcBatchItemWriter<SaleSum> salesItemWriter(){
		return new JdbcBatchItemWriterBuilder<SaleSum>()
				.dataSource(dataSource)
				.sql("insert into Sale_sum (sale_amount, sale_date) values (:sale_amount, :localDate)")
				.beanMapped()
				.build();

	}
	
}
