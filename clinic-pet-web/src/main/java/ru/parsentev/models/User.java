package ru.parsentev.models;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class User {

    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String sex;
    private String phone;
    private String email;
    private Set<Message> messages;
    private Role role;

}
