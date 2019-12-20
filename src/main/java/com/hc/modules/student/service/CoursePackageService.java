package com.hc.modules.student.service;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.hc.modules.student.entity.CoursePackageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 课时包表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-20 17:32:45
 */
public interface CoursePackageService extends IService<CoursePackageEntity> {

    public List<CoursePackageEntity> coursePackageList(Integer studentId);

    public void createCoursePackage(CoursePackageEntity coursePackageEntity);

}

