package com.lcwd.electronic.store.ElectronicStore.dtos;

import com.lcwd.electronic.store.ElectronicStore.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;

    @Size(min = 5, max = 20, message = "invalid name")
    private String name;

    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @Size(min = 4, max = 6, message = "invalid gender")
    private String gender;

    @NotBlank(message = "Write something about yourself")
    private String about;

    @ImageNameValid
    private String userImage;
}
