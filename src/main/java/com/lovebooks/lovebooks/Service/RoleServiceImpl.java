package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Dao.RoleDao;
import com.lovebooks.lovebooks.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao roleDao;

    @Override
    public Role getRoleByName(String name) {
        return roleDao.findByRole(name);
    }
}
