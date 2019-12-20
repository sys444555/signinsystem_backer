package com.hc.modules.student.mapper;

import com.hc.modules.student.entity.CoursePackageEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课时包表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-20 17:32:45
 */
public interface CoursePackageMapper extends BaseMapper<CoursePackageEntity> {

    public List<CoursePackageEntity> coursePackageList(@Param(value = "studentId") Integer studentId);

    public Integer createCoursePackage(CoursePackageEntity coursePackageEntity);

}
