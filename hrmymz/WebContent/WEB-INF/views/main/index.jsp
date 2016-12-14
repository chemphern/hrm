<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${res }/common/css/base.css" />
<link rel="stylesheet" type="text/css" href="${res }/common/css/jquery.dialog.css" />
<link rel="stylesheet" href="${res }/main/index.css" />

<title>人事管理系统</title>
</head>

<body>
<div id="container">
	<jsp:include page="header.jsp"></jsp:include>
    <div id="bd">
    	<div class="wrap ue-clear">
        	<jsp:include page="left.jsp"></jsp:include>
            <div class="content">
            	<iframe src="${ctx }/welcome" id="iframe" width="100%" height="100%" frameborder="0"></iframe>
            </div>
        </div>
    </div>
    <jsp:include page="bottom.jsp"></jsp:include>
</div>
</body>
<script type="text/javascript" src="${res }/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${res }/js/common/common.js"></script>
<script type="text/javascript" src="${res }/js/jquery/plugin/core.js"></script>
<script type="text/javascript" src="${res }/js/jquery/plugin/jquery.dialog.js"></script>
<script type="text/javascript" src="${res }/main/index.js"></script>
<jsp:include page="../common/tip.jsp"></jsp:include>
<script>
function show_iframe(ctx,url){
	$("#iframe").attr("src",ctx + url);
};
</script>
</html>
