package com.spring5demo.demo.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring5demo.demo.domain.Todo;
import com.spring5demo.demo.repository.HibernateTodoRepository;
import com.spring5demo.demo.repository.TodoRepository;

@Profile("orm")
@PropertySource("classpath:/application.properties")
@Configuration
@EnableTransactionManagement
public class HibernateRepository implements RepositoryConfig {

	@Value("${hibernate.hbm2ddl.auto:}")
	private String auto;

	@Value("${hibernate.dialect:}")
	private String dialect;

	@Value("${hibernate.globally_quoted_identifiers:}")
	private String globally_quoted_identifiers;
	
	@Bean
	public TodoRepository todoRepository(SessionFactory sessionFactory) {
		return new HibernateTodoRepository(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setAnnotatedClasses(Todo.class);
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", this.auto);
		properties.setProperty("hibernate.dialect", this.dialect);
		properties.setProperty("hibernate.globally_quoted_identifiers", this.globally_quoted_identifiers);
		sessionFactory.setHibernateProperties(properties);

		return sessionFactory;
	}

	@Bean
	public BeanPostProcessor persistenceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
}
