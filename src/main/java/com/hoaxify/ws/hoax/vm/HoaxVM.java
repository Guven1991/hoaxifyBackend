package com.hoaxify.ws.hoax.vm;

import com.hoaxify.ws.hoax.Hoax;
import com.hoaxify.ws.user.User;
import com.hoaxify.ws.user.vm.UserVM;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class HoaxVM {

    private long id;

    private String content;

    private long timestamp;

    private UserVM user;

    public HoaxVM(Hoax hoax){
        this.setId(hoax.getId());
        this.setContent(hoax.getContent());
        this.setTimestamp(hoax.getTimestamp().getTime());
        this.setUser(new UserVM(hoax.getUser()));
    }
}
