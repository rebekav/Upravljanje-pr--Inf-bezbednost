package com.fax.lekari.repository;

import com.fax.lekari.model.User;
import com.fax.lekari.model.ZdravstveniKarton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZdravstveniKartonRepository extends JpaRepository<ZdravstveniKarton, Integer> {
    List<ZdravstveniKarton> findAllByPregledPacijent(User user);
}
