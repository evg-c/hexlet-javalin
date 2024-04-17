package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.List;

public class HelloWorld {
     public static void main(String[] args) {
          // создаем приложение
          var app = Javalin.create(config -> {
               config.bundledPlugins.enableDevLogging();
               config.fileRenderer(new JavalinJte());
          });
          // описываем 
          // Обратите внимание, что id — это не обязательно число

          app.get("/courses/{id}", ctx -> {
               var id = ctx.pathParam("id");
               //var course = /* Курс извлекается из базы данных */
                       // Предполагаем, что у курса есть метод getName()
               var courses = List.of(new Course("Java", "Программирование на Java"),
                       new Course("JavaScript", "Программирование на JavaScript"),
                       new Course("Go", "Программирование на Go"));
               var header = "Курсы по программированию";
               var page = new CoursesPage(courses, header);
               ctx.render("courses/index.jte", model("page", page));
          });

          app.start(7070);
     }
}

