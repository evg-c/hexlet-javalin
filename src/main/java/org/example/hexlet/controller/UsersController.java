package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParam("name").trim();
        var email = ctx.formParam("email").trim().toLowerCase();
        long id = 0;
        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            id = user.getId();
            ctx.sessionAttribute("flash", "Пользователь добавлен!");
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Не удалось добавить пользователя!");
            var page = new BuildUserPage(id, name, email, e.getErrors());
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("users/build.jte", model("page", page));
        }
    }

    public static void edit(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var name = user.getName();
        var email = user.getEmail();
        var page = new BuildUserPage(id, name, email, null);
        ctx.render("users/edit.jte", model("page", page));
    }

    public static void update(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var name = ctx.formParam("name").trim();
        var email = ctx.formParam("email").trim().toLowerCase();
        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .get();
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            UserRepository.update(user);
            ctx.redirect(NamedRoutes.userPath(id));
        } catch (ValidationException e) {
            var page = new BuildUserPage(id, name, email, e.getErrors());
            ctx.render("users/edit.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        UserRepository.delete(id);
        ctx.redirect(NamedRoutes.usersPath());
    }
}
