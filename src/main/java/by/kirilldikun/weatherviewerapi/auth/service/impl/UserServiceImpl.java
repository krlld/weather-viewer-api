package by.kirilldikun.weatherviewerapi.auth.service.impl;

import by.kirilldikun.weatherviewerapi.auth.dao.UserDao;
import by.kirilldikun.weatherviewerapi.auth.exception.UserAlreadyExistsException;
import by.kirilldikun.weatherviewerapi.auth.exception.UserNotFoundException;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.auth.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));
    }

    @Override
    public User save(User user) {
        if (userDao.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email: " + user.getEmail() + " already exists");
        }
        return userDao.save(user);
    }
}
