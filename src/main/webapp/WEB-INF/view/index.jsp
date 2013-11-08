<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Push Service</title>
	<link href="<%=basePath %>css/bootstrap.css" rel="stylesheet">
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top bs-docs-nav">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand hidden-sm" href="#">Push Service</a>
        </div>
        <nav class="navbar-collapse collapse">
            <ul class="nav navbar-nav" >
                <li class="active"><a href="./index">首页</a></li>
                <li><a href="./about">关于</a></li>
            </ul>
        </nav>
    </div>
</header>
<br><br>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img class="img-responsive" style="width: 100%" src="<%=basePath %>img/first.png" alt="first">
      <div class="carousel-caption">
		<h3>First slide label</h3>
		<p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
      </div>
    </div>
	<div class="item">
	  <img style="width: 100%" alt="second" src="<%=basePath %>img/second.png">
	  <div class="carousel-caption">
		<h3>Second slide label</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
      </div>
	</div>
	<div class="item">
	  <img style="width: 100%" alt="third" src="<%=basePath %>img/third.png">
	  <div class="carousel-caption">
		<h3>Third slide label</h3>
		<p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
      </div>
	</div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.js"></script>
</body>
</html>