package com.anoki.simpleBoard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.models.Tag;
//import com.anoki.simpleBoard.models.User;
import com.anoki.simpleBoard.service.PostService;
import com.anoki.simpleBoard.service.PostsTagsService;
import com.anoki.simpleBoard.service.TagService;
import com.anoki.simpleBoard.service.UserService;

@Controller
public class DefaultController {

	@Autowired
	PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostsTagsService postsTagsService;
    @Autowired
    TagService tagService;

    
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
    	posts.addObject("tagList", tagService.findAll());
    return posts;
	}

    @PostMapping(path = "/updatePost", params="action=save")
    public String submit(@ModelAttribute("post") Post post, @RequestParam("tags") String tags, BindingResult result, ModelAndView model) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("logged user: " + auth.getName());  	
    	postService.addPost(post);
    	logger.info("tags passati: " + tags);
    	postsTagsService.mergeByName(tags);
    	postsTagsService.addTagsComma(post, tags);
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/deletePost")
    public String deletePost(@RequestParam("idPost") Integer idPost) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("idPost: " + idPost);
    	postService.deletePostByIdPost(idPost);
    	//deleting orphans tags that do not belong to any post
    	tagService.deleteOrphans();
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/posts")
    public ModelAndView seatchPost(@RequestParam("idUser") Integer idUser, @RequestParam("text") String text, @RequestParam("idTag") Integer idTag) {
    	ModelAndView posts = new ModelAndView("posts");
    	//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("idUser: " + idUser + "text: " + text + "tagId :" + idTag);
        posts.addObject("postList", postService.searchPosts(idUser, text, idTag));
    	posts.addObject("post", new Post());
    	posts.addObject("userList", userService.findAll()); 
    	posts.addObject("tagList", tagService.findAll());
    	
        //return "redirect:/posts";
    	return posts;
    }
    
    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}