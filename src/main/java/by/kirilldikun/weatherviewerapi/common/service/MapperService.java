package by.kirilldikun.weatherviewerapi.common.service;

import by.kirilldikun.weatherviewerapi.auth.model.User;
import javax.servlet.http.HttpServletRequest;

public interface MapperService {

    User toUser(HttpServletRequest request);
}
