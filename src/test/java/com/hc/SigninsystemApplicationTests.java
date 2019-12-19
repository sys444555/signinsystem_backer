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
        List<CourseEntity> classCourseList = courseService.getClassCourseList(1);
        System.out.println("classCourseList = " + classCourseList);

    }

    @Test
    void te(){
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName("dfs");
        courseEntity.setClassHour(new BigDecimal(1.00));
        courseEntity.setClassId(1);
        String s = "2019-12-13 01:01:01 - 2019-12-13 02:02:06";
        String[] split = s.split(" - ");
        String[] str = {"1","12"};

        courseService.insertCourseStudents(3,str);


    }

}
