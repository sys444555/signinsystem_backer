package com.hc.modules.student.service;


import com.hc.modules.student.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    public List<StudentEntity> getStudentList(String token);

    public void insertStudent(StudentEntity memberEntity);

}
