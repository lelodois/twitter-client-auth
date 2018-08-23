package br.com.lelo.twclient.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableResourceServer
open class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources!!.resourceId("")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val oauthHashRead = "#oauth2.hasScope('read')"
        val oauthHashWrite = "#oauth2.hasScope('write')"

        http.requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .antMatchers(HttpMethod.GET, "/**").access(oauthHashRead)
                .antMatchers(HttpMethod.OPTIONS, "/**").access(oauthHashRead)
                .antMatchers(HttpMethod.POST, "/**").access(oauthHashWrite)
                .antMatchers(HttpMethod.PUT, "/**").access(oauthHashWrite)
                .antMatchers(HttpMethod.DELETE, "/**").access(oauthHashWrite)
    }

}