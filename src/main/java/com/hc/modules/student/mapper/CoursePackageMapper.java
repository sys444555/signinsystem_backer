package com.hc.modules.student.mapper;

import com.hc.modules.student.entity.CoursePackageEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.student.entity.StudentEntity;
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

    public List<CoursePackageEntity> getCoursePackageList(@Param(value = "studentId") Integer studentId);

    public Integer createCoursePackage(CoursePackageEntity coursePackageEntity);

    public Integer getCpid(@Param(value = "studentId") Integer studentId,
                           @Param(value = "classId") Integer classId);

    public Integer setCoursePackage(@Param(value = "studentId") Integer studentId,
                     @Param(value = "classId") Integer classId,
                     @Param(value = "cpid") Integer cpid);

    public Integer updateCoursePackage(@Param(value = "studentId") Integer studentId,
                                       @Param(value = "classId") Integer classId,
                                       @Param(value = "cpid") Integer cpid);

    public List<StudentEntity> getStudentCoursePackageList(@Param(value = "studentId") Integer studentId);
}
