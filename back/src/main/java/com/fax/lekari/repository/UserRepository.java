package com.fax.lekari.repository;

import com.fax.lekari.model.Klinika;
import com.fax.lekari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String username);
    User findByPasswordlessToken(String token);
    List<User> findAllByKlinika(Klinika klinika);
}
