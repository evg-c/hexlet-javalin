package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage2;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.BaseRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class HelloJavalin {
     public static Javalin getApp() throws Exception {

          var hikariConfig = new HikariConfig();
          hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");

          var dataSource = new HikariDataSource(hikariConfig);
          // получаем путь до файла в src/main/resources
          var url = HelloJavalin.class.getClassLoader().getResourceAsStream("schema.sql");
          var sql = new BufferedReader(new InputStreamReader(url))
                  .lines()
                  .collect(Collectors.joining("\n"));

          // получаем соединение, создаем стейтмент и выполняем запрос
          try (var connection = dataSource.getConnection();
               var statement = connection.createStatement()) {
               statement.execute(sql);
          }
          BaseRepository.dataSource = dataSource;

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

          app.delete(NamedRoutes.userPathId(), UsersController::destroy);

          app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
          app.post(NamedRoutes.coursesPath(), CoursesController::create);

          app.get(NamedRoutes.coursePathId(), CoursesController::show);
          app.get(NamedRoutes.coursesPath(), CoursesController::index);

          app.get(NamedRoutes.coursePathIdEdit(), CoursesController::edit);
          app.post(NamedRoutes.coursePathId(), CoursesController::update);

          app.delete(NamedRoutes.coursePathId(), CoursesController::destroy);

          app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
          app.post(NamedRoutes.sessionsPath(), SessionsController::create);
          app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

//          app.get("/", ctx -> {
//               var visited = Boolean.valueOf(ctx.cookie("visited"));
//               var page = new MainPage(visited);
//               ctx.render("index.jte", model("page", page));
//               ctx.cookie("visited", String.valueOf(true));
//          });
          app.get("/", ctx -> {
               var page = new MainPage2(ctx.sessionAttribute("currentUser"));
               ctx.render("index2.jte", model("page", page));
          });

          return app;
     }

     public static void main(String[] args) throws Exception {
          Javalin app = getApp();
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

