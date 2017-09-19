package ru.parsentev.models;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    @NotNull
    private String login;
    private String firstName;
    private String lastName;
    private String sex;
    private String phone;
    private String email;
    private Role role;

}
