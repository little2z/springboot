package com.xyl.springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {
	
	@Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
	@Primary
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
	
	@Value("${spring.datasource.third.url}")
    private String url;
 
    @Value("${spring.datasource.third.username}")
    private String user;
 
    @Value("${spring.datasource.third.password}")
    private String password;
 
    @Value("${spring.datasource.third.driver-class-name}")
    private String driverClass;
	
	@Bean(name = "thirdDataSource")
	public DataSource thirdDataSource(){
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
	}
	
	
	

	@Bean(name = "primaryJdbcTemplate")
	public JdbcTemplate primaryJdbcTemplate(
	        @Qualifier("primaryDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	
	@Bean(name = "secondaryJdbcTemplate")
	public JdbcTemplate secondaryJdbcTemplate(
	        @Qualifier("secondaryDataSource") DataSource dataSource) {
	    return new JdbcTemplate(dataSource);
	}
	
	
}
