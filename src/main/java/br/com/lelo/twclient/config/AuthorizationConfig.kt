package br.com.lelo.twclient.config

import br.com.lelo.twclient.domain.Authorities
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
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
open class AuthorizationConfig : AuthorizationServerConfigurerAdapter() {

    @Value("\${oauth.secret}")
    private val secret: String? = null

    @Autowired
    private var dataSource: DataSource? = null

    @Autowired
    @Qualifier("authenticationManagerBean")
    private var authenticationManager: AuthenticationManager? = null

    private var encoder: PasswordEncoder? = null

    @Bean
    open fun tokenStore(): JdbcTokenStore {
        return JdbcTokenStore(dataSource!!)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(this.authenticationManager!!)
                .tokenStore(tokenStore())
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
                .withClient("twclient")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .authorities(*Authorities.names())
                .resourceIds("twclientresource")
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
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }


    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        if (encoder == null) {
            encoder = BCryptPasswordEncoder()
        }
        return encoder as PasswordEncoder
    }

}
