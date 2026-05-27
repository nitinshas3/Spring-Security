package com.example.SpringSec;

import org.springframework.security.web.server.ui.OneTimeTokenSubmitPageGeneratingWebFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student>  students = new ArrayList<>(List.of(
            new Student(1,60,"Nitin"),
            new Student(2,90,"navin")
            ));

    @GetMapping("/students")
    public List<Student> getStudents (){
        return students;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }
}
