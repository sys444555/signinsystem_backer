package com.hc.modules.member.service;

import com.hc.modules.member.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    public List<StudentEntity> getStudentList(String token);

    public void insertStudent(StudentEntity memberEntity);

}
