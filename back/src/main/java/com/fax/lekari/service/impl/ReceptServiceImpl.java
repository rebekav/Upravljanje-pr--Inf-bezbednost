package com.fax.lekari.service.impl;

import com.fax.lekari.dto.KorisnikDtoRes;
import com.fax.lekari.dto.PregledDtoRes;
import com.fax.lekari.dto.ReceprResDTO;
import com.fax.lekari.model.Pregled;
import com.fax.lekari.model.Recept;
import com.fax.lekari.model.User;
import com.fax.lekari.model.ZdravstveniKarton;
import com.fax.lekari.repository.*;
import com.fax.lekari.security.SecurityConfiguration;
import com.fax.lekari.service.ReceptSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReceptServiceImpl implements ReceptSevice {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReceptRepository receptRepository;

    @Autowired
    PregledRepository pregledRepository;
    @Override
    public List<ReceprResDTO> sestraRecepti(String name) throws Exception{
        User lekar = userRepository.findByEmail(name);
        if (lekar == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<ReceprResDTO> response = new ArrayList<>();
        List<Pregled> pregledi = pregledRepository.findAllByMedicinskaSestraAndPacijentIsNotNull(lekar);
        for(Pregled p:pregledi){
            User pacijent = p.getPacijent();
            PregledDtoRes tmp = new PregledDtoRes();
            tmp.setId(p.getId());
            tmp.setPodaciOPregledu(p.getPodaciOPregledu());
            tmp.setPopust(p.getPopust());
            tmp.setTrajanje(p.getTrajanje());
            tmp.setVreme(p.getVreme().toString());
            tmp.setNazivUsluge(p.getUsluga().getNaziv());
            tmp.setCenaUsluge(p.getUsluga().getCena());
            tmp.setLekar(p.getLekar().getIme() + " " + p.getLekar().getPrezime());
            tmp.setNazivKlinike(p.getLekar().getKlinika().getNaziv());
            tmp.setAdresaKlinike(p.getLekar().getKlinika().getAdresa());
            tmp.setPacijent(p.getPacijent().getIme() + " " + p.getPacijent().getPrezime() + " " + p.getPacijent().getIdentifikator());

            for(Recept r: p.getRecepti()){
                ReceprResDTO tmp1 = new ReceprResDTO();
                tmp1.setId(r.getId());
                tmp1.setNaziv(r.getNaziv());
                tmp1.setOveren(r.isOveren());
                tmp1.setPregled(tmp);
                response.add(tmp1);
            }

        }
        return response;
    }

    @Override
    public String overa(int id, String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<Recept> recept = receptRepository.findById(id);
        if (!recept.isPresent()) {
            throw new Exception("Pregled ne postoji");
        }
        Recept r = recept.get();
        if(r.getPregled().getMedicinskaSestra().getId() != user.getId()){
            throw new Exception("Nemate permision");
        }
        r.setOveren(true);
        receptRepository.save(r);
        return "Recept e overen";
    }
}
