package com.poetryAnthology.service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poetryAnthology.dao.PoemDao;
import com.poetryAnthology.domain.Poem;

public class PoemService {
	private PoemDao poemDao;
	
	public Set<String> getAuthorList(String dirPath) {
		Set<String> authorList=new HashSet<String>();
		File dir = new File(dirPath);
		  File[] files = dir.listFiles();
		  for(int i=0;i<files.length;i++){
			  String path=files[i].getName();
			  Poem poem=poemDao.getPoemsByPath(path);
			  authorList.add(poem.getWorksAuthor()); 
		  }	
		return authorList;
	}

}
