<%@ page language="java" contentType="text/html;charset=utf-8"  %>
<%@ page import="com.poetryAnthology.lucene.Searcher2"%>
<%@ page import="org.apache.lucene.document.Document"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>诗歌阅读</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="Moleskine Notebook with jQuery Booklet" />
        <meta name="keywords" content="jquery, book, flip, pages, moleskine, booklet, plugin, css3 "/>
		<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon"/>

		<script type="text/javascript" src="reader/js/jquery-1.4.4.min.js"></script>
		<script src="reader/booklet/jquery.easing.1.3.js" type="text/javascript"></script>
		<script src="reader/booklet/jquery.booklet.1.1.0.min.js" type="text/javascript"></script>
		<link href="reader/booklet/jquery.booklet.1.1.0.css" type="text/css" rel="stylesheet" media="screen" />
		<link rel="stylesheet" href="reader/css/style.css" type="text/css" media="screen"/>
    
    	<!-- Edit Below -->
		<script type="text/javascript" src="menu/js/jquery.square_menu.js"></script>
		<script type="text/javascript" src="menu/js/art-template.js"></script>
		<link rel="stylesheet" type="text/css" href="menu/css/square_menu.css" />
		<link rel="stylesheet" type="text/css" href="menu/css/style.css" />
		<link rel="stylesheet" type="text/css" href="menu/css/elusive-webfont.css" />
		<link rel="stylesheet" type="text/css" href="menu/css/ul_menu.css" />
    </head>
    <body>    
    	<%
        		String key=request.getParameter("key");  
        	        Searcher2 search=new Searcher2();
        	        ArrayList<Document> docs=null;
        	        if(key.length()>0)
        	        {
        	        	docs=search.service(key);
        	        }
        	%>
    	<div class="sidemenu">
			<nav class="left"> 
			<h1><center>目录</center></h1>
			<div class="bd">
		    <ul class="chapter-list">
		    	<% for(int i=0; i<docs.size();i++) { 
					String title=docs.get(i).get("title");
					String author=docs.get(i).get("author");
					
				%>
					<li><a href="#pageid=<%= i+1 %>" data-charindex="<%= i+1 %>"><%=title+"("+author+")"%></a></li>
				<% } %>
		    </ul>
			</div>
			</nav>	
		</div>	
		<script type="text/javascript">
		  $(document).ready( function() {
		    $(".sidemenu").square_menu();
		  });
		  $(".bd .chapter-list li").mouseover(function () {
		   $(this).addClass("cur");
        });
         $(".bd .chapter-list li").mouseout(function () {
		   $(this).removeClass("cur");   
        });
  	    </script>

		<div class="book_wrapper" >
			<a id="next_page_button"></a>
			<a id="prev_page_button"></a>
			<!--  <div id="loading" class="loading">Loading poems...</div>-->
			<div id="mybook" style="display:none;">
				<div class="b-load">
				<% 
				for(int i=0;i<docs.size();i++)
				{			
					String title=docs.get(i).get("title");
					String author=docs.get(i).get("author");
					//找寻图片
					String imgPath="";
					String fileNo=docs.get(i).get("fileNo");
					//判断目录是否存在
					String fileDir="F://myeclipseworkspace//chapter2//WebRoot//reader//img//"+fileNo;
					File f=new File(fileDir);
					//System.out.println(f.getAbsolutePath());
					if(f.exists())
					{
						 //System.out.println("in");
						 File[] fs = f.listFiles();
						 //System.out.println(fs[0].getAbsolutePath());
						 imgPath=fs[0].getAbsolutePath();
						 //System.out.println("imgPath:"+imgPath);
					}
					//结束找寻图片，有图片，返回imgPath,否则返回imgPath=""
					String poemText=docs.get(i).get("poemText");
					int poemTextNum=0;
					String plainPoemText="";
					for(int j=0;j<poemText.length();j++)
					{
						String c=poemText.substring(j,j+1);
						if(c!=" "&&c!="\n")
						{
							poemTextNum++;	
							plainPoemText+=c;
						}		
					}
					/*
					唐诗文本的排版
					1.
					2.
					3.
					4.长诗
					*/
					String[] poemTextLines=poemText.split("\n");
					String appreciate=docs.get(i).get("appreciate");
					String[] appreciateLines=appreciate.split("\n");
					
				%>
				<div class="page-content">
					<div class="page">
						<h1><%=title+"("+author+")"+fileNo%></h1>
						<%
						if(poemTextLines.length>25)
						{
						%>
							<p><%=plainPoemText%></p>
							<% 
							if(plainPoemText.length()/45<=12&&imgPath!="") 
							{
								String str="reader\\img"+imgPath.split("img")[1];
								str=str.replaceAll("\\\\","//");
							%>
								<img src=<%=str%> alt="pic"/>
							<%} 
						}
						else
						{
							int lineNum=0;
							for(int j=0;j<poemTextLines.length;j++)
							{
								String line=poemTextLines[j];
								if(line.length()==0)
									continue;
								lineNum++;
						%>
							<p><%=line%></p>
						<%
							} 
							if(lineNum<=10&&imgPath!="") 
							{
								System.out.println("imgPath1:"+imgPath);
								String str="reader\\img"+imgPath.split("img")[1];
								System.out.println("str:"+str);
								str=str.replaceAll("\\\\","//");
							%>
								<img src=<%=str%> alt="pic"/>
							<%} 
						}
						%>
					</div>	
				</div>
				<%
				}
				 %>
				</div>
			</div>
		</div>
        <!-- The JavaScript -->
		<script type="text/javascript" src="reader/js/book.js"></script>
    </body>
</html>