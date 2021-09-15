package com.fax.lekari.service.impl;

import com.fax.lekari.dto.*;
import com.fax.lekari.model.*;
import com.fax.lekari.repository.PregledRepository;
import com.fax.lekari.repository.ReceptRepository;
import com.fax.lekari.repository.UserRepository;
import com.fax.lekari.repository.UslugaRepository;
import com.fax.lekari.service.EmailService;
import com.fax.lekari.service.PregledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PregledServiceImpl implements PregledService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PregledRepository pregledRepository;

    @Autowired
    UslugaRepository uslugaRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    ReceptRepository receptRepository;

    @Override
    public List<PregledDtoRes> istorijaPregleda(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<PregledDtoRes> response = new ArrayList<>();
        List<Pregled> pregledi = pregledRepository.findAllByPacijent(user);
        for (Pregled p : pregledi) {
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
            response.add(tmp);
        }
        return response;
    }

    @Override
    public List<PregledDtoRes> dostupniTermini(String name) {
        List<PregledDtoRes> response = new ArrayList<>();
        List<Pregled> pregledi = pregledRepository.findAllByPacijentIsNull();
        for (Pregled p : pregledi) {
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
            response.add(tmp);
        }
        return response;
    }

    @Override
    public List<PregledDtoRes> radniKalendar(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<PregledDtoRes> response = new ArrayList<>();
        List<Pregled> pregledi = pregledRepository.findAllByLekarAndPacijentIsNotNull(user);
        for (Pregled p : pregledi) {
            if(p.getVreme().before(new Date())){
                continue;
            }
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
            response.add(tmp);
        }
        return response;
    }

    @Override
    public List<PregledDtoRes> radniKalendarZaSestru(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<PregledDtoRes> response = new ArrayList<>();
        List<Pregled> pregledi = pregledRepository.findAllByMedicinskaSestraAndPacijentIsNotNull(user);
        for (Pregled p : pregledi) {
            if(p.getVreme().before(new Date())){
                continue;
            }
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
            response.add(tmp);
        }
        return response;
    }

    @Override
    public PregledLightDTO getPregled(String name, int id) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<Pregled> pregledOptional = pregledRepository.findById(id);
        if (!pregledOptional.isPresent()) {
            throw new Exception("Termin ne postoji");
        }

        Pregled pregled = pregledOptional.get();
        if(pregled.getLekar().getId() != user.getId()){
            throw new Exception("Pacijent nije vas");
        }
        PregledLightDTO pl = new PregledLightDTO();
        pl.setId(pregled.getId());
        pl.setLekar(pregled.getLekar().getIme() + " "+pregled.getLekar().getPrezime());
        pl.setSestra(pregled.getMedicinskaSestra().getIme() + " "+pregled.getMedicinskaSestra().getPrezime());
        pl.setPacijent(pregled.getPacijent().getIme() + " "+pregled.getPacijent().getPrezime());
        pl.setPacijent_id(pregled.getPacijent().getId());
        pl.setUsluga(pregled.getUsluga().getNaziv());
        pl.setPodaciOPregledu(pregled.getPodaciOPregledu());
        pl.setRecepti(pregled.getRecepti().stream().map(recept -> new ReceptDTORes(recept)).collect(Collectors.toList()));
        return pl;
    }

    @Override
    public String addRecept(String name, int id, ReceptDTOReq receptDTOReq) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<Pregled> pregledOptional = pregledRepository.findById(id);
        if (!pregledOptional.isPresent()) {
            throw new Exception("Termin ne postoji");
        }

        Pregled pregled = pregledOptional.get();
        if(pregled.getLekar().getId() != user.getId()){
            throw new Exception("Pacijent nije vas");
        }
        Recept r = new Recept();
        r.setOveren(false);
        r.setNaziv(receptDTOReq.getNaziv());
        r.setNapomena(receptDTOReq.getNapomena());
        r.setPregled(pregled);
        receptRepository.save(r);
        return "Success";
    }

    @Override
    public String zakaziPregled(int id, String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }

        Optional<Pregled> pregledOptional = pregledRepository.findById(id);
        if (!pregledOptional.isPresent()) {
            throw new Exception("Termin ne postoji");
        }
        Pregled pregled = pregledOptional.get();
        if (pregled.getPacijent() != null) {
            throw new Exception("Termin je zauzet");
        }
        pregled.setPacijent(user);
        pregledRepository.save(pregled);
        emailService.zakazanPregledEmail(pregled.getLekar().getKlinika().getNaziv(), pregled.getUsluga().getNaziv(), pregled.getVreme().toString(), pregled.getLekar().getIme() + " " + pregled.getLekar().getPrezime(), user.getEmail());
        return "Success";
    }

    @Override
    public String napraviTermin(PregledDTOReq pregledDTOReq, String name) throws Exception {
        User klinikaAdmin = userRepository.findByEmail(name);
        if (klinikaAdmin == null) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<User> lekarOpt = userRepository.findById(pregledDTOReq.getLekarId());
        if(!lekarOpt.isPresent()){
            throw new Exception("Lekar ne postoji");
        }
        Optional<User> medicinskaSestraOpt = userRepository.findById(pregledDTOReq.getMedicinskaSestraId());
        if(!medicinskaSestraOpt.isPresent()){
            throw new Exception("Medicinska sestra ne postoji");
        }
        User lekar = lekarOpt.get();
        User medicinskaSestra = medicinskaSestraOpt.get();
        if(klinikaAdmin.getKlinika().getId() != lekar.getKlinika().getId()){
            throw new Exception("Lekar nije iz vase klinike");
        }
        if(klinikaAdmin.getKlinika().getId() != medicinskaSestra.getKlinika().getId()){
            throw new Exception("Medicinska sestra nije iz vase klinike");
        }
        Optional<Usluga> uslugaOpt = uslugaRepository.findById(pregledDTOReq.getUslugaId());
        if(!uslugaOpt.isPresent()){
            throw new Exception("Usluga ne postoji");
        }
        Usluga usluga = uslugaOpt.get();
        if(usluga.getKlinika().getId() != klinikaAdmin.getKlinika().getId()){
            throw new Exception("Usluga ne pripada vasoj klinici");
        }

        Pregled pregled = new Pregled();
        pregled.setLekar(lekar);
        pregled.setPodaciOPregledu(pregledDTOReq.getPodaciOPregledu());
        pregled.setUsluga(usluga);
        pregled.setPopust(pregledDTOReq.getPopust());
        pregled.setMedicinskaSestra(medicinskaSestra);
        pregled.setTrajanje(pregledDTOReq.getTrajanje());

        //String sDate6 = "31-12-1998 23:37:50";
        //"2021-08-12T13:12"
        String vremePregleda = pregledDTOReq.getVreme().replace("T", " ");
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date vreme=formatter6.parse(vremePregleda);
        pregled.setVreme(vreme);

        pregledRepository.save(pregled);
        return "Success";
    }

    @Override
    public int pregledZapocni(PregledDTOReq pregledDTOReq, String name, int id) throws Exception {
        Optional<User> pacijent = userRepository.findById(id);
        if (!pacijent.isPresent()) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<User> lekarOpt = userRepository.findById(pregledDTOReq.getLekarId());
        if(!lekarOpt.isPresent()){
            throw new Exception("Lekar ne postoji");
        }
        Optional<User> medicinskaSestraOpt = userRepository.findById(pregledDTOReq.getMedicinskaSestraId());
        if(!medicinskaSestraOpt.isPresent()){
            throw new Exception("Medicinska sestra ne postoji");
        }
        User lekar = lekarOpt.get();
        User medicinskaSestra = medicinskaSestraOpt.get();

        if(lekar.getKlinika().getId() != medicinskaSestra.getKlinika().getId()){
            throw new Exception("Medicinska sestra nije iz vase klinike");
        }
        Optional<Usluga> uslugaOpt = uslugaRepository.findById(pregledDTOReq.getUslugaId());
        if(!uslugaOpt.isPresent()){
            throw new Exception("Usluga ne postoji");
        }
        Usluga usluga = uslugaOpt.get();
        if(usluga.getKlinika().getId() != lekar.getKlinika().getId()){
            throw new Exception("Usluga ne pripada vasoj klinici");
        }

        Pregled pregled = new Pregled();
        pregled.setLekar(lekar);
        pregled.setPodaciOPregledu(pregledDTOReq.getPodaciOPregledu());
        pregled.setUsluga(usluga);
        pregled.setPopust(pregledDTOReq.getPopust());
        pregled.setMedicinskaSestra(medicinskaSestra);
        pregled.setTrajanje(pregledDTOReq.getTrajanje());
        pregled.setPacijent(pacijent.get());
        //String sDate6 = "31-12-1998 23:37:50";
        //"2021-08-12T13:12"
        String vremePregleda = pregledDTOReq.getVreme().replace("T", " ");
        SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date vreme=formatter6.parse(vremePregleda);
        pregled.setVreme(vreme);

        pregledRepository.save(pregled);
        return pregled.getId();
    }


    @Override
    public List<SimpleSelectDTORes> usluge(String name) throws Exception {
        User klinikaAdmin = userRepository.findByEmail(name);
        if (klinikaAdmin == null) {
            throw new Exception("Korisnik ne postoji");
        }
        List<SimpleSelectDTORes> response = new ArrayList<>();
        List<Usluga> uslugeZaKliniku = uslugaRepository.findAllByKlinika(klinikaAdmin.getKlinika());
        for(Usluga u:uslugeZaKliniku){
            SimpleSelectDTORes tmp = new SimpleSelectDTORes(u.getId(), u.getNaziv() + " " + u.getCena() + "din");
            response.add(tmp);
        }

        return response;
    }
}
