package com.hc;

import com.github.qcloudsms.httpclient.HTTPException;
import com.hc.modules.business.entity.BusinessEntity;
import com.hc.modules.business.service.BusinessService;
import com.hc.modules.course.entity.CourseEntity;
import com.hc.modules.course.service.CourseService;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.entity.StudentLessonEntity;
import com.hc.modules.student.service.CoursePackageService;
import com.hc.modules.student.service.StudentService;
import com.hc.modules.teacher.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SigninsystemApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private CourseService courseService;

    @Resource
    private LessonService lessonService;

    @Resource
    private CoursePackageService coursePackageService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private BusinessService businessService;

    @Test
    void contextLoads() throws ParseException {
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setClassHour(new BigDecimal(2.0));
        lessonEntity.setClassId(1);

    }
    @Test
    void t1() {
        StudentEntity s = new StudentEntity();
        s.setId(96);
        s.setName("xx356");
        studentService.updateStudent(s);



    }

    @Test
    void te() {
        StudentEntity s = new StudentEntity();
        s.setId(93);
        s.setName("蔡伦");
       lessonService.lessonSign(77,96,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqd3QtaWQiOiJmNTM5OWM0Ni1kOWUxLTQ1NjktOTQ0MS1mMzc0MmIxMjExNjciLCJleHAiOjE1Nzc0NDcxMzMsInVzZXJuYW1lIjoiemhpeWkifQ.aIW2DjtqKtxg-d0IR_M475PzF-jQGEHLi7Ev8YV91uw");

    }

}
