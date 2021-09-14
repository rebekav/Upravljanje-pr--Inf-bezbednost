package com.fax.lekari.repository;

import com.fax.lekari.model.Pregled;
import com.fax.lekari.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptRepository extends JpaRepository<Recept, Integer> {
}
