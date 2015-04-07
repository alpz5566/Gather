<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>设计调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css" />'>
		<script type="text/javascript" src="<s:url value="/jquery-1.7.1.js" />"></script>
		<script type="text/javascript">
			$(function(){
				$("a[href*=delete]").click(function(){
					return confirm("删除该项?");
				});				
			});
		</script>
	</head>
	<body>
		<s:include value="header.jsp" />
		<s:set var="sId" value="id" />
		<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td colspan="2" class="tdHeader">设计调查</td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<td class="tdSHeaderL">
					<s:if test="logoIsExists()">
						<img  src="<s:url value='%{logoPath}' />" height="20px" width="50px">
					</s:if>	
						<!-- 调查标题 -->
						<s:property value="title" />
					</td>
					<td class="tdSHeaderR">
						<s:a action="SurveyAction_toAddLogoPage?sid=%{#sId}">增加Logo</s:a>&nbsp;
						<s:a action="SurveyAction_editSurvey?sid=%{#sId}" namespace="/">编辑调查</s:a>&nbsp;
						<s:a action="PageAction_toAddPage?sid=%{#sId}" namespace="/">增加页</s:a>&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;vertical-align: top;">
						<table>
							<tr>
								<td width="30px"></td>
								<td width="*">
									<table>
										<!-- 迭代页面集合 -->
										<s:iterator var="p" value="pages">
										<s:set var="pId" value="#p.id" />
										<tr>
											<td>
												<table>
													<tr>
														<!-- 页面标题 -->
														<td class="tdPHeaderL"><s:property value="#p.title" /></td>
														<td class="tdPHeaderR">
															<s:a action="PageAction_editPage?sid=%{#sId}&pid=%{#pId}" namespace="/">编辑页标题</s:a>&nbsp;
															<s:a action="MoveOrCopyPageAction_toSelectTargetPage?srcPid=%{#pId}" namespace="/">移动/复制页</s:a>&nbsp;
															<s:a action="QuestionAction_toSelectQuestionType?sid=%{#sId}&pid=%{#pId}" namespace="/">增加问题</s:a>&nbsp;
															<s:a action="PageAction_deletePage?sid=%{#sId}&pid=%{#pId}">删除页</s:a>&nbsp;
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table>
													<tr>
														<td width="30px"></td>
														<td width="*">
															<table>
																<tr>
																	<td>
																		<table>
																			<!-- 迭代问题集合 -->
																			<s:iterator var="q" value="#p.questions">
																			<s:set var="qId" value="#q.id" />
																			<tr>
																				<!-- 问题题干 -->
																				<td class="tdQHeaderL"><s:property value="#q.title" /></td>
																				<td class="tdQHeaderR">
																					<s:a action="QuestionAction_editQuestion?sid=%{#sId}&pid=%{#pId}&qid=%{#qId}">编辑问题</s:a>&nbsp;
																					<s:a action="QuestionAction_deleteQuestion?sid=%{#sId}&pid=%{#pId}&qid=%{#qId}">删除问题</s:a>&nbsp;
																				</td>
																			</tr>
																			<tr>
																				<td colspan="2" style="text-align: left;color: black;background-color: white">
																					<!-- 定义变量,设置第一大类的题型 -->
																					<s:set var="qt" value="#q.questionType" />
																					<!-- 判断当前题型是否属于第一大类(0,1,2,3) -->
																					<s:if test='#qt lt 4'>
																						<s:iterator value="#q.optionArr">
																							<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'><s:property />
																							<s:if test="#qt == 1 || #qt == 3"><br></s:if>
																						</s:iterator>
																						<!-- 处理other问题 -->
																						<s:if test="#q.other">
																							<input type='<s:property value="#qt < 2?'radio':'checkbox'" />'>其他
																							<!-- 文本框 -->
																							<s:if test="#q.otherStyle == 1">
																								<input type="text">
																							</s:if>
																							<!--  下拉列表 -->
																							<s:elseif test="#q.otherStyle == 2">
																								<select>
																									<s:iterator value="#q.otherSelectOptionsArr" >
																										<option><s:property /></option>
																									</s:iterator>
																								</select>
																							</s:elseif>
																						</s:if>
																					</s:if>
																					
																					<!-- 下拉列表 -->
																					<s:if test="#qt == 4">
																						<select>
																							<s:iterator value="#q.optionArr" >
																								<option><s:property /></option>
																							</s:iterator>
																						</select>
																					</s:if>
																					<!-- text -->
																					<s:if test="#qt == 5">
																						<input type="text">
																					</s:if>
																					
																					<!-- 矩阵问题(6,7,8) -->
																					<s:if test="#qt > 5">
																						<table>
																							<!-- 列头 -->
																							<tr>
																								<td></td>
																								<s:iterator value="#q.matrixColTitleArr">
																									<td><s:property /></td>
																								</s:iterator>
																							</tr>
																							<!-- 输出n多行 -->
																							<s:iterator value="#q.matrixRowTitleArr">
																								<tr>
																									<td><s:property /></td>
																									<!-- 套打控件 -->
																									<s:iterator value="#q.matrixColTitleArr">
																										<td>
																											<!-- radio -->
																											<s:if test="#qt == 6"><input type="radio"></s:if>
																											<s:if test="#qt == 7"><input type="checkbox"></s:if>
																											<s:if test="#qt == 8">
																												<select>
																													<s:iterator value="#q.matrixSelectOptionArr">
																														<option><s:property /></option>
																													</s:iterator>
																												</select>
																											</s:if>
																										</td>
																									</s:iterator>
																								</tr>
																							</s:iterator>
																						</table>
																					</s:if>
																				</td>
																			</tr>
																			</s:iterator>
																		</table>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</body>
</html>