package com.groupservice.groupservice.repository;

import com.groupservice.groupservice.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}