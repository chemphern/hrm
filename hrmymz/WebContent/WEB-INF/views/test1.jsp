<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=emulateIE7" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8">
<link rel="stylesheet" href="${res }/js/common/template/css/sapar.css" />
<link rel="stylesheet" type="text/css" href="${res }/js/common/template/css/common.css" />
<link rel="stylesheet" type="text/css" href="${res }/js/common/template/css/user.css" />
<script type="text/javascript" src="${res }/js/common/template/js/jquery.js"></script>
<script type="text/javascript" src="${res }/js/common/template/js/sapar.js"></script>
<script type="text/javascript" src="${res }/js/common/template/js/WdatePicker.js"></script>
<title>员工管理</title>
</head>

<body>
    <div id="saper-container">
        <div id="saper-hd"></div>
        <div id="saper-bd">
            <div class="subfiled clearfix">
                <h2>员工管理</h2>
            </div>
            <div class="subfiled-content">
                <div class="search-box clearfix">
                    <div class="kv-item clearfix">
                        <label>姓名：</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="姓名">
                        </div>
                    </div>
                     <div class="kv-item clearfix">
                        <label>固话：</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="固话">
                        </div>
                    </div>
                    <div class="kv-item clearfix">
                        <label>手机：</label>
                        <div class="kv-item-content">
                            <input type="text" placeholder="手机">
                        </div>
                    </div>
                    <a href="javascript:;" class="sapar-btn sapar-btn-recom query-btn">查询</a>
                </div>
                

                <!--表格开始-->
                <div class="table">
                    <!--表格操作-->
                    <div class="table-operate ue-clear">
                        <a href="javascript:;" class="add">添加</a>
                        <a href="javascript:;" class="del">删除</a>
                    </div>
                    <!--表格具体内容-->
                    <div class="table-box">
                        <table>
                            <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>用户名</th>
                                    <th>手机号码</th>
                                    <th>固定电话</th>
                                    <th>添加日期</th>
                                    <th>使用期限</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="no-data"><td colspan="10">暂时没有数据</td></tr>
                                
                            </tbody>
                        </table>
                    </div>
                </div><!--表格结束-->
            </div>
        </div>
        <div id="saper-ft"></div>
    </div>
    
</body>

<script type="text/javascript">

</script>
</html>