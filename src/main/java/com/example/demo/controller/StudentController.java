package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PostMapping("/save/all")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
        List<Student> savedStudents = studentService.saveStudents(students);
        return new ResponseEntity<>(savedStudents, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) throws Exception {
        Student student = studentService.getStudentById(id)
            .orElseThrow(() -> new Exception("Student not found"));
        return ResponseEntity.ok(student);
    }

    @GetMapping("/search")
    public List<Student> getStudentsByCriteria(@RequestParam(required = false) String name, @RequestParam(required = false) String studentClass) {
        if (name != null) {
            return studentService.getStudentsByName(name);
        } else if (studentClass != null) {
            return studentService.getStudentsByClass(studentClass);
        } else {
            return studentService.getStudents();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) throws Exception {
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }
}
