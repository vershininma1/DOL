package com.dol.entity;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import static com.dol.Constants.*;
@Entity(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getUser", query = "SELECT a FROM users a where a.login = :login")
})
/**
 * пользователи
 */
public class User {
    private static final String CONST_user_id="user_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CONST_user_id)
    private int id;

    @Column(name =CONST_login , nullable = false)
    private String login;

    @Lob
    @Column(name = CONST_pass, nullable = false)
    @Type(type=CONST_hibernate)
    private byte[] pass;

    public User() {
    }
    public User(String login, byte[] pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPass() {
        return pass;
    }

    public void setPass(byte[] pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
