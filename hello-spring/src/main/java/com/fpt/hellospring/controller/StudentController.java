package com.fpt.hellospring.controller;


import com.fpt.hellospring.entity.Student;
import com.fpt.hellospring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/students")

public class StudentController {

    @Autowired
    StudentService studentService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getList(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable String id){
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalStudent.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> create(@RequestBody Student student){
        return ResponseEntity.ok(studentService.save(student));
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
    if (!studentService.findById(id).isPresent()){
        ResponseEntity.badRequest().build();
    }
    studentService.deleteById(id);
    return ResponseEntity.ok().build();
}

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student updateStudent){
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()){
            ResponseEntity.badRequest().build();
        }
        Student existStudent = optionalStudent.get();

        existStudent.setRollNumber(existStudent.getRollNumber());
        existStudent.setFullName(existStudent.getFullName());
        return ResponseEntity.ok(studentService.save(existStudent));

    }
}
