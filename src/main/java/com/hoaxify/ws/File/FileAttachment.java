package com.hoaxify.ws.File;

import com.hoaxify.ws.hoax.Hoax;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class FileAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne
    private Hoax hoax;
}
