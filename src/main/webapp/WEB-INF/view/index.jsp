<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resource/bootstrap/css/bootstrap.css" rel="stylesheet"> 
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/bootstrap/js/bootstrap.js"></script>
<title>cms游客访问页面</title>
<style type="text/css">
	.menu li{
		font-size:20px;
		text-align:center;
	}
	.menu li:hover{
		background:gray;
	}
	
	.bg {
        background:url(/pic/back1.jpg) no-repeat center;
        /* background-size:contain; */   /*图片自适应*/
      	background-size:cover;   /*让图片覆盖满整个div*/
       
}
</style>
</head>
<body class="row bg" style="width:100%; height: 100%">

	<!-- 导航条 头部 -->
	<nav class="navbar navbar-default">
		<%@include  file="common/top.jsp" %>
		
	</nav>
	
	<!-- <div class="progress">
		<div class="progress-bar progress-bar-striped active"
			role="progressbar" aria-valuenow="45" aria-valuemin="0"
			aria-valuemax="100" style="width: 70%">
			<span class="sr-only">45% Complete</span>
		</div>
	</div> -->

<div class="container-fluid" >
	<div class="container" style="minheight:200px" >
		<div class="row">
			<!-- 左侧菜单 -->
			<div class="col-md-2" style="minheight:200px;margin-top:20px" >
				
					<ul class="list-group menu">
					    <li class="list-group-item active" >热门文章</li>
					    <c:forEach items="${channels }" var="channel" varStatus="index">
					    	<li class="list-group-item" data="/channel?id=${channel.id}"><a></a> ${channel.name}</li>
					    </c:forEach>
					</ul>
			</div>
			
			<!-- 中间的内容 -->
			<div class="col-md-8" style="background:white;minheight:200px" >
				<div>
					<!-- 水平线 -->
					<hr>
				</div>
				
				<div id="myCarousel" class="carousel slide">
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1"></li>
							<li data-target="#myCarousel" data-slide-to="2"></li>
							<li data-target="#myCarousel" data-slide-to="3"></li>
							<li data-target="#myCarousel" data-slide-to="4"></li>
						</ol>   
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner" style="border-radius:25px;">
							<div class="item active">
								<img src="/resource/images/1.jpg"  class="img-circle"  style=" align:center;width:800px; height:400px;" alt="First slide">
							</div>
							<div class="item">
								<img src="/resource/images/2.jpg"  class="img-rounded"  style=" border-radius:24px; align:center;width:800px; height:400px;" alt="Second slide">
							</div>
							<div class="item">
								<img src="/resource/images/3.jpg"  class="img-rounded"  style=" align:center;width:800px; height:400px;" alt="Third slide">
							</div>
							<div class="item">
								<img src="/resource/images/4.jpg"  class="img-rounded"  style=" align:center;width:800px; height:400px;" alt="Four slide">
							</div>
							<div class="item">
								<img src="/resource/images/5.jpg"  class="img-circle"  style=" align:center;width:800px; height:400px;" alt="Five slide">
							</div>
						</div>
						<!-- 轮播（Carousel）导航 -->
						<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
					
					<!-- 放文章的列表 -->
					<div>
						<c:forEach items="${hotList.list }" var="article">
						<div class=row style="padding-bottom:1px">
							 <hr width="88%" style="background-color:#D2691E;border:none;height:1px">
							<div class="col-md-2"style="text-align:right"><img height="80px" width="80px" src="/pic/${article.picture}"></div>
							<div class="col-md-10">
								<a href="javascript:showArticle(${article.id })">${article.title }</a>
								<br><br>
								频道:<a>${article.channel.name }</a> &nbsp;&nbsp;
								分类:<a>${article.category.name }</a>
								<br>
								<br>
								${article.user.username }发布于 <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd"/>
							</div>
						</div>
						</c:forEach>
						
						<div class="row" style="text-align: center">
							<hr width="88%" style="background-color:#D2691E;border:none;height:1px">
							<ul class="pagination">
								    <li><a href="/index?page=${hotList.prePage}&key=${key}">&laquo;</a></li>
								    <c:forEach begin="${hotList.pageNum-2 > 1 ? hotList.pageNum-2:1}" 
									end="${hotList.pageNum+2 > hotList.pages ? hotList.pages:hotList.pageNum+2}" varStatus="index">    		
								    	<c:if test="${hotList.pageNum!=index.index}">
								    		<li><a href="/index?page=${index.index}&key=${key}">${index.index}</a></li>
								    	</c:if>
								    	<c:if test="${hotList.pageNum==index.index}">
								    		<li><a href="/index?page=${index.index}&key=${key}"><strong> ${index.index} </strong> </a></li>
								    	</c:if>
								    	
								    </c:forEach>
								    <li><a href="/index?page=${hotList.nextPage}&key=${key}">&raquo;</a></li>
								</ul>
						</div>
					</div>
					 
			</div>
			
			<!-- 中间的内容结束 -->
			
			<div class="col-md-2" style="minheight:200px" >
			
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">图片文章</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${imgArticles}" var="article" varStatus="index"> 
							<a href="javascript:showArticle(${article.id})">${index.index+1}. ${article.title}</a>
							<br/>
						</c:forEach>
					</div>
				</div>
			
			
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">公告</h3>
					</div>
					<div class="panel-body">
						1.<a href="#">本网站禁止发布不良信息</a>
						<br/>
						2.<a href="#">今日下午20:00下红包雨</a>
					</div>
				</div>
				
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">最新文章</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${newArticles}" var="article" varStatus="index">
							${index.index+1} . <a href="javascript:showArticle(${article.id })">${article.title}</a>
							<br/>
						</c:forEach>
					</div>
			    </div>
		</div>
		
	</div>
	
  </div>
  
</div>



<!-- 底部 -->
<nav class="navbar navbar-default" style="background:skyblue">
 <div class="container-fluid" >
  	<span style="color: red">友情链接:</span>
 	<!-- 友情链接 -->
 	<div class="row" style="margin-top:13px" align="center">
 			<c:forEach items="${linkList}" var ="link">
 				<div class="col-md-1" ><a href="${link.url}" class="fl"><span style="color: green">${link.name}</span></a></div>
 			</c:forEach>
 	</div>
 	
 </div>
 </nav>

<script type="text/javascript" src="/resource/js/cms_index.js"></script>


</body>
</html>