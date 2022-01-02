package com.hoaxify.ws.user.vm;

import com.hoaxify.ws.shared.ProfileImage;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateVM {

    @NotBlank
    @Size(min = 4, max = 255)
    private String displayName;

    @ProfileImage
    private String image;
}