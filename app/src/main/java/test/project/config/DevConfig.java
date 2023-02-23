package test.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "test.project", exclude = HibernateJpaAutoConfiguration.class)
@PropertySource("classpath:app-dev.properties")
@EnableTransactionManagement
@Profile("dev")
public class DevConfig {

    private DataSource dataSource;

    @Autowired
    public DevConfig(DataSource dataSource) {
        System.out.println("DevConfig starting");
        this.dataSource = dataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        System.out.println("LocalSessionFactoryBean");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("test.project");

        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager getPlatformTransactionManager() {
        System.out.println("PlatformTransactionManager");
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean().getObject());
        return transactionManager;
    }
}
