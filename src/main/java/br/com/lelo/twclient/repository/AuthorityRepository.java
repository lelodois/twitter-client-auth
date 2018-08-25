package br.com.lelo.twclient.repository;

import br.com.lelo.twclient.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    @Query("  SELECT u " +
            " FROM Authority u " +
            " WHERE " +
            " LOWER(u.name) = LOWER(:name)")
    Authority findByName(String name);

}