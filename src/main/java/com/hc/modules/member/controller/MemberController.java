package com.hc.modules.member.controller;


import com.hc.modules.member.entity.MemberEntity;
import com.hc.modules.member.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    @Resource
    private MemberService memberService;



}
