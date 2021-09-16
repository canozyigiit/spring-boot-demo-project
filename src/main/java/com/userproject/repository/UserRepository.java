package com.userproject.repository;


import com.userproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

   User getByEmail(String Email);
}
