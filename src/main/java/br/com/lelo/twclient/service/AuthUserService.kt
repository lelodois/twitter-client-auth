package br.com.lelo.twclient.service

import br.com.lelo.twclient.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
open class AuthUserService : UserDetailsService {

    @Autowired
    private var userRepository: UserRepository? = null

    override fun loadUserByUsername(username: String): UserDetails {
        val optUsername = userRepository!!.findByUsername(username)
        optUsername.ifPresent { t ->
            val authorities = ArrayList<GrantedAuthority>()
            t.authorities!!.forEach { authority ->
                authorities.add(SimpleGrantedAuthority(authority.name))
            }
        }

        throw UsernameNotFoundException("AuthUser $username Not found")
    }

    @Autowired
    fun setUserRepository(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

}