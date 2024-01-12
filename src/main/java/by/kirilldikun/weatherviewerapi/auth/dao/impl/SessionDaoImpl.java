package by.kirilldikun.weatherviewerapi.auth.dao.impl;

import by.kirilldikun.weatherviewerapi.auth.dao.SessionDao;
import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.db.HikariConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

public class SessionDaoImpl implements SessionDao {

    private static final String SELECT_SESSION_BY_USER_ID_QUERY = "SELECT * FROM sessions WHERE user_id = ?";

    private static final String INSERT_SESSION_QUERY = "INSERT INTO sessions(id, user_id, expires_at) VALUES(?,?,?)";

    @Override
    public Optional<Session> findByUserId(Long id) {
        try (Connection connection = HikariConnectionPoolDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SESSION_BY_USER_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Session(UUID.fromString(resultSet.getString("id")),
                        resultSet.getLong("user_id"),
                        resultSet.getTimestamp("expires_at").toLocalDateTime()));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Session save(Session session) {
        try (Connection connection = HikariConnectionPoolDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SESSION_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, session.getId().toString());
            preparedStatement.setLong(2, session.getUserId());
            preparedStatement.setTimestamp(3, new Timestamp(session.getExpiresAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
            preparedStatement.execute();
            return session;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
