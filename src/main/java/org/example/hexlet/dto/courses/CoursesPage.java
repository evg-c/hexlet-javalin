package org.example.hexlet.dto.courses;

import lombok.AllArgsConstructor;
import org.example.hexlet.model.Course;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CoursesPage {
    private List<Course> courses;
    private String header;
    private String term;

    //public CoursesPage(List<Course> courses) {
    //}
    //public CoursesPage(List<Course> courses, String header) {
    //    this.courses = courses;
    //    this.header = header;
    //}
}
