package com.ypandey.calllogsystem.repository;
import com.ypandey.calllogsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

