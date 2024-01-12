package by.kirilldikun.weatherviewerapi.common.service.impl;

import by.kirilldikun.weatherviewerapi.auth.exception.IncorrectEmailException;
import by.kirilldikun.weatherviewerapi.auth.exception.IncorrectPasswordException;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.common.service.MapperService;
import by.kirilldikun.weatherviewerapi.common.service.ValidationService;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperServiceImpl implements MapperService {

    private final ValidationService validationService = new ValidationServiceImpl();

    @Override
    public User toUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (!validationService.isValidEmail(email)) {
            throw new IncorrectEmailException("Incorrect email");
        }
        if (!validationService.isValidPassword(password)) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return new User()
                .setEmail(email)
                .setPassword(password);
    }
}
