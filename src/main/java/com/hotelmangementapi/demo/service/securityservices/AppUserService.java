package com.hotelmangementapi.demo.service.securityservices;

import com.hotelmangementapi.demo.repository.AppUserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRep appUserRep;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  appUserRep.findAppUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Username %s not found", username)));
    }
}
