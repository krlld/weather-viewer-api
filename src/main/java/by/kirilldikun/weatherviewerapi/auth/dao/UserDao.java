package by.kirilldikun.weatherviewerapi.auth.dao;

import by.kirilldikun.weatherviewerapi.auth.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean existsByEmail(String email);
}
