package com.fax.lekari.repository;

import com.fax.lekari.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {
    List<Role> findAllByNaziv(String naziv);
}
