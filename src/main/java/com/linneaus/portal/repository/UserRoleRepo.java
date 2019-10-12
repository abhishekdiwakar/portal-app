package com.linneaus.portal.repository;

import com.linneaus.portal.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<Role, Long>{
}
