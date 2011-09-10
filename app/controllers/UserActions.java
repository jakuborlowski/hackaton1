package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import play.data.validation.*;
import play.libs.Crypto;

public class UserActions extends Controller {

    public static void logout() {
        session.remove("uid");
        UserActions.login();
    }

    public static void login() {
        render();
    }

    public static void doLogin(@Required String email,
            @Required String plainPassword) {
        if (!Validation.hasErrors()) {
            User user = User.find("byEmailAndPassword", email,
                    Crypto.passwordHash(plainPassword)).first();

            if (user != null) {
                session.put("uid", user.email);
                Game.list();
            } else {
                Validation.addError("email", "błędny login lub hasło");
            }
        }

        params.flash();
        Validation.keep();
        login();
    }

    public static void register() {
        render();
    }

    public static void doRegister(
            @Required(message = "Wymagany e-mail") @Email(message = "Błędny e-mail") String email,
            @Required(message = "Wymagane hasło") String password)
            throws Exception {

        if (!Validation.hasErrors() && User.count("byEmail", email) > 0) {
            Validation.addError("email", "email jest już użyty");
        }

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            register();
        }

        User newUser = new User(email, password);
        newUser.save();

        UserActions.login();
    }

}
