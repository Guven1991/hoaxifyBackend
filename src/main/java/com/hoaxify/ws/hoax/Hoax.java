package com.hoaxify.ws.hoax;

import com.hoaxify.ws.File.FileAttachment;
import com.hoaxify.ws.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Hoax {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // database deki ytabloda kendine özgü id ile devam eder
    private long id;


    @Column(length = 1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "hoax", cascade = CascadeType.REMOVE)// 2.yol olarak orphanRemoval= true bu hoax ile bağlantılı olana fileda birlikte silinmesi için kullanılır
    private FileAttachment fileAttachment;
}
