package com.anoki.simpleBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anoki.simpleBoard.dao.PostDao;
import com.anoki.simpleBoard.dao.UserDao;
import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.service.PostService;

@Controller
public class DefaultController {

	
    @Autowired
	PostService postService;
    UserDao userDao;
	
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
    
    @GetMapping("/posts")
	public ModelAndView getPosts() {
    	ModelAndView posts = new ModelAndView("posts");
    	posts.addObject("postList", postService.findAll());
    	posts.addObject("post", new Post());
    	//posts.addObject("userList", userDao.findAll());    	
    return posts;
	}

    @PostMapping("/posts")
    public String greetingSubmit(@ModelAttribute(value="post") Post post, BindingResult bindingResult, Model model) {
    	postService.addPost(post);
        return "posts";
    }
    
    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}