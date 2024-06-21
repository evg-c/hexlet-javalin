package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
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

          app.get(NamedRoutes.usersPath(), UsersController::index);
          app.get(NamedRoutes.buildUserPath(), UsersController::build);
          app.post(NamedRoutes.usersPath(), UsersController::create);
          app.get(NamedRoutes.userPathId(), UsersController::show);

          app.get(NamedRoutes.userPathIdEdit(), UsersController::edit);
          app.post(NamedRoutes.userPathId(), UsersController::update);
          //app.patch(NamedRoutes.userPathId(), UsersController::update);

          app.delete(NamedRoutes.userPathId(), UsersController::destroy);

          app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
          app.post(NamedRoutes.coursesPath(), CoursesController::create);

          app.get(NamedRoutes.coursePathId(), CoursesController::show);
          app.get(NamedRoutes.coursesPath(), CoursesController::index);

          app.get(NamedRoutes.coursePathIdEdit(), CoursesController::edit);
          app.post(NamedRoutes.coursePathId(), CoursesController::update);
          //app.patch(NamedRoutes.coursePathId(), CoursesController::update);

          app.delete(NamedRoutes.coursePathId(), CoursesController::destroy);

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

