package com.hc.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Charles Chan
 * @Date: 2019/3/6 23:23
 * @Version 1.0
 */

@Controller
public class CommonController {

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }

}
