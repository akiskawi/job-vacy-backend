package com.manpower.backendProject.repositories;

import com.manpower.backendProject.models.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = """
      select t from Token t inner join User u 
      on t.tokensUser.id = u.id 
      where u.id = :id and t.revoked = false
      """)
    List<Token> findAllValidTokenByUser(long id);

    Optional<Token> findByToken(String token);
}