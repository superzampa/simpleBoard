package com.anoki.simpleBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anoki.simpleBoard.models.PostDao;

@Controller
public class DefaultController {

	
    @Autowired
	PostDao postDao;
	
    @GetMapping("/")
    public String home1() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/posts")
	public ModelAndView getPosts() {
    	ModelAndView posts = new ModelAndView("posts");
    	posts.addObject("postList", postDao.findAll());
    	
    return posts;
	}

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}