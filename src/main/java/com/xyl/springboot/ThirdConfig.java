package com.xyl.springboot;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.xyl.springboot.dao", sqlSessionFactoryRef = "thirdSqlSessionFactory")
public class ThirdConfig {
	
    static final String MAPPER_LOCATION = "classpath:mapper/**/*.xml";
	
	@Resource(name="thirdDataSource")
	private DataSource thirdDataSource;
	
	@Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager() {
        return new DataSourceTransactionManager(thirdDataSource);
    }
 
    @Bean(name = "thirdSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory()
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(thirdDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
	
}
