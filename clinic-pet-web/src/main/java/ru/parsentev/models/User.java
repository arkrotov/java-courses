package ru.parsentev.models;

import lombok.*;

import java.util.Set;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.04.2015
 */

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class User extends Base {
    private String login;
    private String firstName;
    private String lastName;
    private String sex;
    private String phone;
    private String email;
    private Set<Message> messages;
    private Role role;

    public User(int i, String login, String firstName, String lastName, String sex, String phone, String email) {

    }
}
