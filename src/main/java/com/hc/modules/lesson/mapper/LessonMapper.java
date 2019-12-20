package com.hc.modules.lesson.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.lesson.entity.LessonEntity;
import com.hc.modules.student.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface LessonMapper extends BaseMapper<LessonEntity> {

    public List<LessonEntity> getClassLessonList(@Param(value = "cid") Integer cid);


    public Integer insertLesson(LessonEntity lessonEntity);

    public Integer insertLessonStudents(@Param(value = "coid")Integer coid,
                                        @Param(value = "list") List<Integer> list);



}
