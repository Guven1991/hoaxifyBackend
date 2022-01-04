package com.hoaxify.ws.user;


import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //herbir tablonun identity lociği oluyor
    private long id;

    @NotBlank(message = "{hoaxify.constraints.username.NotBlank.message}")   // hem @NotNull hem de @NotEmpty  alamaması için
    @Size(min = 4, max = 255)
    @UniqueUsername   //    @Column(unique = true) //database tarafında aynı username de iki şeye izin varmez
    private String username;

    @NotBlank
    @Size(min = 4, max = 255)
    private String displayName;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message ="{hoaxify.constraints.password.Pattern.message}")  //küçükharf,büyük harf, sayi olmalı
    private String password;

    private String image;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("Role_user");

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
