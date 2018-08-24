package br.com.lelo.twclient.config

import br.com.lelo.twclient.domain.Authorities
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.sql.DataSource


@Configuration
@EnableAuthorizationServer
open class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private val dataSource: DataSource? = null

    @Value("\${oauth.secret}")
    private val secret: String? = null

    @Autowired
    @Qualifier("authenticationManagerBean")
    private val authenticationManager: AuthenticationManager? = null

    @Bean
    open fun tokenStore(): JdbcTokenStore {
        return JdbcTokenStore(dataSource!!)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.jdbc(dataSource)
                .withClient("twclient")
                .authorizedGrantTypes("password,refresh_token")
                .authorities(*Authorities.names())
                .resourceIds("resources")
                .scopes("read,write")
                .secret(secret)
                .accessTokenValiditySeconds(3600)
    }

    @Bean
    open fun corsFilter(): FilterRegistrationBean {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)

        val bean = FilterRegistrationBean(CorsFilter(source))
        bean.order = 0
        return bean
    }


}