package com.ischoolbar.programmer.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ischoolbar.programmer.util.Md5Class;

@Controller
public class Md5Controller {
 
    @RequestMapping(value = "/md5/md5.do")
    public HashMap<?, ?> result(String md5) {
        String old ="123456";
        //ʹ�ù��������123456
        String s1 = Md5Class.string2MD5(old);
        //�Ƚ�ǰ�˴�������
        System.out.println(md5.equals(s1));
        return null;
    }
}
