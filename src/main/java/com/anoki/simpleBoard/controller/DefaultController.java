package com.anoki.simpleBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anoki.simpleBoard.dao.PostDao;
import com.anoki.simpleBoard.dao.UserDao;
import com.anoki.simpleBoard.models.Post;
//import com.anoki.simpleBoard.models.User;
import com.anoki.simpleBoard.service.PostService;
import com.anoki.simpleBoard.service.UserService;
import com.anoki.simpleBoard.util.MyAccessDeniedHandler;

@Controller
public class DefaultController {

	
    @Autowired
	PostService postService;
    UserDao userDao;
    @Autowired
    UserService userService;
    //User user;
    
    private static Logger logger = LoggerFactory.getLogger(DefaultController.class); 
	
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
    	posts.addObject("userList", userService.findAll());    	
    return posts;
	}

    @PostMapping(path = "/updatePost", params="action=save")
    public String submit(@ModelAttribute("post") Post post, BindingResult result, ModelAndView model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("logged user: " + auth.getName());
    	postService.addPost(post);
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/updatePost", params="action=delete")
    public String deletePost(@ModelAttribute("post") Post post, BindingResult result, ModelAndView model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("logged user: " + auth.getName());
    	postService.deletePost(post);
    	return "redirect:/posts";
    }
   
    
    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}