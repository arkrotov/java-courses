package ru.parsentev.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.relation.Role;

import java.util.List;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.04.2015
 */

@Data
@AllArgsConstructor
public class User extends Base {
    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private String sex;
    private String phone;
    private String email;
    private Role role;
    private List<Message> messages;
}
