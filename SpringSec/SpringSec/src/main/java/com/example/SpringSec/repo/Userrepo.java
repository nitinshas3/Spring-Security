package com.example.SpringSec.repo;

import com.example.SpringSec.model.Student;
import com.example.SpringSec.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<Users, Integer> {

}
