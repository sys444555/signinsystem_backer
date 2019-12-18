package com.hc.modules.teacher.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.teacher.entity.ClassEntity;
import com.hc.modules.teacher.entity.TeacherEntity;
import com.hc.modules.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
@RestController

public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询班级列表
     */
    @RequestMapping(value = "/teacher/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(){
        List<TeacherEntity> teacherList = teacherService.getTeacherList();
        return ResponseUtil.success(teacherList);
    }


    /**
     * 查询班级列表
     */
    @RequestMapping(value = "/class/list",method = RequestMethod.GET)
    public ResponseUtil getClassList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<ClassEntity> classList = teacherService.getClassList(token);
        PageInfo<ClassEntity> pageInfo = new PageInfo<>(classList);
        return ResponseUtil.success(pageInfo);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/class/create", method = RequestMethod.POST)
    public ResponseUtil createClass(ClassEntity classEntity){
        teacherService.insertClass(classEntity);
        return ResponseUtil.success();
    }

    /**
     * 修改班状态
     */
    @RequestMapping(value = "/class/update/{status}/{id}", method = RequestMethod.POST)
    public ResponseUtil updateClassStatus(@PathVariable(value = "id") Integer id, @PathVariable(value = "status") Integer status){
        teacherService.updateClassStatus(status, id);
        return ResponseUtil.success();
    }

}
