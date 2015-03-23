<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>我的调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src='<s:url value="/jquery-1.7.1.js" />' ></script>
		<script type="text/javascript">
			$(function(){
				$("a[href*=delete]").click(function(){
					$(this).attr("disabled","disabled");
				});
			});
		</script>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:if test="mySurveys.isEmpty() == true">目前您没有任何调查项!</s:if >
		<s:else>
			<table>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
				<tr>
					<td colspan="10" class="tdHeader">我的调查:</td>
				</tr>
				<tr>
					<td colspan="10" style="height: 5px"></td>
				</tr>
				<tr>
					<td class="tdListHeader">ID</td>
					<td class="tdListHeader">调查标题</td>
					<td class="tdListHeader">创建时间</td>
					<td class="tdListHeader">状态</td>
					<td class="tdListHeader">设计</td>
					<td class="tdListHeader">收集信息</td>
					<td class="tdListHeader">分析</td>
					<td class="tdListHeader">打开/关闭</td>
					<td class="tdListHeader">清除调查</td>
					<td class="tdListHeader">删除</td>
				</tr>
				<s:iterator value="mySurveys" >
					<s:set var="sId" value="id" />
					<tr>
						<td><s:property value="id" /></td>
						<td><s:property value="title" /></td>
						<td><s:date name="createTime" format="MM/dd/yy HH:mm" /></td>
						<td>
						</td>
						<td>设计</td>
						<td>收集信息</td>
						<td>分析</td>
						<td>打开/关闭</td>
						<td>清除调查</td>
						<td>删除</td>
					</tr>
				</s:iterator>
			</table>
		</s:else>
	</body>
</html>