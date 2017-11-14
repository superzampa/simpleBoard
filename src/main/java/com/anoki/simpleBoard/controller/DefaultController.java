package com.anoki.simpleBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anoki.simpleBoard.dao.PostDao;
import com.anoki.simpleBoard.dao.PostsTagsDao;
import com.anoki.simpleBoard.dao.TagDao;
import com.anoki.simpleBoard.dao.UserDao;
import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.models.Tag;
//import com.anoki.simpleBoard.models.User;
import com.anoki.simpleBoard.service.PostService;
import com.anoki.simpleBoard.service.PostsTagsService;
import com.anoki.simpleBoard.service.UserService;
import com.anoki.simpleBoard.util.MyAccessDeniedHandler;

@Controller
public class DefaultController {

	
    @Autowired
	PostService postService;
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;
    @Autowired
    PostsTagsDao postsTagsDao;
    @Autowired
    PostsTagsService postsTagsService;
    @Autowired
    TagDao tagDao;
    //User user;
    
    private static Logger logger = LoggerFactory.getLogger(DefaultController.class); 
	
    @GetMapping("/")
    public String home1() {
        return "posts";
    }

    @GetMapping("/home")
    public String home() {
        return "posts";
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
        for(Post post : postService.findAll()) {
        	for(Tag tag : post.getlistTag())
        	logger.info(tag.getName());
        }
    return posts;
	}

    @PostMapping(path = "/updatePost", params="action=save")
    public String submit(@ModelAttribute("post") Post post, @RequestParam("tags") String tags, BindingResult result, ModelAndView model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("logged user: " + auth.getName());  	
    	postService.addPost(post);
    	logger.info("tags passati" + tags);
    	postsTagsService.mergeByName(tags);
    	postsTagsService.addTagsComma(post, tags);
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/deletePost")
    public String deletePost(@RequestParam("idPost") Integer idPost) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("post: " + idPost);
    	postService.deletePostByIdPost(idPost);
    	return "redirect:/posts";
    }
   
    
    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}