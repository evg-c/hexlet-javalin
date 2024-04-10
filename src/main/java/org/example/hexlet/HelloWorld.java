package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
     public static void main(String[] args) {
          // создаем приложение
          var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());
          // описываем 
          app.get("/hello", ctx -> {
              var name = ctx.queryParam("name");
              //if (ctx.queryParam("name").isEmpty()) {
              if ((name == null) || (name.isEmpty())) {
                    name = "World";
              }
              ctx.result("Hello, " + name);
          });
          app.start(7070);
     }
}

