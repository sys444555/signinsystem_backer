package com.hc.modules.lesson.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.modules.lesson.mapper.LessonMapper;
import com.hc.modules.lesson.entity.LessonEntity;

import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, LessonEntity> implements LessonService {

    @Resource
    private LessonMapper lessonMapper;


    @Override
    public List<LessonEntity> getClassLessonList(Integer cid) {
        List<LessonEntity> classLessonList = lessonMapper.getClassLessonList(cid);
        return classLessonList;
    }

    @Override
    public void insertLesson(LessonEntity lessonEntity, String dataRange) {
        String[] split = dataRange.split(" - ");
        lessonEntity.setStartDate(split[0]);
        lessonEntity.setEndDate(split[1]);
        Integer result = lessonMapper.insertLesson(lessonEntity);
        if(result == null || result == 0){
            throw new JcException("新增课时失败");
        }
    }

    @Override
    public void insertLessonStudents(Integer coid, String[] studentList) {

        if(studentList != null){

            List<Integer> list = new ArrayList<>();
            for(int i=0;i<studentList.length;i++){
                Integer integer = Integer.valueOf(studentList[i]);
                list.add(integer);
            }
            lessonMapper.insertLessonStudents(coid, list);

        }else {
            throw new JcException("新增学员失败");
        }


    }

    @Override
    public void lessonSign(LessonEntity lessonEntity, Integer studentId) throws ParseException {

        CoursePackageEntity coursePackageEntity = lessonMapper.lessonSign(lessonEntity.getId(), studentId);
        if(coursePackageEntity == null){
            throw new JcException("该学员课时包为空或未设置");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(coursePackageEntity.getPeriodOfValidity());
        System.out.println("date = " + date);
        System.out.println("new Date() = " + new Date());
        if(date.compareTo(new Date()) < 0){
            throw new JcException("该学员课时包已过期");
        }
        BigDecimal classHour = lessonEntity.getClassHour();
        BigDecimal leftClassHour = coursePackageEntity.getLeftClassHour();
        BigDecimal consumedClassHour = coursePackageEntity.getConsumedClassHour();
        if(coursePackageEntity.getIsValidity() == null || coursePackageEntity.getIsValidity() == 0){
            throw new JcException("该学员课时包不在有效期");
        }
        BigDecimal add = consumedClassHour.add(classHour);
        BigDecimal subtract = leftClassHour.subtract(classHour);
        if(subtract.compareTo(BigDecimal.ZERO) < 0){
            throw new JcException("该学员课时包课时不足");
        }
        coursePackageEntity.setLeftClassHour(subtract);
        coursePackageEntity.setConsumedClassHour(add);
        lessonMapper.updateCoursePackage(coursePackageEntity);

    }
}
