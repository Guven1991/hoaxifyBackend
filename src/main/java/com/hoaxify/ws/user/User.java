package com.hoaxify.ws.user;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "{hoaxify.constraints.username.NotBlank.message}")   // hem @NotNull hem de @NotEmpty  alamaması için
    @Size(min = 4, max = 255)
    @UniqueUsername                                        //    @Column(unique = true) //database tarafında aynı username de iki şeye izin varmez
    private String username;

    @NotBlank
    @Size(min = 4, max = 255)
    private String displayName;

    @NotBlank
    @Size(min = 8, max = 255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message ="{hoaxify.constraints.password.Pattern.message}")  //küçükharf,büyük harf, sayi olmalı
    private String password;
}
