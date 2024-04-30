package org.example.hexlet.dto.courses;

import org.example.hexlet.model.Course;
import lombok.Getter;

import java.util.List;

@Getter
public class CoursesPage {
    private List<Course> courses;
    private String header;
    public CoursesPage(List<Course> courses, String header) {
        this.courses = courses;
        this.header = header;
    }
}
