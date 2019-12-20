package com.hc;

import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class SigninsystemApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;

    @Test
    void contextLoads() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("a");
        Integer id = 1;
        studentService.insertStudent(studentEntity, 1);

    }
    @Test
    void t1() {


    }

    @Test
    void te(){

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("hello");

        courseService.insertCourse(courseEntity, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqd3QtaWQiOiIwN2M5NGYwYi04OWQxLTQ1NjEtODM1Yy0xYWE1ZDMyNzgwMWUiLCJleHAiOjE1NzY4MzY0NTEsInVzZXJuYW1lIjoidXNlciJ9.cHEi3LgEfRltkHof5JzxDTClytv6PR62O16c1P6dIM0");


    }

}
