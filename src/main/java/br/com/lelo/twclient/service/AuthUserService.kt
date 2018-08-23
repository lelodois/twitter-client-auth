package br.com.lelo.twclient.service

import br.com.lelo.twclient.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class AuthUserService : UserDetailsService {

    @Autowired
    private var userRepository: UserRepository? = null

    private var encoder: PasswordEncoder? = null

    override fun loadUserByUsername(username: String): UserDetails {
        val optUsername = userRepository!!.findByUsername(username)
        if (optUsername.isPresent) {
            val authUser = optUsername.get()
            val authorities = ArrayList<GrantedAuthority>()
            authUser.authorities!!.forEach { authority ->
                authorities.add(SimpleGrantedAuthority(authority.name))
            }
            return User(authUser.username, authUser.password, authorities)
        }
        
        throw UsernameNotFoundException("AuthUser $username Not found")
    }

    @Autowired
    fun setUserRepository(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        if (encoder == null) {
            encoder = BCryptPasswordEncoder()
        }
        return encoder as PasswordEncoder
    }

}