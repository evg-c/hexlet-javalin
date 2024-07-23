package org.example.hexlet.repository;

import org.example.hexlet.model.Course;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository extends BaseRepository {
    //private static List<Course> entities = new ArrayList<Course>();

    public static void save(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            // устанавливаем ID в сохраненную сущность
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
        //course.setId((long) entities.size() + 1);
        //entities.add(course);
    }

    public static void update(Course course) throws SQLException {
        String sql = "UPDATE courses SET name = ?, description = ? WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setLong(3, course.getId());
            preparedStatement.executeUpdate();
        }
    }

    public static List<Course> search(String term) throws SQLException {
        var sql = "SELECT * FROM courses WHERE name LIKE ?";
        try (var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, term);
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
        //return entities.stream()
        //        .filter(entity -> entity.getName().startsWith(term))
        //        .toList();
    }

    public static Optional<Course> find(long id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                return Optional.of(course);
            }
            return Optional.empty();
        }
        //return entities.stream()
        //        .filter(entity -> entity.getId() == id)
        //        .findAny();
    }

    public static List<Course> getEntities() throws SQLException {
        String sql = "SELECT * FROM courses";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Course>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var description = resultSet.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
        //return entities;
    }

    public static void delete(Long id) throws SQLException {
        var sql = "DELETE * FROM courses WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
        //if (find(id) != null) {
        //    entities.remove(find(id));
        //}
    }
}
