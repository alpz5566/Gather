<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>登陆页面</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="header.jsp" />
		<s:if test="#session['user'] != null">
			<div class="divNavigatorOuterFrame">
				<div class="divNavigatorInnerFrame" style="text-align: right;">
					欢迎<s:property value="#session['user'].Nickname" />&nbsp;&nbsp;
				</div>
			</div>
			<div class="divWhiteLine"></div>
		</s:if>
		<s:form action="LoginAction_doLog" namespace="/" method="post">
		<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdHeader" colspan="2">用户登陆</td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLabel" width="40%">E-mail:</td>
				<td class="tdFormControl">
					<input type="text" name="email" class="text" value="">
					<font class="fonterror"><br><s:actionerror></s:actionerror></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">密码:</td>
				<td class="tdFormControl">
					<input type="password" name="password" class="text" value="">
					<font class="fonterror"></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><s:submit type="submit" cssClass="btn" value="登录" /></td>
			</tr>
		</table>
		</s:form>
	</body>
</html>