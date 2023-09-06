package com.kavinduchamiran.postharbor.persistence.service;

import java.util.List;
import java.util.Optional;

public interface IPersistenceService<T> {

    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T user);

    void deleteById(Long id);
}
