package com.nals.config.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * DatabaseConfig
 *
 * @author vietnt
 * @since 2022/02/27
 * @version 1.0
 * @see
 * 
 * <pre>
 * == Config Database ==
 * 
 * date                modifier                     status
 * -----                -----            ------
 *  2022/02/27 11:57:00  vietnt           CREATE
 * 
 * </pre>
 */
@Configuration
@MapperScan(
        basePackages = "com.nals.mybatis.mapper",
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class DatabaseConfig {

    private org.apache.ibatis.session.Configuration configuration;

    @Value("${spring.datasource.host}")
    protected String host;

    @Value("${spring.datasource.port}")
    protected String port;

    @Value("${spring.datasource.database}")
    protected String database;

    @Value("${spring.datasource.username}")
    protected String userName;

    @Value("${spring.datasource.password}")
    protected String password;

    /**
     *@ description MySQL dataSource
     * @updateTime  2022/02/27
     */
    @Bean
    public DataSource dataSource() {
        final String URL = "jdbc:mysql://%s:%s/%s";
        String formattedUrl = String.format(URL, host, port, database);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(formattedUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource; 

    }

    /**
     *@ description MySQL sqlSessionFactory configuration
     * @updateTime  2022/02/27
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        // mybatis config
        configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setCallSettersOnNulls(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setDataSource(dataSource());
        final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/*Mapper.xml"));
        return sessionFactory;
    }

    /**
     *@ description MySQL sqlSessionTemplate configuration
     * @updateTime  2022/02/27
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     *@ description MySQL transaction configuration
     * @updateTime  2022/02/27
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
