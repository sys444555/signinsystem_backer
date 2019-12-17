package com.hc.modules.member.service;

import com.hc.modules.member.entity.MemberEntity;

import java.util.List;

public interface MemberService {

    public List<MemberEntity> getMemberList();

    public void insertMember(MemberEntity memberEntity);

}
