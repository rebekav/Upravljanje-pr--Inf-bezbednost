package com.fax.lekari.service.impl;

import com.fax.lekari.dto.KlinikaDtoReq;
import com.fax.lekari.dto.KlinikaDtoRes;
import com.fax.lekari.dto.SimpleSelectDTORes;
import com.fax.lekari.model.Klinika;
import com.fax.lekari.model.Role;
import com.fax.lekari.model.User;
import com.fax.lekari.repository.KlinikaRepository;
import com.fax.lekari.repository.UserRepository;
import com.fax.lekari.service.KlinikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlinikaServiceImpl implements KlinikaService {

    @Autowired
    KlinikaRepository klinikaRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<KlinikaDtoRes> findAll() {
        List<KlinikaDtoRes> response = new ArrayList<>();
        List<Klinika> klinike = klinikaRepository.findAll();
        for(Klinika klinika:klinike){
            KlinikaDtoRes klinikaDtoRes = new KlinikaDtoRes();
            klinikaDtoRes.setNaziv(klinika.getNaziv());
            klinikaDtoRes.setAdresa(klinika.getAdresa());
            klinikaDtoRes.setId(klinika.getId());
            klinikaDtoRes.setOpis(klinika.getOpis());
            response.add(klinikaDtoRes);
        }
        return response;
    }
    @Override
    public List<SimpleSelectDTORes> lekari(String name) throws Exception {
        User klinikaAdmin = userRepository.findByEmail(name);
        if (klinikaAdmin == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<SimpleSelectDTORes> response = new ArrayList<>();
        List<User> useriZaKliniku = userRepository.findAllByKlinika(klinikaAdmin.getKlinika());
        for(User u:useriZaKliniku){
            for(Role r:u.getRoles()){
                if(r.getNaziv().equals("LEKAR")){
                    SimpleSelectDTORes tmp = new SimpleSelectDTORes(u.getId(), u.getIme() + " " + u.getPrezime());
                    response.add(tmp);
                    break;
                }
            }
        }
        return response;
    }

    @Override
    public List<SimpleSelectDTORes> medicinskeSestre(String name) throws Exception {
        User klinikaAdmin = userRepository.findByEmail(name);
        if (klinikaAdmin == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<SimpleSelectDTORes> response = new ArrayList<>();
        List<User> useriZaKliniku = userRepository.findAllByKlinika(klinikaAdmin.getKlinika());
        for(User u:useriZaKliniku){
            for(Role r:u.getRoles()){
                if(r.getNaziv().equals("MEDICINSKA_SESTRA")){
                    SimpleSelectDTORes tmp = new SimpleSelectDTORes(u.getId(), u.getIme() + " " + u.getPrezime());
                    response.add(tmp);
                    break;
                }
            }
        }
        return response;
    }

    @Override
    public List<SimpleSelectDTORes> getSestre(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<User> zaposleni = user.getKlinika().getUsers();
        List<SimpleSelectDTORes> out = new ArrayList<>();
        for(User zaposlen: zaposleni ){
            if(zaposlen.getRoles().stream().anyMatch(role -> role.getNaziv().equals("MEDICINSKA_SESTRA"))){
                out.add(new SimpleSelectDTORes(zaposlen.getId(),zaposlen.getIme()+" "+zaposlen.getPrezime()));
            }
        }
        return out;
    }

    @Override
    public String createKlinika(KlinikaDtoReq klinikaDtoReq) {
        Klinika klinika = new Klinika();
        klinika.setAdresa(klinikaDtoReq.getAdresa());
        klinika.setNaziv(klinikaDtoReq.getNaziv());
        klinika.setOpis(klinikaDtoReq.getOpis());
        klinikaRepository.save(klinika);
        return "Success";
    }
}
