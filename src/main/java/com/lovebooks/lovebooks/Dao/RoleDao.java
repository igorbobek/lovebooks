package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
