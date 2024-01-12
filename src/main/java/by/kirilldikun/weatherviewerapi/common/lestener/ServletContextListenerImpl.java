package by.kirilldikun.weatherviewerapi.common.lestener;

import by.kirilldikun.weatherviewerapi.auth.dao.SessionDao;
import by.kirilldikun.weatherviewerapi.auth.dao.UserDao;
import by.kirilldikun.weatherviewerapi.auth.dao.impl.SessionDaoImpl;
import by.kirilldikun.weatherviewerapi.auth.dao.impl.UserDaoImpl;
import by.kirilldikun.weatherviewerapi.auth.service.AuthenticationService;
import by.kirilldikun.weatherviewerapi.auth.service.SessionService;
import by.kirilldikun.weatherviewerapi.auth.service.UserService;
import by.kirilldikun.weatherviewerapi.auth.service.impl.AuthenticationServiceImpl;
import by.kirilldikun.weatherviewerapi.auth.service.impl.SessionServiceImpl;
import by.kirilldikun.weatherviewerapi.auth.service.impl.UserServiceImpl;
import by.kirilldikun.weatherviewerapi.common.service.MapperService;
import by.kirilldikun.weatherviewerapi.common.service.impl.MapperServiceImpl;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);

        UserDao userDao = new UserDaoImpl();
        SessionDao sessionDao = new SessionDaoImpl();

        UserService userService = new UserServiceImpl(userDao);
        sce.getServletContext().setAttribute("userService", userService);
        SessionService sessionService = new SessionServiceImpl(userDao, sessionDao);
        sce.getServletContext().setAttribute("sessionService", sessionService);
        AuthenticationService authenticationService = new AuthenticationServiceImpl(userService, sessionService);
        sce.getServletContext().setAttribute("authenticationService", authenticationService);

        MapperService mapperService = new MapperServiceImpl();
        sce.getServletContext().setAttribute("mapperService", mapperService);
    }
}
