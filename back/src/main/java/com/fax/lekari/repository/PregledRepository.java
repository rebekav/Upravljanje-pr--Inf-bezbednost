package com.fax.lekari.repository;

import com.fax.lekari.model.Pregled;
import com.fax.lekari.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregledRepository extends JpaRepository<Pregled, Integer> {
    List<Pregled> findAllByPacijent(User user);
    List<Pregled> findAllByLekarAndPacijentIsNotNull(User user);
    List<Pregled> findAllByMedicinskaSestraAndPacijentIsNotNull(User user);
    List<Pregled> findAllByPacijentIsNull();

    List<Pregled> findAllByLekarAndPacijent(User lekar, User pacijent);
    List<Pregled> findAllByMedicinskaSestraAndPacijent(User lekar, User pacijent);
}
