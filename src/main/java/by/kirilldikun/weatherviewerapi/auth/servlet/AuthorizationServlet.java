package by.kirilldikun.weatherviewerapi.auth.servlet;

import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.auth.service.AuthenticationService;
import by.kirilldikun.weatherviewerapi.common.service.MapperService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    private MapperService mapperService;

    private AuthenticationService authenticationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mapperService = (MapperService) config.getServletContext().getAttribute("mapperService");
        authenticationService = (AuthenticationService) config.getServletContext().getAttribute("authenticationService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = mapperService.toUser(req);
            Session session = authenticationService.authenticate(user);
            resp.addCookie(new Cookie("SESSION_ID", session.getId().toString()));
        } catch (RuntimeException e) {
            log.error(e.toString());
            resp.getWriter().println(e.getMessage());
        }
    }
}
