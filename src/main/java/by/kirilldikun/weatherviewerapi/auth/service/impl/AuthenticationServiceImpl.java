package by.kirilldikun.weatherviewerapi.auth.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kirilldikun.weatherviewerapi.auth.exception.UserNotVerifiedException;
import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.auth.service.AuthenticationService;
import by.kirilldikun.weatherviewerapi.auth.service.SessionService;
import by.kirilldikun.weatherviewerapi.auth.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final int COST = 10;

    private final UserService userService;

    private final SessionService sessionService;

    @Override
    public Session register(User user) {
        user.setPassword(BCrypt.withDefaults().hashToString(COST, user.getPassword().toCharArray()));
        userService.save(user);
        return sessionService.create(user);
    }

    @Override
    public Session authenticate(User user) {
        User databaseUser = userService.findByEmail(user.getEmail());
        if (!BCrypt.verifyer().verify(user.getPassword().toCharArray(), databaseUser.getPassword()).verified) {
            throw new UserNotVerifiedException("User with email: " + user.getEmail() + " not verified");
        }
        return sessionService.create(databaseUser);
    }
}
