package com.fax.lekari.repository;

import com.fax.lekari.model.Klinika;
import com.fax.lekari.model.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Integer> {
    List<Usluga> findAllByKlinika(Klinika klinika);
}
