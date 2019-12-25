package com.hc.modules.lesson.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.hc.common.exception.JcException;
import com.hc.common.utils.SmsUtils;
import com.hc.modules.lesson.mapper.LessonMapper;
import com.hc.modules.lesson.entity.LessonEntity;

import com.hc.modules.lesson.service.LessonService;
import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.entity.StudentEntity;
import com.hc.modules.student.entity.StudentLessonEntity;
import com.hc.modules.student.mapper.StudentMapper;
import com.hc.modules.student.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
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

    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<LessonEntity> getClassLessonList(Integer cid) {
        List<LessonEntity> classLessonList = lessonMapper.getClassLessonList(cid);
        return classLessonList;
    }

    @Override
    public void insertLesson(LessonEntity lessonEntity, String dataRange, String timeRange, Integer period, Integer times) throws ParseException {

        String[] split = timeRange.split(" - ");
        String startDate = dataRange + " " + split[0];

        String endDate = dataRange + " " + split[1];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (period){
            case 0:
                lessonEntity.setStartDate(startDate);
                lessonEntity.setEndDate(endDate);
                Integer result = lessonMapper.insertLesson(lessonEntity);
                if(result == null || result == 0){
                    throw new JcException("新增课时失败");
                }
                break;
            case 1:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(startDate);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, i);
                    parseStartDate = c1.getTime();
                    String newStartDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(newStartDate);

                    Date parseEndDate = simpleDateFormat.parse(endDate);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, i);
                    parseEndDate = c2.getTime();
                    String newEndDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(newEndDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 2:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(startDate);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 2*i);
                    parseStartDate = c1.getTime();
                    String newStartDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(newStartDate);

                    Date parseEndDate = simpleDateFormat.parse(endDate);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 2*i);
                    parseEndDate = c2.getTime();
                    String newEndDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(newEndDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 3:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(startDate);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 7*i);
                    parseStartDate = c1.getTime();
                    String newStartDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(newStartDate);

                    Date parseEndDate = simpleDateFormat.parse(endDate);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 7*i);
                    parseEndDate = c2.getTime();
                    String newEndDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(newEndDate);
                    Integer result1 = lessonMapper.insertLesson(lessonEntity);
                    if(result1 == null || result1 == 0){
                        throw new JcException("新增课时失败");
                    }
                }
                break;
            case 4:
                for(int i=0;i<times;i++){
                    Date parseStartDate = simpleDateFormat.parse(startDate);
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(parseStartDate);
                    c1.add(Calendar.DAY_OF_MONTH, 14*i);
                    parseStartDate = c1.getTime();
                    String newStartDate = simpleDateFormat.format(parseStartDate);
                    lessonEntity.setStartDate(newStartDate);

                    Date parseEndDate = simpleDateFormat.parse(endDate);
                    Calendar c2 = Calendar.getInstance();
                    c2.setTime(parseEndDate);
                    c2.add(Calendar.DAY_OF_MONTH, 14*i);
                    parseEndDate = c2.getTime();
                    String newEndDate = simpleDateFormat.format(parseEndDate);
                    lessonEntity.setEndDate(newEndDate);
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
    public void lessonSign(Integer lessonId, Integer studentId) throws ParseException {

        CoursePackageEntity coursePackageEntity = lessonMapper.findCoursePackage(lessonId, studentId);
        if(coursePackageEntity == null){
            throw new JcException(999,"该学员课时包为空或未设置");
        }
        LessonEntity lesson = this.getLesson(lessonId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(coursePackageEntity.getPeriodOfValidity());
        System.out.println("date = " + date);
        System.out.println("new Date() = " + new Date());
        if(date.compareTo(new Date()) < 0){
            throw new JcException(999,"该学员课时包已过期");
        }
        BigDecimal classHour = lesson.getClassHour();
        BigDecimal leftClassHour = coursePackageEntity.getLeftClassHour();
        BigDecimal consumedClassHour = coursePackageEntity.getConsumedClassHour();
        if(coursePackageEntity.getIsValidity() == null || coursePackageEntity.getIsValidity() == 0){
            throw new JcException(999,"该学员课时包不在有效期");
        }
        BigDecimal add = consumedClassHour.add(classHour);
        BigDecimal subtract = leftClassHour.subtract(classHour);
        if(subtract.compareTo(BigDecimal.ZERO) < 0){
            throw new JcException(999,"该学员课时包课时不足");
        }
        lessonMapper.lessonSign(lessonId, studentId);
        coursePackageEntity.setLeftClassHour(subtract);
        coursePackageEntity.setConsumedClassHour(add);
        lessonMapper.updateCoursePackage(coursePackageEntity);
        this.sengSms(studentId, lesson);

    }


    public String sengSms(Integer studentId, LessonEntity lessonEntity) throws ParseException {

        StudentEntity student= studentMapper.getStudentById(studentId);
        //校验相关信息，发送短信验证
        String startDate = lessonEntity.getStartDate();
        String endDate = lessonEntity.getEndDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse(startDate);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM月dd日");
        String format = simpleDateFormat1.format(parse);

        //转化星期几
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(parse);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        String format1 = simpleDateFormat2.format(simpleDateFormat2.parse(startDate.split(" ")[1]));
        String format2 = simpleDateFormat2.format(simpleDateFormat2.parse(endDate.split(" ")[1]));

        String msg = lessonEntity.getName()+ "于" + format + " (" + weekDays[w] + ") " + format1 + " - " +  format2;
        System.out.println("msg = " + msg);
        //1.封装数据  参数1.code值， 参数2. 分钟数
        String[] pararms = {student.getName(),msg};

        SmsSingleSender smsSingleSender = new SmsSingleSender(Integer.parseInt(SmsUtils.APPID), SmsUtils.APPKEY);

        SmsSingleSenderResult result = null;

        String ext = null;

        try {

            result = smsSingleSender.sendWithParam("86",  student.getGuarderPhone(), Integer.parseInt(SmsUtils.CODETEMPLEID), pararms, SmsUtils.SIGN, "", "");
            ext = result.ext;
            System.out.println("result = " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result.result != 0){
            throw new JcException(999, "签到失败,发送通知短信不成功");
        }
        return ext;
    }


    @Override
    public LessonEntity getLesson(Integer lessonId) {
        LessonEntity lesson = lessonMapper.getLesson(lessonId);

        return lesson;
    }

    @Override
    public List<StudentLessonEntity> getLessonStudentList(Integer lessonId) {
        List<StudentLessonEntity> lessonStudentList = lessonMapper.getLessonStudentList(lessonId);
        return lessonStudentList;
    }

    @Override
    public void removeLessonStudent(Integer studentId, Integer lessonId) {
        Integer result = lessonMapper.removeLessonStudent(studentId, lessonId);
        if(result == null || result == 0){
            throw new JcException("课程移除学员失败");
        }
    }

    @Override
    public List<StudentEntity> getLessonAbsentStudentList(Integer lessonId, Integer classId) {
        List<StudentEntity> lessonAbsentStudentList = lessonMapper.getLessonAbsentStudentList(lessonId, classId);
        return lessonAbsentStudentList;
    }
}
