package com.hc.modules.lesson.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.modules.lesson.mapper.LessonMapper;
import com.hc.modules.lesson.entity.LessonEntity;

import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public void insertLesson(LessonEntity lessonEntity, String dataRange, Integer period, Integer times) throws ParseException {
        String[] split = dataRange.split(" - ");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (period){
            case 0:
                lessonEntity.setStartDate(split[0]);
                lessonEntity.setEndDate(split[1]);
                Integer result = lessonMapper.insertLesson(lessonEntity);
                if(result == null || result == 0){
                    throw new JcException("新增课时失败");
                }
                break;
            case 1:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(split[0]);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, i);
                    parseStartDate = c1.getTime();
                    String startDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(startDate);

                    Date parseEndDate = simpleDateFormat.parse(split[1]);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, i);
                    parseEndDate = c2.getTime();
                    String endDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(endDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 2:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(split[0]);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 2*i);
                    parseStartDate = c1.getTime();
                    String startDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(startDate);

                    Date parseEndDate = simpleDateFormat.parse(split[1]);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 2*i);
                    parseEndDate = c2.getTime();
                    String endDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(endDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 3:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(split[0]);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 7*i);
                    parseStartDate = c1.getTime();
                    String startDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(startDate);

                    Date parseEndDate = simpleDateFormat.parse(split[1]);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 7*i);
                    parseEndDate = c2.getTime();
                    String endDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(endDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 4:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(split[0]);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 14*i);
                    parseStartDate = c1.getTime();
                    String startDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(startDate);

                    Date parseEndDate = simpleDateFormat.parse(split[1]);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 14*i);
                    parseEndDate = c2.getTime();
                    String endDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(endDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
        }

    }

    @Override
    public void insertLessonStudents(Integer coid, String[] studentList) {


        //清空课节学员
        Integer result = lessonMapper.deleteLessonStudent(coid);
        System.out.println("result = " + result);

        if(studentList != null){
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<studentList.length;i++){
                Integer integer = Integer.valueOf(studentList[i]);
                list.add(integer);
            }
            lessonMapper.insertLessonStudents(coid, list);
        }




    }

    @Override
    public void lessonSign(LessonEntity lessonEntity, Integer studentId) throws ParseException {

        CoursePackageEntity coursePackageEntity = lessonMapper.lessonSign(lessonEntity.getId(), studentId);
        if(coursePackageEntity == null){
            throw new JcException("该学员课时包为空或未设置");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
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

    @Override
    public LessonEntity getLesson(Integer lessonId) {
        LessonEntity lesson = lessonMapper.getLesson(lessonId);

        return lesson;
    }

    @Override
    public List<StudentEntity> getLessonStudentList(Integer lessonId) {
        List<StudentEntity> lessonStudentList = lessonMapper.getLessonStudentList(lessonId);
        return lessonStudentList;
    }

    @Override
    public List<StudentEntity> getLessonAbsentStudentList(Integer lessonId) {
        List<StudentEntity> lessonAbsentStudentList = lessonMapper.lessonAbsentStudentList(lessonId);
        return lessonAbsentStudentList;
    }
}
