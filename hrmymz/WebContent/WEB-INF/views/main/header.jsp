<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>        
	<div id="hd">
    	<div class="hd-wrap ue-clear">
        	<div class="top-light"></div>
            <h1 class="logo"></h1>
            <div class="login-info ue-clear">
                <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:;" class="user-name">${hrm_session_user.name}</a></div>
                <div class="login-msg ue-clear">
                    <!--
                    <a href="javascript:;" class="msg-txt">消息</a>
                    <a href="javascript:;" class="msg-num">10</a>
                    -->
                </div>
            </div>
            <div class="toolbar ue-clear">
                <a href="javascript:;" class="home-btn" id="home-btn">首页</a>
                <a href="javascript:;" id="y_exit" class="quit-btn exit" style="width:40px;padding-left:46px;color:#fff">退出</a>
            </div>
        </div>
    </div>
    <script>
    window.onload = function(){
    	$("#y_exit").click(function(){
    		$tip("是否要退出该系统","",{value:"确定",click:function(){
    			window.location.href = "${ctx}/logout";
    		}},{value:"取消"});
    	});
    	$("#home-btn").click(function(){
    		show_iframe("${ctx}","/welcome");
    	});
    }
    	
    </script>