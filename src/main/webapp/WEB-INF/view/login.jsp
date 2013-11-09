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
	<link href="<%=basePath %>css/signin.css" rel="stylesheet">
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
            <ul class="nav navbar-nav navbar-right" >
                <li><a href="./about">About</a></li>
            </ul>
        </nav>
    </div>
</header>
<br><br>
<div class="container">

  <form class="form-signin" action="login" method="post">
    <h2 class="form-signin-heading">Please sign in</h2>
    <input name="email" type="text" class="form-control" placeholder="Email address" required autofocus>
    <input name="password" type="password" class="form-control" placeholder="Password" required>
    <label class="checkbox">
      <input name="remember" type="checkbox" value="1"> Remember me
    </label>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  </form>

</div>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.js"></script>
</body>
</html>