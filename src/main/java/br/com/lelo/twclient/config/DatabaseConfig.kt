package br.com.lelo.twclient.config

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

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


}