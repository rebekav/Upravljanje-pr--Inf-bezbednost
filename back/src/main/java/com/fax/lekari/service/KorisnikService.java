package com.fax.lekari.service;

import com.fax.lekari.dto.*;
import com.fax.lekari.model.User;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface KorisnikService {
    String registerKlinikaAdmin(KorisnikDtoReq korisnikDto) throws Exception;

    User findByEmail(String username);

    List<KorisnikDtoRes> findAllUsers();

    String ban(int id) throws Exception;

    String unban(int id) throws Exception;

    String editProfile(KorisnikEditDtoReq korisnikDto, String name) throws Exception;

    KorisnikDtoRes getMyProfile(String name) throws Exception;

    void changePassword(ChangePasswordDto changePasswordDto, Principal principal) throws Exception;

    Collection<KorisnikDtoRes> pacijentiKlinikeZaLekara(String name, String filter) throws Exception;

    Collection<KorisnikDtoRes> pacijentiKlinikeZaSestru(String name, String f, String d) throws Exception;

    String registerSuperAdmin(KorisnikDtoReq korisnikDto) throws Exception;

    String registerPacijent(KorisnikDtoReq korisnikDto) throws Exception;

    List<SimpleSelectDTORes> users();

    List<SimpleSelectDTORes> roles();

    String addRole(AddRoleDtoReq addRoleDtoReq) throws Exception;

    String registerLekar(KorisnikDtoReq korisnikDto, String name) throws Exception;

    String registerSestra(KorisnikDtoReq korisnikDto, String name) throws Exception;
}
