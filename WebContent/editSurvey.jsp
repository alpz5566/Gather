<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>编辑调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<table>
			<tr>
				<td class="tdHeader">编辑调查标题:</td>
			</tr>
			<tr>
				<td style="vertical-align: top;">
					<table>
						<tr>
							<td>
								<s:form action="SurveyAction_updateSurvey" namespace="/" method="post">
								<s:hidden name="id" />
								<table>
									<tr>
										<td class="tdFormLabel">调查标题:</td>
										<td class="tdFormControl"><s:textfield name="title" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"下一步"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="nextText" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"上一步"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="preText" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"完成"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="doneText" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel">"退出"提示文本:</td>
										<td class="tdFormControl"><s:textfield name="exitText" cssClass="text" /></td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl"><s:submit value="确定" cssClass="btn" /></td>
									</tr>
								</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>