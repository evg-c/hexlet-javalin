package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
     public static void main(String[] args) {
          // создаем приложение
          var app = Javalin.create(config -> config.bundledPlugins.enableDevLogging());
          // описываем 
          // Обратите внимание, что id — это не обязательно число

          // Название параметров мы выбрали произвольно
          app.get("/users/{id}/post/{postId}", ctx -> {
             ctx.result("User ID: " + ctx.pathParam("id") + "   Post ID: " + ctx.pathParam("postId"));
             // ctx.result("Lesson ID: " + ctx.pathParam("id"));
          });

          app.start(7070);
     }
}

