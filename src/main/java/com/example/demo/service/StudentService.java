package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repo.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveStudents(List<Student> students) {
        return studentRepository.saveAll(students);
    }
    
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> getStudentsByClass(String studentClass) {
        return studentRepository.findByStudentClass(studentClass);
    }

    public Student updateStudent(Long id, Student studentDetails) throws Exception {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new Exception("Student not found"));

        student.setName(studentDetails.getName());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setJoiningDate(studentDetails.getJoiningDate());
        student.setStudentClass(studentDetails.getStudentClass());

        return studentRepository.save(student);
    }
}
