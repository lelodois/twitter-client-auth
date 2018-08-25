package br.com.lelo.twclient.config

import org.flywaydb.core.Flyway
import org.h2.server.web.WebServlet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource
import org.springframework.boot.web.servlet.ServletRegistrationBean


@Configuration
open class DatabaseConfig {

    @Autowired
    private val dataSource: DataSource? = null

    @Bean(name = ["flyway"], initMethod = "migrate")
    open fun flyway(): Flyway {
        val flyway = Flyway()
        flyway.dataSource = dataSource
        flyway.isBaselineOnMigrate = true
        flyway.setLocations("classpath:/sql/")
        flyway.clean()
        return flyway
    }

    @Bean
    open fun h2servletRegistration(): ServletRegistrationBean {
        val webServlet = WebServlet()
        val bean = ServletRegistrationBean(webServlet)
        bean.addUrlMappings("/h2console/*")
        return bean
    }


}