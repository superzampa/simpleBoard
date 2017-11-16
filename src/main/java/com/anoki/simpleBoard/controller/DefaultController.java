package com.anoki.simpleBoard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.models.Tag;
import com.anoki.simpleBoard.models.User;
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
    public String root() {
        return "redirect:/posts";
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
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	ModelAndView posts = new ModelAndView("posts");
    	posts.addObject("postList", postService.findAll());
    	posts.addObject("post", new Post(userService.findByUsername(auth.getName())));
    	posts.addObject("userList", userService.findAll()); 
    	posts.addObject("tagList", tagService.findAll());
    	posts.addObject("loggedUser", userService.findByUsername(auth.getName()));
    	return posts;
	}

    @PostMapping(path = "/posts", params="action=save")
    public String save(@RequestParam("idUserNew") Integer idUser, @RequestParam("textNew") String text, @RequestParam("tags") String tags) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //logger.info("logged user: " + auth.getName());  
    	Post post = new Post(userService.findByIdUser(idUser), text);
    	postService.addPost(post);
    	logger.info("tags passati: " + tags);
    	postsTagsService.mergeByName(tags);
    	postsTagsService.addTagsComma(post, tags);
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/deleteHidePost", params="action=delete")
    public String deletePost(@RequestParam("idPost") Integer idPost) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUsername(auth.getName());
        Post postToDelete = postService.findByIdPost(idPost);
        //check that the user have permissions to delete
        if(loggedUser.getUsername().equals(postToDelete.getUser().getUsername())) {
        	postService.deletePostByIdPost(idPost);
        	//deleting orphans tags that do not belong to any post
        	tagService.deleteOrphans();
        }
        else logger.info("user: " + auth.getName() + " tried to delete post " + idPost + " but does not have permissions!");
        
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/deleteHidePost", params="action=hide")
    public String hidePost(@RequestParam("idPost") Integer idPost) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = userService.findByUsername(auth.getName());
        Post postToHide = postService.findByIdPost(idPost);
        //check that the user have permissions to delete
        if (loggedUser.getUserType().equals("ADMIN_ROLE") || loggedUser.getUsername().equals(postToHide.getUser().getUsername())) {
        	postService.hidePost(idPost, loggedUser.getIdUser());
        }
        else logger.info("user: " + auth.getName() + " tried to hide post " + idPost + " but does not have permissions!");
        
    	return "redirect:/posts";
    }
    
    @PostMapping(path = "/posts", params="action=search")
    public ModelAndView seatchPost(@RequestParam("idUser") Integer idUser, @RequestParam("text") String text, @RequestParam("idTag") Integer idTag) {
    	ModelAndView posts = new ModelAndView("posts");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("idUser: " + idUser + " ,text: " + text + " ,tagId :" + idTag);
        posts.addObject("postList", postService.searchPosts(idUser, text, idTag));
    	posts.addObject("post", new Post(userService.findByUsername(auth.getName())));
    	posts.addObject("userList", userService.findAll()); 
    	posts.addObject("tagList", tagService.findAll());
    	posts.addObject("loggedUser", userService.findByUsername(auth.getName()));
    	
        //return "redirect:/posts";
    	return posts;
    }
    
    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

}