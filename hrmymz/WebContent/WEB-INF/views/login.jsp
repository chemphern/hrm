<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${res }/common/css/base.css" />
	<link rel="stylesheet" href="${res }/login/login.css" />
	<title>人事管理系统</title>
</head>
<style>
</style>
<body>
	<div id="container">
		<form id="login_form" action="${ctx }/login" method="post" autocomplete="off">
			<div id="bd">
				<div class="login1">
					<div class="login-top"><h1 class="logo"></h1></div>
					<div class="login-input">
						<p class="user ue-clear">
							<label>用户名</label>
							<input type="text" name="username" id="usn" autocomplete="off" />
						</p>
						<p class="password ue-clear">
							<label>密&nbsp;&nbsp;&nbsp;码</label>
							<input type="password" name="password" id="pwd" autocomplete="off" />
						</p>
						<p class="yzm ue-clear">
							<label>验证码</label>
							<input type="text" name="captcha" id="cap" autocomplete="off"/>
							<img id="kapimg" src="${ctx }/static/images/kaptcha.jpg" style="margin-left: -80px;position: absolute;cursor:pointer">
						</p>
					</div>
					<div class="login-btn ue-clear">
						<a href="javascript:void(0);" class="btn" id="login_btn">登录</a>
						<div class="remember ue-clear">
							<input type="checkbox" id="remember" name="rememberme" autocomplete="off"/>
							<em></em>
							<label for="remember">记住密码</label>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
    <div id="ft">CopyRight&nbsp;2016&nbsp;&nbsp;版权所有&nbsp;&nbsp;广东水利电力职业技术学院毕业设计&nbsp;&nbsp;作者：叶明珠</div>
</body>
<script type="text/javascript" src="${res }/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${res}/js/common/common.js"></script>
<jsp:include page="./common/tip.jsp" ></jsp:include>
<script type="text/javascript">
//调整页面的代码
var height = $(window).height();
$("#container").height(height);
$("#bd").css("padding-top",(height/2) - $("#bd").height()/2);
$(window).resize(function(){
	var height = $(window).height();
	$("#bd").css("padding-top",$(window).height()/2 - $("#bd").height()/2);
	$("#container").height(height);
	
});

//记住我的操作
$('#remember').focus(function(){
   $(this).blur();
});

$('#remember').click(function(e) {
	checkRemember($(this));
});

function checkRemember($this){
	if(!-[1,]){
		//选中记住我
		 if($this.prop("checked")){
			$this.parent().addClass('checked');
			$("#remember").val("1");
			//没选中记住我
		}else{
			$this.parent().removeClass('checked');
			$("#remember").val("0");
		}
	}
}
$(function(){
	//$("#usn").val(" ");
	//$("#usn").focus(function(){
	//	if($("#usn").val() == " "){
	//		$("#usn").val("");
	//	}
	//});
	//验证码点击的时候刷新图片
	$("#kapimg").click(function(){
		var ts = $(this);
		var ts_value = ts.attr("src");
		ts.attr("src",ts_value.split("?")[0] + "?r=" + Math.random());
	});
	//点击登陆按钮的事件
	$("#login_btn").click(function(){
		var usn = $("#usn").val();
		var pwd = $("#pwd").val();
		var cap = $("#cap").val();
		var tip = "";
		tip = (usn == null|| usn == "")? "用户名不能为空!"
				:(pwd == null || pwd == "")? "密码不能为空!"
				:(cap == null || cap == "")? "验证码不能为空!":"";
		if(tip != ""){
			alert(tip);
		}else{
			$("#login_form").submit();
		}		
	});
	
	$("#login_form").keydown(function(e){
		if(e.keyCode==13){
			$("#login_btn").click();
		}
	});
});
</script>
</html>

