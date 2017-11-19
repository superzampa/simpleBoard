package com.anoki.simpleBoard.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.dao.PostDao;
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
		//split and trim and remove duplicates
		String tagsFixed = Arrays.stream(tags.split(",")).map(String::trim).distinct().collect(Collectors.joining(","));
		String[] tagsArray = tagsFixed.split(",");
		for (String tag : tagsArray) {
			//insert tag only if not null and not found
			if (((post.getListTag() == null)  || 
					(!post.getListTag().stream().filter(o -> o.getName().equals(tag)).findFirst().isPresent() 
							)) && !(tag == null) && !(tag.equals("")))
			{
			postsTagsDao.addPostTag(post.getIdPost(), tagDao.findByName(tag).getIdTag());
			}
		}
	}
	
	public void mergeByName(String tags) {
		String[] tagsArray = tags.split(",");
		for (String tag : tagsArray) {
			if(tagDao.findByName(tag.trim()) == null && !(tag == null)) 
				tagDao.addTag(tag.trim());
		}
	}
}