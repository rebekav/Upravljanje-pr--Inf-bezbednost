package com.fax.lekari.service.impl;

import com.fax.lekari.dto.DijagnozaDTO;
import com.fax.lekari.dto.ZdravstveniKartonDtoReq;
import com.fax.lekari.dto.ZdravstveniKartonDtoRes;
import com.fax.lekari.model.Pregled;
import com.fax.lekari.model.User;
import com.fax.lekari.model.ZdravstveniKarton;
import com.fax.lekari.repository.PregledRepository;
import com.fax.lekari.repository.UserRepository;
import com.fax.lekari.repository.ZdravstveniKartonRepository;
import com.fax.lekari.service.EncriptionService;
import com.fax.lekari.service.ZdravstveniKartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.xml.bind.JAXB;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Service
public class ZdravstveniKartonServiceImpl implements ZdravstveniKartonService {

    @Autowired
    ZdravstveniKartonRepository zdravstveniKartonRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PregledRepository pregledRepository;
    @Autowired
    EncriptionService encriptionService;
    @Override
    public List<ZdravstveniKartonDtoRes> mojKarton(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        return getZdravstveniKartonDtoRes(user);
    }

    private List<ZdravstveniKartonDtoRes> getZdravstveniKartonDtoRes(User user) throws Exception {
        List<ZdravstveniKartonDtoRes> response = new ArrayList<>();
        List<ZdravstveniKarton> logovi = zdravstveniKartonRepository.findAllByPregledPacijent(user);
        for (ZdravstveniKarton zk : logovi) {
            ZdravstveniKartonDtoRes tmp = new ZdravstveniKartonDtoRes();
            tmp.setId(zk.getId());
            String dijagnoza = zk.getBeleska();
            if (dijagnoza != null && !dijagnoza.isEmpty()) {
                String desifrovanaDijagnoza = this.encriptionService.desifruj(dijagnoza);
                System.out.println(desifrovanaDijagnoza);
                tmp.setBolest(getTagValue(desifrovanaDijagnoza, "bolest"));
                tmp.setIzvestaj(getTagValue(desifrovanaDijagnoza, "izvestaj"));
            }
            response.add(tmp);
        }
        return response;
    }

    @Override
    public List<ZdravstveniKartonDtoRes> kartonPacijenta(int id, String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if (user == null) {
            throw new Exception("Korisnik ne postoji");
        }
        Optional<User> pacijentOpt = userRepository.findById(id);
        if (!pacijentOpt.isPresent()) {
            throw new Exception("Korisnik ne postoji");
        }
        User pacijent = pacijentOpt.get();
        List<Pregled> preglediZaSestruIPacijenta = pregledRepository.findAllByMedicinskaSestraAndPacijent(user, pacijent);
        List<Pregled> preglediZaLekaraIPacijenta = pregledRepository.findAllByLekarAndPacijent(user, pacijent);
        boolean lekar = true;
        if (preglediZaLekaraIPacijenta == null || preglediZaLekaraIPacijenta.isEmpty()) {
            lekar = false;
        }
        boolean sestra = true;
        if (preglediZaSestruIPacijenta == null || preglediZaSestruIPacijenta.isEmpty()) {
            sestra = false;
        }
        if(!sestra && !lekar){
            throw new Exception("Nemate pravo da pregledate karton");
        }
        return getZdravstveniKartonDtoRes(pacijent);
    }


    public static String getTagValue(String xml, String tagName) {
        return xml.split("<" + tagName + ">")[1].split("</" + tagName + ">")[0];
    }

    @Override
    public String dodajBelesku(ZdravstveniKartonDtoReq zdravstveniKartonDtoReq, String name) throws Exception {
        Optional<Pregled> pregledOptional = pregledRepository.findById(zdravstveniKartonDtoReq.getIdPregled());
        if (!pregledOptional.isPresent()) {
            throw new Exception("Pregled ne postoji");
        }
        Pregled pregled = pregledOptional.get();
        if (!pregled.getLekar().getEmail().equals(name)) {
            throw new Exception("Niste lekar ovom pacijentu");
        }
        if (zdravstveniKartonDtoReq.getBolest() == null) {
            throw new Exception("Bolest je obavezno polje");
        }

        if (zdravstveniKartonDtoReq.getIzvestaj() == null) {
            throw new Exception("Izvestaj je obavezno polje");
        }
        ZdravstveniKarton zdravstveniKarton = new ZdravstveniKarton();
        if (pregled.getZdravstveniKartons() != null) {
            zdravstveniKarton = pregled.getZdravstveniKartons();
        }

        zdravstveniKarton.setPregled(pregled);
        zdravstveniKarton.setVreme(new Date());

        DijagnozaDTO sifrovanaDijagnoza = new DijagnozaDTO(zdravstveniKartonDtoReq.getBolest(), zdravstveniKartonDtoReq.getIzvestaj());

        StringWriter sw = new StringWriter();
        JAXB.marshal(sifrovanaDijagnoza, sw);
        String xmlString = sw.toString();
        String xmlEncoded = this.encriptionService.sifruj(xmlString);
        zdravstveniKarton.setBeleska(xmlEncoded);

        zdravstveniKartonRepository.save(zdravstveniKarton);
        return "Success";
    }


}
