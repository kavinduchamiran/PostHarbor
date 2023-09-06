package com.kavinduchamiran.postharbor.entities.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegeRepository extends JpaRepository<Privilege, Long> {
}
