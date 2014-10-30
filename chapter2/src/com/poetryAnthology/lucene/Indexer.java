package com.poetryAnthology.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Indexer {
	public static void main(String[] args) throws Exception{
		String indexDir ="index\\tangPoemIndex";
		String dataDir ="resources\\tangPoem";
		
		long start=System.currentTimeMillis();
		Indexer indexer=new Indexer(indexDir);
		int numIndexed;
		try{
			numIndexed =indexer.index(dataDir, new XmlFilesFilter());
		}finally{
			indexer.close();
		}
		long end=System.currentTimeMillis();
		System.out.println("Indexing "+numIndexed+" files took "+(end - start)+"milliseconds");
		
	}
	private IndexWriter writer;
	public Indexer(String indexDir) throws IOException{
		Directory dir =FSDirectory.open(new File(indexDir));
		writer=new IndexWriter(
				dir,
				new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47))
				);
	}
	public void close() throws IOException{
		writer.close();
	}
	public int index(String dataDir,FileFilter filter) throws Exception{
		File[] files=new File(dataDir).listFiles();
		for (File f:files){
			if(!f.isDirectory()&&!f.isHidden()&&f.exists()&&f.canRead()&&(filter==null||filter.accept(f))){
				indexFile(f);
			}
		}	
		return writer.numDocs();
	}
	private static class XmlFilesFilter implements FileFilter{
		public boolean accept(File path){
			return path.getName().toLowerCase().endsWith(".xml");
		}
	}
	protected Document getDocument(File f){
		Document doc =new Document();
		System.out.println(f.getName().length());
		
		String fileNo=f.getName().toString().substring(0, f.getName().length()-4);
		System.out.println(fileNo);
		//解析xml
		try { 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Element works = (Element)db.parse(f).getElementsByTagName("works").item(0);
			String title = works.getElementsByTagName("title").item(0).getTextContent();
			String author = works.getElementsByTagName("title").item(0).getAttributes().item(0).getTextContent();
			String poemText = works.getElementsByTagName("text").item(0).getTextContent();
			

			doc.add(new Field("title",title,Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("author",author,Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("poemText",poemText,Field.Store.YES,Field.Index.ANALYZED));
			doc.add(new Field("fileNo",fileNo,Field.Store.YES,Field.Index.ANALYZED));
			
			NodeList apps=works.getElementsByTagName("appreciate");
			for (int i = 0; i < apps.getLength(); ++i)
			{
				String appreciate=apps.item(i).getTextContent();
				doc.add(new Field("appreciate",appreciate,Field.Store.YES,Field.Index.ANALYZED));
			}
			NodeList keywords=works.getElementsByTagName("keyword");
			for (int i = 0; i < keywords.getLength(); ++i)
			{
				String appreciate=keywords.item(i).getTextContent();
				doc.add(new Field("keyword",appreciate,Field.Store.YES,Field.Index.ANALYZED));
			}
		}catch (FileNotFoundException e) { 
		System.out.println(e.getMessage()); 
		} catch (ParserConfigurationException e) { 
		System.out.println(e.getMessage()); 
		} catch (SAXException e) { 
		System.out.println(e.getMessage()); 
		} catch (IOException e) { 
		System.out.println(e.getMessage()); 
		} 
		return doc;
	}
	private void indexFile(File f) throws Exception{
		System.out.println("Indexing "+f.getAbsolutePath());
		Document doc=getDocument(f);
		writer.addDocument(doc);
	}
	

}
