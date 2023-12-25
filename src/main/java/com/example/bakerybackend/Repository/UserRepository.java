package com.example.bakerybackend.Repository;

import com.example.bakerybackend.Entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
    public UserInfo save(UserInfo userInfo);
}
