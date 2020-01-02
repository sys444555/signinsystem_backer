package com.hc.modules.student.controller;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.student.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hc.modules.student.entity.CoursePackageEntity;
import com.hc.modules.student.service.CoursePackageService;




/**
 * 课时包表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-20 17:32:45
 */
@RestController

public class CoursePackageController {
    @Autowired
    private CoursePackageService coursePackageService;

    /**
     * 列表
     */
    @RequestMapping(value = "/student/coursePackage/list/{studentId}", method = RequestMethod.GET)
    public ResponseUtil list(@PathVariable(value = "studentId") Integer studentId, Integer classId){

        Map<String, Object> coursePackageEntities = coursePackageService.getCoursePackageList(studentId, classId);
        return ResponseUtil.success(coursePackageEntities);
    }

    /**
     * 新增课时包
     */
    @RequestMapping(value = "/student/coursePackage/create", method = RequestMethod.POST)
    public ResponseUtil createCoursePackage(CoursePackageEntity coursePackageEntity){
        coursePackageService.createCoursePackage(coursePackageEntity);
        return ResponseUtil.success();
    }

    /**
     * 设置课时包
     */
    @RequestMapping(value = "student/coursePackage/set", method = RequestMethod.POST)
    public ResponseUtil setCoursePackage(Integer studentId, Integer classId, Integer cpid){

        coursePackageService.setCoursePackage(studentId, classId, cpid);
        return ResponseUtil.success();
    }

    /**
     * 移除课时包
     */
    @RequestMapping(value = "/coursePackage/remove/{coursePackageId}", method = RequestMethod.POST)
    public ResponseUtil removeCoursePackageById(@PathVariable(value = "coursePackageId") Integer coursePackageId){
        coursePackageService.removeCoursePackageById(coursePackageId);
        return ResponseUtil.success();
    }

    /**
     * 更新课时包
     */
    @RequestMapping(value = "/coursePackage/update", method = RequestMethod.POST)
    public ResponseUtil updateCoursePackage(CoursePackageEntity coursePackageEntity){
        coursePackageService.updateCoursePackage(coursePackageEntity);
        return ResponseUtil.success();

    }

    /**
     * 查看课时包
     */
    @RequestMapping(value = "/coursePackage/get/{coursePackageId}", method = RequestMethod.GET)
    public ResponseUtil getCoursePackageById(@PathVariable(value = "coursePackageId") Integer coursePackageId){
        CoursePackageEntity coursePackageEntity = coursePackageService.getCoursePackageById(coursePackageId);
        return ResponseUtil.success(coursePackageEntity);
    }

}
