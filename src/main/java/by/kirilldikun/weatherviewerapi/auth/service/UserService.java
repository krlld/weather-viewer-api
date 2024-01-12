package by.kirilldikun.weatherviewerapi.auth.service;

import by.kirilldikun.weatherviewerapi.auth.model.User;

public interface UserService {

    User findByEmail(String email);

    User save(User user);
}
