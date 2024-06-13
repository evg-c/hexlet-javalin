package org.example.hexlet;

public class NamedRoutes {
    // Маршрут пользователей
    public static String usersPath() {
        return "/u";
    }

    // Маршрут создания пользователя
    public static String buildUserPath() {
        return "/u/build";
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
}
