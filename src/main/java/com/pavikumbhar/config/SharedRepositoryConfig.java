package com.pavikumbhar.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pavikumbhar.repository.SharedBaseEntityRepositoryImpl;

/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(  entityManagerFactoryRef = "sharedEntityManagerFactory", 
						transactionManagerRef = "sharedTransactionManager",
						basePackages = {"com.pavikumbhar.repository.shared"},
						repositoryBaseClass=SharedBaseEntityRepositoryImpl.class)
@PropertySource({ "classpath:persistence.properties" })
public class SharedRepositoryConfig {
   
    
    @Autowired
    private Environment environment;

    @Bean("sharedEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(sharedDataSource());
        em.setPackagesToScan(new String[]{"com.pravinkumbhar.shared.entity"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean("sharedTransactionManager")
    public PlatformTransactionManager  transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

  
    protected Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        return hibernateProperties;
    }

    @Bean
    public DataSource sharedDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("shared.database.driverClass"));
        dataSource.setUrl(environment.getRequiredProperty("shared.database.url"));
        dataSource.setUsername(environment.getRequiredProperty("shared.database.username"));
        dataSource.setPassword(environment.getRequiredProperty("shared.database.password"));
        return dataSource;
    }   
    
    
}