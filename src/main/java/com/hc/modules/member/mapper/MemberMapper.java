package com.hc.modules.member.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hc.modules.member.entity.MemberEntity;

public interface MemberMapper extends BaseMapper<MemberEntity> {

    public Integer insertMember(MemberEntity memberEntity);
}
