package by.kirilldikun.weatherviewerapi.auth.service.impl;

import by.kirilldikun.weatherviewerapi.auth.dao.SessionDao;
import by.kirilldikun.weatherviewerapi.auth.dao.UserDao;
import by.kirilldikun.weatherviewerapi.auth.exception.SessionNotFoundException;
import by.kirilldikun.weatherviewerapi.auth.model.Session;
import by.kirilldikun.weatherviewerapi.auth.model.User;
import by.kirilldikun.weatherviewerapi.auth.service.SessionService;
import by.kirilldikun.weatherviewerapi.common.util.PropertySource;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private static final long duration = Long.parseLong(PropertySource.get("session.duration"));

    private final UserDao userDao;

    private final SessionDao sessionDao;

    @Override
    public Session findByUserId(Long userId) {
        return sessionDao.findByUserId(userId)
                .orElseThrow(() -> new SessionNotFoundException("Session for user with id: " + userId + " not found"));
    }

    @Override
    public Session create(User user) {
        Session session = new Session(UUID.randomUUID(), user.getId(), LocalDateTime.now().plusSeconds(duration));
        return sessionDao.save(session);
    }
}
