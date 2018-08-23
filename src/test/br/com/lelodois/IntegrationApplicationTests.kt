package br.com.lelodois

import br.com.lelo.twclient.MainApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner::class)
@TestPropertySource(locations = ["classpath:application-test.properties"])
@ContextConfiguration(classes = [MainApplication::class])
open class IntegrationApplicationTests {

    @Test
    fun initConfig() {

    }
}
