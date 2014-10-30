<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/> 
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
		<title>唐诗诗集</title>
		<!--header-->
		<link rel="stylesheet" href="cssfile/bookshelf.css" type="text/css" charset="utf-8" />
		<link rel="stylesheet" href="cssfile/master.css" type="text/css" charset="utf-8" />
		<link rel="stylesheet" type="text/css" href="3DBookShowcase/css/default.css" />
		<link rel="stylesheet" type="text/css" href="3DBookShowcase/css/component.css" />
		<script src="3DBookShowcase/js/modernizr.custom.js"></script>
        <link rel="stylesheet" type="text/css" href="sweetPages/styles.css"/>   
	    <script type="text/javascript">
		    var redirect = function (o) {
			var e = window.event|| redirect.caller.arguments[0];
			if ((e.keyCode || e.which) == 13) {
				var input=$(".inp #inp-query").val();
				if(input=="")
					alert("请输入关键词");
				else
				 	window.location.href= "reader.html?key="+input;
			}
		};
		</script>
     </head>  
	<body>
		<div id="db-nav-book" class="nav">
			<div class="nav-wrap">
				<div class="nav-primary">
					<div class="nav-search">
						<fieldset>
							<legend>
								搜索：
							</legend>
							<label style="display: none;" for="inp-query">
								诗名、诗人、主题词
							</label>
							<div class="inp">
								<input dir="ltr" autocomplete="off" placeholder="诗名、诗人、主题词"
									id="inp-query" name="search_text" size="22" maxlength="60"
									value=""  onkeydown="redirect(this)">
							</div>
							<div class="inp-btn">
								<input value="搜索" type="button" />
							</div>
							<input name="cat" value="1001" type="hidden">
						</fieldset>
					</div>
				</div>
			</div>
		</div>
		<!--bookshelf-->
		<div class="container">
			<div class="main">
				<ul id="bk-list" class="bk-list clearfix">
					<li>
						<div class="bk-book book-1 bk-bookdefault">
							<div class="bk-front">
								<div class="bk-cover">
									<h2>
										<span>采菊东篱下</span>
									</h2>
								</div>
								<div class="bk-cover-back"></div>
							</div>
							<div class="bk-page">
								<div class="bk-content"></div>						
							</div>
							<div class="bk-back"></div>
							<div class="bk-right"></div>
							<div class="bk-left"></div>
							<div class="bk-top"></div>
							<div class="bk-bottom"></div>
						</div>
						<div class="bk-info">
							<button class="bk-bookview" onclick="window.location.href= 'reader.html?key=山 水 田园'">
								阅读
							</button>
						</div>
					</li>
					<li>
						<div class="bk-book book-2 bk-bookdefault">
							<div class="bk-front">
								<div class="bk-cover">
									<h2>
										<span>月是故乡明</span>
									</h2>
								</div>
								<div class="bk-cover-back"></div>
							</div>
							<div class="bk-page">
								<div class="bk-content">
								
								</div>
								<div class="bk-content">
									
								</div>
								<div class="bk-content bk-content-current">

								</div>
							</div>
							<div class="bk-back"></div>
							<div class="bk-right"></div>
							<div class="bk-left"></div>
							<div class="bk-top"></div>
							<div class="bk-bottom"></div>
						</div>
						<div class="bk-info">
							<button class="bk-bookview" onclick="window.location.href= 'reader.html?key=思乡 月'">
								阅读
							</button>
						</div>
					</li>
					<li>
						<div class="bk-book book-3 bk-bookdefault">
							<div class="bk-front">
								<div class="bk-cover">
									<h2>
										<span>杜甫诗集</span>
									</h2>
								</div>
								<div class="bk-cover-back"></div>
							</div>
							<div class="bk-page">
								<div class="bk-content bk-content-current">
								</div>
								<div class="bk-content">
								</div>
								<div class="bk-content">
								</div>
							</div>
							<div class="bk-back">
								<img src="3DBookShowcase/images/3.png" alt="cat" />
							</div>
							<div class="bk-right"></div>
							<div class="bk-left">	
							</div>
							<div class="bk-top"></div>
							<div class="bk-bottom"></div>
						</div>
						<div class="bk-info">
							<button class="bk-bookview" onclick="window.location.href= 'reader.html?key=杜甫'">
								阅读
							</button>
						</div>
					</li>
					<li>
						<div class="bk-book book-4 bk-bookdefault">
							<div class="bk-front">
								<div class="bk-cover">
								<h2>
								<span>大漠孤烟直</span>
								</h2>
								</div>
								<div class="bk-cover-back"></div>
							</div>
							<div class="bk-page">
								<div class="bk-content bk-content-current">
								</div>
								<div class="bk-content">
								</div>
							</div>
							<div class="bk-back">
							</div>
							<div class="bk-right"></div>
							<div class="bk-left"></div>
							<div class="bk-top"></div>
							<div class="bk-bottom"></div>
						</div>
						<div class="bk-info">
							<button class="bk-bookview" onclick="window.location.href= 'reader.html?key=军'">
								阅读
							</button>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- /container -->

<script src="3DBookShowcase/js/jquery.min.js"></script>
<script>
$(function() {
	$("div.inp-btn").click(function () {
		  //alert($(".inp #inp-query").val());
		  //location.href = "reader.jsp?key=" + $(".inp #inp-query").val(); 
		var input=$(".inp #inp-query").val();
		if(input=="")
			alert("请输入关键词");
		else
		 	window.location.href= "reader.html?key="+input;
		 //window.location.href= "reader.html?key="+$(".inp #inp-query").val(); 
        });
});

</script>
<script type="text/javascript" src="sweetPages/jquery.min.js"></script>
<script type="text/javascript" src="sweetPages/script.js"></script>
</body>
</html>
