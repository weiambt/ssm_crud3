package top.ambtwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    2022/2/18 11:26
    @author 张渭
    Project Name:ssm_crud
     
    theme:
*/
@Controller
public class TestController {

    @RequestMapping("/t1/{id}")
    public void t1(@PathVariable int id){
        System.out.println(id);
    }

    @RequestMapping("/t2/{id}")
    public void t2(int id){
        System.out.println(id);
    }
}
