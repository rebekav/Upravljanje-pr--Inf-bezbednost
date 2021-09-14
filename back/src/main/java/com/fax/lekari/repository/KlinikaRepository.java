package com.fax.lekari.repository;

import com.fax.lekari.model.Klinika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlinikaRepository extends JpaRepository<Klinika, Integer> {
}
