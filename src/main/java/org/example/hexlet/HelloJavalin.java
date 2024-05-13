package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;

import java.util.List;
import java.util.stream.Collectors;

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
               var id = ctx.pathParamAsClass("id", Long.class).get();
               var courses = List.of(new Course("Java", "Программирование на Java", 1),
                       new Course("JavaScript", "Программирование на JavaScript", 2),
                       new Course("Go", "Программирование на Go", 3));
               //var course = /* Курс извлекается из базы данных */
               var course = findCourse(courses, id);
               if (course == null) {
                    throw new NotFoundResponse("Course not found");
               }
               var page = new CoursePage(course);
               ctx.render("courses/show.jte", model("page", page));
          });

          app.get("/courses", ctx -> {
               //var courses = /* Список курсов извлекается из базы данных */
               var courses = List.of(new Course("Java", "Программирование на Java", 1),
                       new Course("JavaScript", "Программирование на JavaScript", 2),
                       new Course("Go", "Программирование на Go", 3));
               var header = "Курсы по программированию";
               List<Course> coursesTerm;
               var term = ctx.queryParam("term");
               if (term != null) {
                    coursesTerm = findCoursesTerm(courses, term);
               } else {
                    coursesTerm = courses;
               }
               var page = new CoursesPage(coursesTerm, header, term);
               ctx.render("courses/index.jte", model("page", page));
          });

          app.get("/", ctx -> ctx.render("index.jte"));

          app.start(7070);
     }

     public static Course findCourse(List<Course> listCourses, long idCourse) {
          return listCourses.stream()
                  .filter(u -> idCourse == u.getId())
                  .findFirst()
                  .orElse(null);
     }

     public static List<Course> findCoursesTerm(List<Course> listCourses, String term) {
          return listCourses.stream()
                  .filter(u -> u.getName().equalsIgnoreCase(term))
                  .collect(Collectors.toList());
     }
}

