package com.project.example.job;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.project.example.entity.Sale_sum;
import com.project.example.entity.Stock_sum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SalesJobConfiguration {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;
//	private final EntityManagerFactory entityManagerFactory;
	
	private int chunkSize=1;
	
	@Bean(name="saleBatchJob")
	public Job SaleJob() throws Exception{
		return jobBuilderFactory
				.get("saleBatchJob")
				.start(SalesStep())
				.build();
	}
	
	@Bean
	public Step SalesStep() throws Exception{
		return stepBuilderFactory
				.get("SalesStep")
				.<Sale_sum,Sale_sum>chunk(chunkSize)
				.reader(salesItemReader())
				.writer(salesItemWriter())
				.build();
	}
	
	@Bean
	public JdbcCursorItemReader<Sale_sum> salesItemReader() throws Exception{
		
//		Map<String, Object> param = new HashMap<>();
//		param.put("sale_day", "2020-11-02");
//		System.out.print(param);
//		
//		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
//		queryProvider.setDataSource(dataSource);
//		queryProvider.setSelectClause("sale_deal_date as sale_date, sum(sale_deal_price) as sale_sum");
//		queryProvider.setFromClause("from sale");
//		queryProvider.setWhereClause("where sale_date=:sale_day");
//		queryProvider.setGroupClause("group by sale_date");
//		queryProvider.setSortKey("sale_date");
//		
//		return new JdbcPagingItemReaderBuilder<Sale_sum>()
//				.name("salesItemReader")
//				.pageSize(chunkSize)
//				.fetchSize(chunkSize)
//				.dataSource(dataSource)
//				.rowMapper(new BeanPropertyRowMapper<>(Sale_sum.class))
//				.queryProvider(queryProvider.getObject())
//				.parameterValues(param)
//				.build();
		
		return new JdbcCursorItemReaderBuilder<Sale_sum>()
				.fetchSize(chunkSize)
				.dataSource(dataSource)
				.rowMapper(new BeanPropertyRowMapper<>(Sale_sum.class))
				.sql("select sale_deal_date as sale_date, sum(sale_deal_price) as sale_sum from sale group by sale_date")
				.name("salesItemReader")
				.build();
	
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
	public JdbcBatchItemWriter<Sale_sum> salesItemWriter(){
		return new JdbcBatchItemWriterBuilder<Sale_sum>()
				.dataSource(dataSource)
				.sql("insert into Sale_sum (sale_sum, sale_date) values (:sale_sum, :sale_date")
				.beanMapped()
				.build();
	}
	
}
