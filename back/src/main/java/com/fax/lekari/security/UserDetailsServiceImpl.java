package com.fax.lekari.security;

import com.fax.lekari.model.Role;
import com.fax.lekari.model.User;
import com.fax.lekari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("Nije nadjen korisnik sa email-om '%s'.", email));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for(Role role:user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getNaziv()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPass(), grantedAuthorities);
    }
}
