package com.hc.modules.classStudent.mapper;

import com.hc.modules.classStudent.entity.ClassEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.classStudent.entity.ClassListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级表
 *
 * @author fenghuang
 * @email
 * @date 2019-12-17 20:56:21
 */
public interface ClassMapper extends BaseMapper<ClassEntity> {

    public List<ClassListVO> getClassList(@Param(value = "username") String username);

}
