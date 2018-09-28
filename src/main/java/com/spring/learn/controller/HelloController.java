package com.spring.learn.controller;


import com.spring.learn.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author yu
 * @date 2016/10/31
 */
@RestController
@RequestMapping(value = "/index")
public class HelloController {

    @Autowired
    private Blog blog;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(@RequestParam(required = true) String name) {
        System.out.print("say hello to " + name);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return blog.getName() + blog.getAuthor();
    }
}
