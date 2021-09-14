package com.fax.lekari.service.impl;

import com.fax.lekari.dto.*;
import com.fax.lekari.model.Klinika;
import com.fax.lekari.model.Pregled;
import com.fax.lekari.model.Role;
import com.fax.lekari.model.User;
import com.fax.lekari.repository.KlinikaRepository;
import com.fax.lekari.repository.PregledRepository;
import com.fax.lekari.repository.RolesRepository;
import com.fax.lekari.repository.UserRepository;
import com.fax.lekari.security.SecurityConfiguration;
import com.fax.lekari.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KlinikaRepository klinikaRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    SecurityConfiguration configuration;

    @Autowired
    PregledRepository pregledRepository;

    @Override
    public String registerKlinikaAdmin(KorisnikDtoReq korisnikDto) throws Exception {
        User user = userRepository.findByEmail(korisnikDto.getEmail());
        if(user!=null){
            throw new Exception("Korisnik vec postoji");
        }
        Optional<Klinika> klinikaOptional = klinikaRepository.findById(korisnikDto.getIdKlinika());
        if(!klinikaOptional.isPresent()){
            throw new Exception("Klinika ne postoji");
        }
        user = new User();
        user.setAdresa(korisnikDto.getAdresa());
        user.setEmail(korisnikDto.getEmail());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setIdentifikator(korisnikDto.getIdentifikator());
        user.setValidiran((byte)1);
        user.setTelefon(korisnikDto.getTelefon());
        user.setPass(configuration.passwordEncoder().encode(korisnikDto.getPass()));
        user.setKlinika(klinikaOptional.get());
        user.setRoles(rolesRepository.findAllByNaziv("KLINIKA_ADMIN"));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        date = calendar.getTime();
        user.setExpire(date);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public String registerSuperAdmin(KorisnikDtoReq korisnikDto) throws Exception {
        User user = userRepository.findByEmail(korisnikDto.getEmail());
        if(user!=null){
            throw new Exception("Korisnik vec postoji");
        }
        user = new User();
        user.setAdresa(korisnikDto.getAdresa());
        user.setEmail(korisnikDto.getEmail());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setIdentifikator(korisnikDto.getIdentifikator());
        user.setValidiran((byte)1);
        user.setTelefon(korisnikDto.getTelefon());
        user.setPass(configuration.passwordEncoder().encode(korisnikDto.getPass()));
        user.setRoles(rolesRepository.findAllByNaziv("SUPER_ADMIN"));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        date = calendar.getTime();
        user.setExpire(date);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String registerSestra(KorisnikDtoReq korisnikDto, String name) throws Exception {
        User user = userRepository.findByEmail(korisnikDto.getEmail());
        if(user!=null){
            throw new Exception("Korisnik vec postoji");
        }
        User admin = userRepository.findByEmail(name);
        if(admin==null){
            throw new Exception("Korisnik ne postoji");
        }
        user = new User();
        user.setAdresa(korisnikDto.getAdresa());
        user.setEmail(korisnikDto.getEmail());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setIdentifikator(korisnikDto.getIdentifikator());
        user.setValidiran((byte)1);
        user.setKlinika(admin.getKlinika());
        user.setTelefon(korisnikDto.getTelefon());
        user.setPass(configuration.passwordEncoder().encode(korisnikDto.getPass()));
        user.setRoles(rolesRepository.findAllByNaziv("MEDICINSKA_SESTRA"));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        date = calendar.getTime();
        user.setExpire(date);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String registerLekar(KorisnikDtoReq korisnikDto, String name) throws Exception {
        User user = userRepository.findByEmail(korisnikDto.getEmail());
        if(user!=null){
            throw new Exception("Korisnik vec postoji");
        }
        User admin = userRepository.findByEmail(name);
        if(admin==null){
            throw new Exception("Korisnik ne postoji");
        }
        user = new User();
        user.setAdresa(korisnikDto.getAdresa());
        user.setEmail(korisnikDto.getEmail());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setIdentifikator(korisnikDto.getIdentifikator());
        user.setValidiran((byte)1);
        user.setKlinika(admin.getKlinika());
        user.setTelefon(korisnikDto.getTelefon());
        user.setPass(configuration.passwordEncoder().encode(korisnikDto.getPass()));
        user.setRoles(rolesRepository.findAllByNaziv("LEKAR"));
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        date = calendar.getTime();
        user.setExpire(date);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String registerPacijent(KorisnikDtoReq korisnikDto) throws Exception {
        User user = userRepository.findByEmail(korisnikDto.getEmail());
        if(user!=null){
            throw new Exception("Korisnik vec postoji");
        }
        user = new User();
        user.setAdresa(korisnikDto.getAdresa());
        user.setEmail(korisnikDto.getEmail());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setIdentifikator(korisnikDto.getIdentifikator());
        user.setValidiran((byte)0);
        user.setTelefon(korisnikDto.getTelefon());
        user.setPass(configuration.passwordEncoder().encode(korisnikDto.getPass()));
        user.setRoles(rolesRepository.findAllByNaziv("PACIJENT"));
        userRepository.save(user);
        return "Success";
    }

    @Override
    public List<SimpleSelectDTORes> users() {
        List<SimpleSelectDTORes> response = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User u:users){
            SimpleSelectDTORes dto = new SimpleSelectDTORes(u.getId(), u.getIme() + "-" + u.getPrezime());
            response.add(dto);
        }
        return response;
    }

    @Override
    public List<SimpleSelectDTORes> roles() {
        List<SimpleSelectDTORes> response = new ArrayList<>();
        List<Role> roles = rolesRepository.findAll();
        for(Role r:roles){
            SimpleSelectDTORes dto = new SimpleSelectDTORes(r.getId(), r.getNaziv());
            response.add(dto);
        }
        return response;
    }

    @Override
    public String addRole(AddRoleDtoReq addRoleDtoReq) throws Exception {
        Optional<User> userOptional = userRepository.findById(addRoleDtoReq.getUserId());
        if(!userOptional.isPresent()){
            throw new Exception("Korisnik ne postoji");
        }
        Optional<Role> roleOptional = rolesRepository.findById(addRoleDtoReq.getRoleId());
        if(!roleOptional.isPresent()){
            throw new Exception("Rola ne postoji");
        }
        User user = userOptional.get();
        Role role = roleOptional.get();

        boolean postojiVecRola = false;
        for(Role r:user.getRoles()){
            if(r.getId()==addRoleDtoReq.getRoleId()){
                postojiVecRola = true;
            }
        }
        if(postojiVecRola){
           throw new Exception("Korisnik vec ima rolu");
        }
        List<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
        return "Uspesno";
    }

    @Override
    public List<KorisnikDtoRes> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<KorisnikDtoRes> response = new ArrayList<>();
        for(User user:users){
            KorisnikDtoRes tmp = fill(user);
            List<String> roles = new ArrayList<>();
            for(Role role:user.getRoles()){
                roles.add(role.getNaziv());
            }
            tmp.setRoles(roles);
            response.add(tmp);
        }
        return response;
    }

    @Override
    public String ban(int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new Exception("Korisnik ne postoji");
        }
        User user = userOptional.get();
        user.setValidiran((byte) 0);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String unban(int id) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent()) {
            throw new Exception("Korisnik ne postoji");
        }
        User user = userOptional.get();
        user.setValidiran((byte) 1);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String editProfile(KorisnikEditDtoReq korisnikDto, String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if(user==null){
            throw new Exception("Korisnik ne postoji");
        }
        user.setAdresa(korisnikDto.getAdresa());
        user.setIme(korisnikDto.getIme());
        user.setPrezime(korisnikDto.getPrezime());
        user.setTelefon(korisnikDto.getTelefon());
        userRepository.save(user);
        return "Success";
    }

    @Override
    public KorisnikDtoRes getMyProfile(String name) throws Exception {
        User user = userRepository.findByEmail(name);
        if(user==null){
            throw new Exception("Korisnik ne postoji");
        }
        KorisnikDtoRes tmp = fill(user);
        return tmp;
    }

    private KorisnikDtoRes fill(User user) {
        KorisnikDtoRes tmp = new KorisnikDtoRes();
        tmp.setId(user.getId());
        tmp.setEmail(user.getEmail());
        tmp.setIme(user.getIme());
        tmp.setIdentifikator(user.getIdentifikator());
        tmp.setPass(user.getPass());
        tmp.setPrezime(user.getPrezime());
        tmp.setTelefon(user.getTelefon());
        tmp.setValidiran(user.getValidiran());
        tmp.setAdresa(user.getAdresa());
        tmp.setRoles(user.getRoles().stream().map(role -> role.getNaziv()).collect(Collectors.toList()));
        if(user.getExpire()!=null){
            tmp.setChangePass(true);
        }else{
            tmp.setChangePass(false);
        }
        if(user.getKlinika()!=null) {
            KlinikaDtoRes klinikaDtoRes = new KlinikaDtoRes();
            klinikaDtoRes.setNaziv(user.getKlinika().getNaziv());
            klinikaDtoRes.setAdresa(user.getKlinika().getAdresa());
            klinikaDtoRes.setId(user.getKlinika().getId());
            klinikaDtoRes.setOpis(user.getKlinika().getOpis());
            tmp.setKlinikaDtoRes(klinikaDtoRes);
        }
        return tmp;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto, Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName());
        if(user==null){
            throw new Exception("Korisnik ne postoji");
        }
        if (!configuration.passwordEncoder().matches(changePasswordDto.getOldPassword(), user.getPass())) {
            throw new Exception("Pogresna stara lozinka!");
        }
        user.setPass(configuration.passwordEncoder().encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public Collection<KorisnikDtoRes> pacijentiKlinikeZaLekara(String name, String filter) throws Exception {
        User lekar = userRepository.findByEmail(name);
        if (lekar == null) {
            throw new Exception("Korisnik ne postoji");
        }
        HashMap<Integer, KorisnikDtoRes> response = new HashMap<>();
        List<Pregled> pregledi = pregledRepository.findAllByLekarAndPacijentIsNotNull(lekar);
        for(Pregled p:pregledi){
            User pacijent = p.getPacijent();
            if(filter!=null){
                filter = filter.toLowerCase();
                String ime = pacijent.getIme().toLowerCase();
                String prezime = pacijent.getPrezime().toLowerCase();
                String identifikator = pacijent.getIdentifikator().toLowerCase();
                if(!ime.contains(filter) && !prezime.contains(filter) && !identifikator.contains(filter)){
                    continue;
                }
            }
            KorisnikDtoRes tmp = new KorisnikDtoRes();
            tmp.setId(pacijent.getId());
            tmp.setEmail(pacijent.getEmail());
            tmp.setIme(pacijent.getIme());
            tmp.setIdentifikator(pacijent.getIdentifikator());
            tmp.setPass(pacijent.getPass());
            tmp.setPrezime(pacijent.getPrezime());
            tmp.setTelefon(pacijent.getTelefon());
            tmp.setValidiran(pacijent.getValidiran());
            tmp.setAdresa(pacijent.getAdresa());
            response.put(pacijent.getId(),tmp);
        }
        List<KorisnikDtoRes> sort = new ArrayList<>(response.values());
        Collections.sort(sort);
        return sort;
    }

    @Override
    public Collection<KorisnikDtoRes> pacijentiKlinikeZaSestru(String name, String f, String d) throws Exception {
        User lekar = userRepository.findByEmail(name);
        if (lekar == null) {
            throw new Exception("Korisnik ne postoji");
        }

        // Koristi se da ukloni duplikate
        HashMap<Integer, KorisnikDtoRes> response = new HashMap<>();
        List<Pregled> pregledi = pregledRepository.findAllByMedicinskaSestraAndPacijentIsNotNull(lekar);

        for(Pregled p:pregledi){
            User pacijent = p.getPacijent();
            KorisnikDtoRes tmp = new KorisnikDtoRes();
            tmp.setId(pacijent.getId());
            tmp.setEmail(pacijent.getEmail());
            tmp.setIme(pacijent.getIme());
            tmp.setIdentifikator(pacijent.getIdentifikator());
            tmp.setPass(pacijent.getPass());
            tmp.setPrezime(pacijent.getPrezime());
            tmp.setTelefon(pacijent.getTelefon());
            tmp.setValidiran(pacijent.getValidiran());
            tmp.setAdresa(pacijent.getAdresa());
            response.put(pacijent.getId(),tmp);
        }
        // Lista da bi se moglo sortirat
        List<KorisnikDtoRes> out = response.values().stream().collect(Collectors.toList());
        if(!f.isEmpty()) {
            switch (f){
                case "ime":
                    out.sort((Comparator.comparing(o -> o.getIme())));
                    break;
                case "id":
                    out.sort((Comparator.comparingInt(o -> o.getId())));
                    break;
            }
            if(d.equals("desc")){
                Collections.reverse(out);
            }
        }
        return out;
    }
}
