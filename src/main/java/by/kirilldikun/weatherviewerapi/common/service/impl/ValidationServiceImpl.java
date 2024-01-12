package by.kirilldikun.weatherviewerapi.common.service.impl;

import by.kirilldikun.weatherviewerapi.common.service.ValidationService;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isValidEmail(String email) {
        return email != null && !email.isBlank();
    }

    @Override
    public boolean isValidPassword(String password) {
        return password != null && !password.isBlank();
    }
}
