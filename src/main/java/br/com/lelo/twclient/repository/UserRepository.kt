package br.com.lelo.twclient.repository

import br.com.lelo.twclient.domain.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import java.util.Optional

interface UserRepository : JpaRepository<AuthUser, String> {

    @Query("  SELECT u " +
            " FROM AuthUser u " +
            " WHERE " +
            " LOWER(u.username) = LOWER(:username)")
    fun findByUsername(@Param("username") username: String): Optional<AuthUser>

}