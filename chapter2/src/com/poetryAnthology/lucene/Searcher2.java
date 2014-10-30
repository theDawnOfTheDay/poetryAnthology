package com.poetryAnthology.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poetryAnthology.service.InputKeywordsService;
public class Searcher2 {
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
		String indexDir="../../chapter2/index/tangPoemIndex"; //tangPoemOldIndex";
		//System.out.println("in1");
		String q=query;
		return search(indexDir,q);	
	}
	public static ArrayList<Document> search(String indexDir,String inputQuery) throws IOException,ParseException
	{
		String[] q=new InputKeywordsService().getAuthorQuery(inputQuery);
		Directory dir=FSDirectory.open(new File(indexDir));
		IndexSearcher is=new IndexSearcher(DirectoryReader.open(dir));
		TopDocs hits;
		//出现人名必须是在author中搜索，将其搜索结果在多域搜索
		System.out.println("q0"+q[0]);
		System.out.println("q1"+q[1]);
		if(q[0]!=""&&q[1]!="")
		{
			/*
			QueryParser parser1=new QueryParser(Version.LUCENE_47,
					"author",new StandardAnalyzer(Version.LUCENE_47));	 
			parser1.setDefaultOperator(QueryParser.Operator.AND);
	        Query query1 = parser1.parse(q[0]);  
	        Filter authorFilter = new QueryWrapperFilter(query1);
	        CachingWrapperFilter filter = new CachingWrapperFilter(authorFilter);
	 
			String[] fields = {"keyword","title","poemText","appreciate"};
			QueryParser parser2=new MultiFieldQueryParser(Version.LUCENE_47,
					fields,new StandardAnalyzer(Version.LUCENE_47));
			parser2.setDefaultOperator(QueryParser.Operator.AND);
			Query query2=parser2.parse(q[1]);
			hits=is.search(query2, filter,MAX_VALUE);
			*/
			//组合
			String[] q0=q[0].split("\\s+");
			BooleanQuery m_BooleanQuery = new BooleanQuery();
		    BooleanQuery bq = new BooleanQuery();
			for(int i=0;i<q0.length;i++)
			{
				QueryParser parser=new QueryParser(Version.LUCENE_47,
						"author",new StandardAnalyzer(Version.LUCENE_47));	 
				parser.setDefaultOperator(QueryParser.Operator.AND);
		        Query query = parser.parse(q0[i]);  
		        bq.add(query,BooleanClause.Occur.SHOULD);
			}
			m_BooleanQuery.add(bq, BooleanClause.Occur.MUST);
			
	        Filter authorFilter = new QueryWrapperFilter(m_BooleanQuery);
	        CachingWrapperFilter filter = new CachingWrapperFilter(authorFilter);
	        
			String[] fields = {"keyword","title","poemText","appreciate"};
			QueryParser parser2=new MultiFieldQueryParser(Version.LUCENE_47,
					fields,new StandardAnalyzer(Version.LUCENE_47));
			parser2.setDefaultOperator(QueryParser.Operator.AND);
			Query query2=parser2.parse(q[1]);
			hits=is.search(query2, filter,MAX_VALUE);
			
		}
		else if(q[0]!=""&&q[1]=="")
		{
			String[] q0=q[0].split("\\s+");
			BooleanQuery m_BooleanQuery = new BooleanQuery();
		    BooleanQuery bq = new BooleanQuery();
			for(int i=0;i<q0.length;i++)
			{
				QueryParser parser=new QueryParser(Version.LUCENE_47,
						"author",new StandardAnalyzer(Version.LUCENE_47));	 
				parser.setDefaultOperator(QueryParser.Operator.AND);
		        Query query = parser.parse(q0[i]);  
		        bq.add(query,BooleanClause.Occur.SHOULD);
			}
			m_BooleanQuery.add(bq, BooleanClause.Occur.MUST);
			hits=is.search(m_BooleanQuery,MAX_VALUE);//以TopDoc对象的形式返回搜索结果集
			/*
			QueryParser parser=new QueryParser(Version.LUCENE_47,
					"author",new StandardAnalyzer(Version.LUCENE_47));
			parser.setDefaultOperator(QueryParser.Operator.AND);
			Query query=parser.parse(q[0]);
			hits=is.search(query,MAX_VALUE);//以TopDoc对象的形式返回搜索结果集
			*/
			
		}
		else
		{
			String[] fields = {"keyword","title","poemText","appreciate"};
			QueryParser parser=new MultiFieldQueryParser(Version.LUCENE_47,
					fields,new StandardAnalyzer(Version.LUCENE_47));
			parser.setDefaultOperator(QueryParser.Operator.AND);
			Query query=parser.parse(q[1]);
			hits=is.search(query,MAX_VALUE);
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
			//System.out.println();
			//System.out.println(doc.get("title"));
			//System.out.println(doc.get("appreciate"));
			//System.out.println(doc.get("keyword"));
		}
		return documents;
	} 
}
