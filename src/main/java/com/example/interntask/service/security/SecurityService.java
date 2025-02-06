package com.example.interntask.service.security;

import com.example.interntask.globalException.exceptions.NotFoundException;
import com.example.interntask.model.entity.UsersEntity;
import com.example.interntask.repo.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UsersEntity user = usersRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("username not found"));
        return new User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UsersEntity usersEntity) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }
}