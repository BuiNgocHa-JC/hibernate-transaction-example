package org.example.hibernate.main;

import org.example.hibernate.dao.StudentDao;
import org.example.hibernate.model.Student;

public class Main {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        Student student = new Student("Jap","anese","japanese@gmail.com");
        studentDao.saveStudent(student);
        studentDao.insertStudent();
        studentDao.getStudent(student.getId());
    }
}