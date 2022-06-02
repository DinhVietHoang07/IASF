package com.fpt.hellospring.controller;


import com.fpt.hellospring.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")

public class StudentController {
    static List<Student> list = new ArrayList<>();

    {
        list.add(Student.builder().rollNumber("A001").fullName("Lio").build());
        list.add(Student.builder().rollNumber("A002").fullName("Lia").build());
        list.add(Student.builder().rollNumber("A003").fullName("Lib").build());
        list.add(Student.builder().rollNumber("A004").fullName("Lic").build());
        list.add(Student.builder().rollNumber("A005").fullName("Lid").build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll(){
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Student findById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++){
            if(list.get(1).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        return list.get(foundIndex);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@PathVariable Student student){
        list.add(student);
        return student;
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public boolean deleteById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++){
            if(list.get(1).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return false;
        }
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Student update(@PathVariable String id, @RequestBody Student updateStudent){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++){
            if(list.get(1).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        Student existing = list.get(foundIndex);
        existing.setFullName(updateStudent.getFullName());
        return existing;
    }
}
