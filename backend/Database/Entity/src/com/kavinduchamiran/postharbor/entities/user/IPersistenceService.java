package com.kavinduchamiran.postharbor.entities.user;

import java.util.List;
import java.util.Optional;

public interface IPersistenceService<T> {

    List<T> findAll();

    Optional<T> findById(String id);

    T save(T user);

    void deleteById(String id);
}
