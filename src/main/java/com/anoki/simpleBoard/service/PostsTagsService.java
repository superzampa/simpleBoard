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

	private PostsTagsDao postsTagsDao;
	TagDao tagDao;
	
	@Autowired
	public void setPostsTagsDao(PostsTagsDao postsTagsDao) {
		this.postsTagsDao = postsTagsDao;
	}
	
	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	public List<Tag> getAllTags(Post post){
		return postsTagsDao.findByIdPost(post.getIdPost());
	}
	
	public void addTagsComma(Post post, String tags) {
		String[] tagsArray = tags.split(",");
		for (String tag : tagsArray) {
			postsTagsDao.addPostTag(post.getIdPost(), tagDao.findByName(tag.trim()).getIdTag());
		}
	}
	
	public void mergeByName(String tags) {
		String[] tagsArray = tags.split(",");
		for (String tag : tagsArray) {
			if(tagDao.findByName(tag.trim()) == null) 
				tagDao.addTag(tag.trim());
		}
	}
}