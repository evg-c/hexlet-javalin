package org.example.hexlet;

public class NamedRoutes {
    // Маршрут пользователей
    public static String usersPath() {
        return "/users";
    }

    // Маршрут создания пользователя
    public static String buildUserPath() {
        return "/users/build";
    }

    public static String userPathId() {
        return "/users/{id}";
    }

    public static String userPathIdEdit() {
        return "/users/{id}/edit";
    }

    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String userPathEdit(Long id) {
        return userPathEdit(String.valueOf(id));
    }

    public static String userPathEdit(String id) {
        return "/users/" + id + "/edit";
    }

    // Маршрут курсов
    public static String coursesPath() {
        return "/courses";
    }

    // Маршрут конкретного курса по id
    // это нужно, чтобы не преобразовывать типы снаружи
    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    // Маршрут создания курса
    public static String buildCoursePath() {
        return "/courses/build";
    }

    public static String coursePathId() {
        return "/courses/{id}";
    }

    public static String coursePathIdEdit() {
        return "/courses/{id}/edit";
    }
}
