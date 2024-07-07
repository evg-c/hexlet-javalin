package org.example.hexlet.controller;

import io.javalin.http.Context;

public class SessionsController {
    public static void build(Context ctx) {
        ctx.render("sessions/build.jte");
    }

    public static void create(Context ctx) {
        var nickname = ctx.formParam("nickname");

        ctx.sessionAttribute("currentUser", nickname);
        ctx.redirect("/");
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
}
