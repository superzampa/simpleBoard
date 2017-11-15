package com.anoki.simpleBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.dao.TagDao;
import com.anoki.simpleBoard.models.Tag;

@Service("TagService")
public class TagService{
	
	private TagDao tagDao;
	
	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}
	
	public List<Tag> findAll() {
		return tagDao.findAll();
	}	
	
	public void deleteOrphans() {
		tagDao.deleteOrphans();
	}
	
}