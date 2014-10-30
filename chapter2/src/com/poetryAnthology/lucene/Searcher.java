package com.poetryAnthology.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.CachingWrapperFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	/*
	public static void main(String[] args) throws IllegalArgumentException,IOException,ParseException{
		String indexDir="index\\new";
		String q="怨女";
		
		search(indexDir,q);
	}
	*/
	
	public static final int MAX_VALUE=2147483647;
	public ArrayList<Document> service(String query) throws IOException, ParseException
	{
		String indexDir="../../chapter2/index/"; //tangPoemOldIndex";
		String q=query;
		return search(indexDir,q);	
	}
	public static ArrayList<Document> search(String indexDir,String q) throws IOException,ParseException
	{
		Directory dir=FSDirectory.open(new File(indexDir));
		IndexSearcher is=new IndexSearcher(DirectoryReader.open(dir));
		
		//出现人名必须是在author中搜索，将其搜索结果在多域搜索
		QueryParser parser=new QueryParser(Version.LUCENE_47,
				"author",new StandardAnalyzer(Version.LUCENE_47));	 
		parser.setDefaultOperator(QueryParser.Operator.AND);
        Query query = parser.parse(q);  
        Filter authorFilter = new QueryWrapperFilter(query);
        CachingWrapperFilter filter = new CachingWrapperFilter(authorFilter);
 
		String[] fields = {"keyword","title","poemText","appreciate"};
		QueryParser parser2=new MultiFieldQueryParser(Version.LUCENE_47,
				fields,new StandardAnalyzer(Version.LUCENE_47));
		parser2.setDefaultOperator(QueryParser.Operator.AND);
		Query query2=parser2.parse(q);
		TopDocs hits=
			is.search(query2, filter,MAX_VALUE);
		if(hits.totalHits==0)
		{
			String[] fields2 = {"keyword","title","poemText","appreciate"};
			QueryParser parser3=new MultiFieldQueryParser(Version.LUCENE_47,
					fields2,new StandardAnalyzer(Version.LUCENE_47));
			parser3.setDefaultOperator(QueryParser.Operator.AND);
			Query query3=parser3.parse(q);
			hits=is.search(query3,MAX_VALUE);
		}
		/*
		String[] fields = {"keyword","title","poemText","appreciate"};
		QueryParser parser=new MultiFieldQueryParser(Version.LUCENE_47,
				fields,new StandardAnalyzer(Version.LUCENE_47));
		parser.setDefaultOperator(QueryParser.Operator.AND);
		Query query=parser.parse(q);
		*/
		long start=System.currentTimeMillis();
		//TopDocs hits=is.search(query,MAX_VALUE);//以TopDoc对象的形式返回搜索结果集
		long end=System.currentTimeMillis();
		System.err.println("Found "+hits.totalHits+"document(s) (in "+(end-start)+" milliseconds) that matched query '"+q+"':");
		ArrayList<Document> documents=new ArrayList<Document>();
		for (ScoreDoc scoreDoc :hits.scoreDocs){
			Document doc=is.doc(scoreDoc.doc);
			documents.add(doc);
			System.out.println();
			System.out.println(doc.get("title"));
			System.out.println(doc.get("appreciate"));
			//System.out.println(doc.get("keyword"));
		}
		return documents;
	} 
}
