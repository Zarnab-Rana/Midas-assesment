package com.midas.app.repositories;

import com.midas.app.models.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query(nativeQuery = true, value = "select * from accounts where email = ?1")
  Optional<Account> findAccountByEmail(String email);
}
