package com.poetryAnthology.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class InputKeywordsService {
	//private PoemService poemService;
	
	public String[] getAuthorQuery(String query)
	{
		System.out.println(query);
		String [] q=new String[2];
		String q1="";
		String q2="";
		Set<String> authorList=getAuthorList();//poemService.getAuthorList("../../chapter2/resources/poemXml"); 
		int cnt=1;
		for(String str:authorList)
		{
			System.out.println("第"+cnt+"个作者是："+str);
			cnt++;
		}
		System.out.println(authorList.size());
		String[] wds=query.split("\\s+");
		for(int i=0;i<wds.length;i++)
		{
	
			if(authorList.contains(wds[i]))
			{
				if(q1=="")
					q1+=wds[i];
				else
					q1+=" "+wds[i];
			}
			else
			{
				if(q2=="")
					q2+=wds[i];
				else
					q2+=" "+wds[i];
			}
		}
		q[0]=q1;
		q[1]=q2;
		return q;
		
	}
	public Set<String> getAuthorList()
	{
		Set<String> authorList=new HashSet<String>();
		String path="../../chapter2/resources/authors.csv";
		try
		{
			BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
			// 读取直到最后一行
			String line = "";
			while ((line = br.readLine()) != null) {
				line=line.replaceAll("\n", "");
				authorList.add(line);
			}	
		} catch (FileNotFoundException e) {
			// 捕获File对象生成时的异常
			e.printStackTrace();
		} catch (IOException e) {
			// 捕获BufferedReader对象关闭时的异常
			e.printStackTrace();
		} 
		return authorList;
	}
}
