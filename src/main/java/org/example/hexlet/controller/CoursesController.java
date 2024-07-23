package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {
    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        long id = 0;
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
            id = course.getId();
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var page = new BuildCoursePage(id, name, description, e.getErrors());
            ctx.render("courses/build.jte", model("page", page));
        }
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        //var courses =
                //List.of(new Course("Java", "Программирование на Java", 1),
                //new Course("JavaScript", "Программирование на JavaScript", 2),
                //new Course("Go", "Программирование на Go", 3));
        //var course = /* Курс извлекается из базы данных */
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Course with id " + id + " not found"));
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void index(Context ctx) throws SQLException {
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
    }

    public static void edit(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var name = course.getName();
        var description = course.getDescription();
        var page = new BuildCoursePage(id, name, description, null);
        ctx.render("courses/edit.jte", model("page", page));
    }

    public static void update(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var name = ctx.formParam("name").trim();
        var description = ctx.formParam("description").trim().toLowerCase();
        try {
            name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.trim().length() > 2, "Недостаточная длина названия курса")
                    .get();
            description = ctx.formParamAsClass("description", String.class)
                    .check(value -> value.trim().length() > 10, "Недостаточная длина описания курса")
                    .get();
            var course = CourseRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
            course.setName(name);
            course.setDescription(description);
            CourseRepository.update(course);
            ctx.redirect(NamedRoutes.coursePath(id));
        } catch (ValidationException e) {
            var page = new BuildCoursePage(id, name, description, e.getErrors());
            ctx.render("courses/edit.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) throws SQLException {
        var id = ctx.formParamAsClass("id", Long.class).get();
        CourseRepository.delete(id);
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
