package com.hc.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hc.common.exception.JcException;
import com.hc.common.utils.JWTUtil;
import com.hc.modules.member.entity.MemberEntity;
import com.hc.modules.member.mapper.MemberMapper;
import com.hc.modules.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private JWTUtil jwtUtil;


    @Override
    public List<MemberEntity> getMemberList(String token) {
        String username = jwtUtil.getUsername(token);
        List<MemberEntity> memberList = memberMapper.getMemberList(username);

        return memberList;
    }

    @Override
    public void insertMember(MemberEntity memberEntity) {
        Integer result = memberMapper.insert(memberEntity);
        if(result == null || result ==0){
            throw new JcException("新增学员失败");
        }

    }


}
