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
        List<MemberEntity> list = memberMapper.selectList(new EntityWrapper<MemberEntity>().eq("username", username));
        List<MemberEntity> memberEntities = null;
        if(list != null && list.size() == 1){
            Integer id = list.get(0).getId();
            memberEntities = memberMapper.selectList(new EntityWrapper<MemberEntity>().eq("user_id", id));
        }else {
            throw new JcException("服务器端异常");
        }

        return memberEntities;
    }

    @Override
    public void insertMember(MemberEntity memberEntity) {
        Integer result = memberMapper.insert(memberEntity);
        if(result == null || result ==0){
            throw new JcException("新增学员失败");
        }

    }


}
