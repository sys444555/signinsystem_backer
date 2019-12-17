package com.hc.modules.member.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper extends BaseMapper<MemberEntity> {

    public List<MemberEntity> getMemberList(@Param(value = "username") String username);

    public Integer insertMember(MemberEntity memberEntity);
}
