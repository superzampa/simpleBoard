package com.anoki.simpleBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.dao.PostsTagsDao;
import com.anoki.simpleBoard.dao.TagDao;
import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.models.Tag;

@Service("PostsTagsService")
public class PostsTagsService{
	@Autowired
	private PostsTagsDao postsTagsDao;
	@Autowired
	TagDao tagDao;
	
	public List<Tag> getAllTags(Post post){
		return postsTagsDao.findByIdPost(post.getIdPost());
	}
	
	public void addTagsComma(Post post, String tags) {
		String[] tagsArray = tags.split(",");
		for (String tag : tagsArray) {
			postsTagsDao.addPostTag(post.getIdPost(), tagDao.findByName(tag.trim()).getTagId());
		}
	}
	
	public void mergeByName(String tags) {
		String[] tagsArray = tags.split(",");
		for (String tag : tagsArray) {
			if(tagDao.findByName(tag) == null) tagDao.addTag(tag.trim());
		}
	}
}