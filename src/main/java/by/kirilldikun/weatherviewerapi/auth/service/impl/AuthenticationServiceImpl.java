package by.kirilldikun.weatherviewerapi.auth.service.impl;

import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.auth.service.AuthenticationService;
import by.kirilldikun.weatherviewerapi.auth.service.SessionService;
import by.kirilldikun.weatherviewerapi.auth.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final SessionService sessionService;

    @Override
    public Session register(User user) {
        userService.save(user);
        return sessionService.create(user);
    }

    @Override
    public Session authenticate(User user) {
        user = userService.findByEmail(user.getEmail());
        return sessionService.create(user);
    }
}
