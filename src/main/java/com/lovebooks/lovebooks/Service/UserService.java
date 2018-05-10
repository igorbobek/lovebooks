package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Model.User;

public interface UserService {

    void save(User user);
    User findById(Long id);
    User findByName(String name);
    User findByEmail(String email);
    void update(User user);

}
