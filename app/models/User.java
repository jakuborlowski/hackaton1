package models;

import javax.persistence.*;
import play.data.validation.*;
import play.db.jpa.Model;
import play.libs.Crypto;

@Entity
public class User extends Model {
    @Required
    @Email
    public String email;

    @Required
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(@Required @Email String email) {
        this.email = email;
    }

    public void setPassword(@Required String password) {
        this.password = Crypto.passwordHash(password);
    }
}
