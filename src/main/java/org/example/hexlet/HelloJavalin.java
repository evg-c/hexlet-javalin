package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HelloJavalin {
     public static void main(String[] args) {
          // создаем приложение
          var app = Javalin.create(config -> {
               config.bundledPlugins.enableDevLogging();
               config.fileRenderer(new JavalinJte());
          });
          // описываем 
          // Обратите внимание, что id — это не обязательно число

          app.get("/users/build", ctx -> {
               var page = new BuildUserPage();
               ctx.render("users/build.jte", model("page", page));
          });

          app.post("/users", ctx -> {
               var name = ctx.formParam("name").trim();
               var email = ctx.formParam("email").trim().toLowerCase();

               try {
                    var passwordConfirmation = ctx.formParam("passwordConfirmation");
                    var password = ctx.formParamAsClass("password", String.class)
                            .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                            .get();
                    var user = new User(name, email, password);
                    UserRepository.save(user);
                    ctx.redirect("/users");
               } catch (ValidationException e) {
                    var page = new BuildUserPage(name, email, e.getErrors());
                    ctx.render("users/build.jte", model("page", page));
               }
          });

          app.get("/users", ctx -> {
             var users = UserRepository.getEntities();
             var page = new UsersPage(users);
             ctx.render("users/index.jte", model("page", page));
          });

          app.get("/courses/build", ctx -> {
               var page = new BuildCoursePage();
               ctx.render("courses/build.jte", model("page", page));
          });

          app.post("/courses", ctx -> {
               var name = ctx.formParam("name").trim();
               var description = ctx.formParam("description").trim().toLowerCase();
               try {
                  name = ctx.formParamAsClass("name", String.class)
                          .check(value -> value.trim().length() > 2, "Недостаточная длина названия курса")
                          .get();
                  description = ctx.formParamAsClass("description", String.class)
                          .check(value -> value.trim().length() > 10, "Недостаточная длина описания курса")
                          .get();
                  var course = new Course(name, description);
                  CourseRepository.save(course);
                  ctx.redirect("/courses");
             } catch (ValidationException e) {
                  var page = new BuildCoursePage(name, description, e.getErrors());
                  ctx.render("courses/build.jte", model("page", page));
             }
          });

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
               //var courses = List.of(new Course("Java", "Программирование на Java", 1),
               //        new Course("JavaScript", "Программирование на JavaScript", 2),
               //        new Course("Go", "Программирование на Go", 3));
               var courses = CourseRepository.getEntities();
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

