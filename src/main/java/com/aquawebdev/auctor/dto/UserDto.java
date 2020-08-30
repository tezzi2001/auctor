package com.aquawebdev.auctor.dto;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.validation.annotations.Login;
import com.aquawebdev.auctor.validation.annotations.Password;
import com.aquawebdev.auctor.validation.annotations.UniqueEmail;
import com.aquawebdev.auctor.validation.annotations.UniqueLogin;
import lombok.Data;
import javax.imageio.ImageIO;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Data
public class UserDto {
    @Login
    @UniqueLogin
    private String login;

    @NotBlank(message = "field name can`t be blank")
    private String name;

    private String surname;

    @Email(message = "email is not valid")
    @UniqueEmail
    @NotBlank(message = "field email can`t be blank")
    private String email;

    @Password
    private String password;

    private byte[] photo;

    public User toUser() {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPhoto(photo);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhoto(user.getPhoto());
        return userDto;
    }

    public String getTempLinkOnPhoto() throws IOException {
        String fileName = login + "_photo.jpg";
        File photoAsFile = new File(fileName);

        if (photoAsFile.exists()) {
            return photoAsFile.getAbsolutePath();
        }

        ByteArrayInputStream photoStream = new ByteArrayInputStream(photo);
        BufferedImage bufferedImage = ImageIO.read(photoStream);
        ImageIO.write(bufferedImage, "jpg", photoAsFile);

        return photoAsFile.getAbsolutePath();
    }
}
