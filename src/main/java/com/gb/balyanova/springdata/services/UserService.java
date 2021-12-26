package com.gb.balyanova.springdata.services;

import com.gb.balyanova.springdata.entities.Authority;
import com.gb.balyanova.springdata.entities.Role;
import com.gb.balyanova.springdata.entities.User;
import com.gb.balyanova.springdata.exceptions.UserIsAlreadyExistsException;
import com.gb.balyanova.springdata.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapPermissionsToAuthorities(user.getAuthorities()));
//    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        log.info("Role: " + roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    private Collection <? extends GrantedAuthority> mapPermissionsToAuthorities (Collection <Authority> authorities){
        log.info("Authority: " + authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList()));
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }
    public UserDetails saveUser(User user) throws UserIsAlreadyExistsException {
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());

        if(userFromDB.isPresent()) {
            throw new UserIsAlreadyExistsException(String.format("User '%s' is already exists", user.getUsername()));
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(Collections.singleton(new Role()));
            userRepository.save(user);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
}