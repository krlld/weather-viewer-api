package by.kirilldikun.weatherviewerapi.auth.service;

import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;

public interface SessionService {

    Session findByUserId(Long userId);

    Session create(User user);
}
