<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>    
<div class="sidebar">
            	<h2 class="sidebar-header"><p>功能导航</p></h2>
                <ul class="nav">
                <!-- index_permissions -> List<PermissionEntity> -->
                	<c:forEach var="i" items="${index_permissions}" varStatus="status">
                		<li class="office">
                			<div class="nav-header"><a href="javascript:;" class="ue-clear"><span>${i.name }</span><i class="icon"></i></a></div>
	                        <c:if test="${i.childPermission != null && fn:length(i.childPermission) > 0}">
		                        <ul class="subnav">
		                        	<c:forEach var="ic" items="${i.childPermission}">
		                        		<li><a href="javascript:;" onclick='show_iframe("${ctx}","${ic.url}")'>${ic.name}</a></li>
		                        	</c:forEach>
		                        </ul>
	                        </c:if>
                		</li>
                	</c:forEach>
                	<!--  
                	<li class="office current"><div class="nav-header"><a href="javascript:;" date-src="home.html" class="ue-clear"><span>日常办公</span><i class="icon"></i></a></div></li>
                    <li class="gongwen"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>公文起草</span><i class="icon"></i></a></div></li>
                    <li class="nav-info">
                    	<div class="nav-header"><a href="javascript:;" class="ue-clear"><span>导航信息管理</span><i class="icon"></i></a></div>
                        <ul class="subnav">
                        	<li><a href="javascript:;" date-src="info-reg.html">信息录入</a></li>
                            <li><a href="javascript:;" date-src="info-mgt.html">信息管理</a></li>
                            <li><a href="javascript:;" date-src="info-det.html">领导值岗管理</a></li>
                            <li><a href="javascript:;">中层领导管理</a></li>
                            <li><a href="javascript:;">领导值班记录</a></li>
                        </ul>
                    </li>
                    <li class="konwledge"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>知识管理</span><i class="icon"></i></a></div></li>
                    <li class="agency"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>组织结构</span><i class="icon"></i></a></div></li>
                    <li class="email"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>邮件管理</span><i class="icon"></i></a></div></li>
                    <li class="system"><div class="nav-header"><a href="javascript:;" class="ue-clear"><span>系统管理</span><i class="icon"></i></a></div></li>
                    -->
                </ul>
            </div>
            <script>
            	var iconDefined = {
            			"人事管理":"agency",
            			"帮助支持":"konwledge",
            			"系统管理":"system"
            	};
            	var nav_header = document.getElementsByClassName("nav-header");
            	for(var x = 0;x <nav_header.length;x++){
            		var span = nav_header[x].getElementsByTagName("span")[0];
            		console.log(span);
            		if(span.innerText){
            			for(var icon in iconDefined){
            				if(span.innerText == icon){
            					nav_header[x].parentNode.className = iconDefined[icon];
            				}
            			}
            		}
            	}
            </script>