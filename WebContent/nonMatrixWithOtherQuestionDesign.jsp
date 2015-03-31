<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>非矩阵型问题设计</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:form action="QuestionAction_saveOrUpdateQuestion.action" method="post">
		<s:hidden name="id" />
		<s:hidden name="questionType" />
		<s:hidden name="pid" />
		<s:hidden name="sid" />
		<table>
			<tr>
				<td colspan="2" class="tdQHeaderL">非矩阵型问题设计:</td>
			</tr>
			<tr>
				<td width="35%" style="text-align: right;">问题标题:</td>
				<td width="*" style="text-align: left;"><s:textfield name="title" cssClass="text" /></td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">问题选项:</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="options"/></td>
			</tr>
			<tr>
				<td style="text-align: right;">是否含有"其它"选项:</td>
				<td width="*" style="text-align: left;"><s:checkbox name="other" /></td>
			</tr>
			<tr>
				<td style="text-align: right;">"其它"项类型:</td>
				<td width="*" style="text-align: left;">
					<s:radio list="#{0:'无',1:'文本框',2:'下拉列表框'}" listKey="key" listValue="value" name="otherStyle" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;vertical-align: top;">"其它"项下拉列表选项:</td>
				<td width="*" style="text-align: left;"><s:textarea cols="41" rows="8" name="otherSelectOptions" /></td>
			</tr>
			<tr>
				<td style="text-align: right;"></td>
				<td width="*" style="text-align: left;"><input type="submit" name="ok" value="确定" class="btn"></td>
			</tr>
		</table>
		</s:form>
	</body>
</html>