package com.hc.modules.member.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hc.common.utils.ResponseUtil;
import com.hc.modules.member.entity.MemberEntity;
import com.hc.modules.member.service.MemberService;


import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    @Resource
    private MemberService memberService;


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseUtil getMemberList(Integer pageNo, Integer pageSize, HttpServletRequest httpRequest){
        String token = httpRequest.getHeader("token");
        PageHelper.startPage(pageNo, pageSize);
        List<MemberEntity> memberList = memberService.getMemberList(token);
        PageInfo<MemberEntity> pageInfo = new PageInfo<>(memberList);
        return ResponseUtil.success(pageInfo);
    }
}
