package org.example.hexlet.repository;

import org.example.hexlet.model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository {
    //private static List<User> entities = new ArrayList<User>();

    public static void save(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
                var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
        //user.setId((long) entities.size() + 1);
        //entities.add(user);
    }

    public static void update(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        }
    }
    //public static List<User> search(String term) {
    //    return entities.stream()
    //            .filter(entity -> entity.getName().startsWith(term))
    //            .toList();
    //}

    public static Optional<User> find(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                return Optional.of(user);
            }
            return Optional.empty();
        }

        //return entities.stream()
        //        .filter(entity -> entity.getId() == id)
        //        .findAny();
    }

    public static void delete(long id) throws SQLException {
        //if (find(id) != null) {
        //    entities.remove(find(id));
        //}
        String sql = "DELETE FROM users WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public static List<User> getEntities() throws SQLException {
        String sql = "SELECT * FROM users";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<User>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
        //return entities;
    }
}
