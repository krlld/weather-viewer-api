package by.kirilldikun.weatherviewerapi.auth.dao;

import by.kirilldikun.weatherviewerapi.auth.model.Session;

import java.util.Optional;

public interface SessionDao {

    Optional<Session> findByUserId(Long id);

    Session save(Session session);
}
