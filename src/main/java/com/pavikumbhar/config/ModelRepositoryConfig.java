package com.pavikumbhar.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pavikumbhar.repository.ModelBaseEntityRepositoryImpl;


/**
 * 
 * @author pavikumbhar
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories( entityManagerFactoryRef = "modelEntityManagerFactory", 
					    transactionManagerRef = "modelTransactionManager",
                        basePackages = {"com.pavikumbhar.repository.model"},
                        repositoryBaseClass = ModelBaseEntityRepositoryImpl.class)
@PropertySource({ "classpath:persistence.properties" })
public class ModelRepositoryConfig {
   
    
    @Autowired
    private Environment environment;
    
    
  

    @Primary
    @Bean("modelEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(modelDataSource());
        em.setPackagesToScan(new String[]{"com.pravinkumbhar.model.entity"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
       
        return em;
    }
   
    
    
/*    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }

    @Primary
    @Bean("modelEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(modelDataSource());
        em.setPackagesToScan(new String[]{"com.pravinkumbhar.model.entity"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        em.afterPropertiesSet();
        return em.getObject();
    }*/

    @Primary
    @Bean("modelTransactionManager")
    public PlatformTransactionManager  transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        //transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

  
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    protected Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        return hibernateProperties;
    }

    @Primary
    @Bean
    public DataSource modelDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("database.driverClass"));
        dataSource.setUrl(environment.getRequiredProperty("database.url"));
        dataSource.setUsername(environment.getRequiredProperty("database.username"));
        dataSource.setPassword(environment.getRequiredProperty("database.password"));
        return dataSource;
    }   
    
    
}