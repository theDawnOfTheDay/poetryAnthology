package com.poetryAnthology.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.poetryAnthology.domain.Poem;

public class PoemDao {
	public Poem getPoemsByPath(String path) {
		Poem poem=new Poem();
		try {
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();
			Document doc=db.parse(new File(path));
			Element root=doc.getDocumentElement();
			
		    NodeList titles = root.getElementsByTagName("title");
		    Element e = (Element) titles.item(0);
		    Node t = e.getFirstChild();
		    poem.setTitle(t.getNodeValue());
		    poem.setWorksAuthor(e.getAttribute("worksAuthor"));
		    
		    ArrayList<String> appreciate=new ArrayList<String>();
		    NodeList appreciates = root.getElementsByTagName("appreciate");
		    for(int i=0;i<appreciates.getLength();i++)
		    {
		    	Element elem = (Element)appreciates.item(i);
		    	Node node = elem.getFirstChild();
		    	appreciate.add(node.getNodeValue());	
		    }
		    poem.setAppreciate(appreciate);
		    /*
		    ArrayList<String> keyword=new ArrayList<String>();
		    NodeList keywords = root.getElementsByTagName("keyword");
		    for(int i=0;i<keywords.getLength();i++)
		    {
		    	Element elem = (Element)keywords.item(i);
		    	Node node = elem.getFirstChild();
		    	keyword.add(node.getNodeValue());	
		    }
		    poem.setAppreciate(keyword);
		    */
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return poem;
		
	}

}
