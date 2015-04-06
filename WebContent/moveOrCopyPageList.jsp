<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>移动/复制页面</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td colspan="3" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="3" class="tdHeader">移动/复制页:[同一调查内是移动,不同调查间是复制]</td>
			</tr>
			<tr>
				<td colspan="3" class="tdWhiteLine"></td>
			</tr>
			<s:iterator var="s" value="surveys">
				<s:set var="sId" value="#s.id" />
				<tr>
					<td colspan="3" class="tdSHeaderL"><s:property value="title" /></td>
				</tr>
				<s:iterator var="p" value="#s.pages" status="st">
					<s:set var="pId" value="#p.id"/>
					<!-- 当前的页面高亮 -->
					<s:if test="#pId == srcPid">
						<!-- 设置字符串变量值,保持颜色 -->
						<s:set var="bgcolor" value='"rgb(200,125,200)"' />
					</s:if>
					<s:else>
						<s:set var="bgcolor" value='"bgcolor=\"white\""' />
					</s:else>
					<tr bgcolor='<s:property value="#bgcolor"/>'>
						<td style="width:30px;border-width:0;background-color: white"></td>
						<td><s:property value="#p.title" /></td>
						<td>
							<s:if test="#pId != srcPid">
								<s:form name="form%{#pId}" action="MoveOrCopyPageAction_doMoveOrCopyPage" method="post">
									<s:hidden name="srcPid" />
									<s:hidden name="targPid" value="%{#pId}" />
									<!-- 当移动/复制完成后,需要重定向到目标调查的设计页面 -->
									<s:hidden name="sid" value="%{#sId}" />
									<s:radio list="#{0:'之前',1:'之后'}" listKey="key" listValue="value" name="pos"/>
									<input type="submit" class="btn" value="确定">
								</s:form>
							</s:if>
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
		</table>
	</body>
</html>