package com.example.SpringSec.repo;

import com.example.SpringSec.model.Student;
import com.example.SpringSec.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<Users, Integer> {


    User findByUsername(String username);
}
