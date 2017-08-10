package ru.parsentev.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.management.relation.Role;

import java.util.List;

/**
 * TODO: comment
 * @author parsentev
 * @since 17.04.2015
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class User extends Base {
    private String login;
    private String firstName;
    private String lastName;
    private String sex;
    private String phone;
    private String email;
    //private List<Message> messages;

    public User(Integer id, String login, String firstName, String lastName, String sex, String phone, String email) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }
}
