package by.kirilldikun.weatherviewerapi.auth.service;

import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;

public interface AuthenticationService {

    Session register(User user);

    Session authenticate(User user);
}
