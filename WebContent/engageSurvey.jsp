<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>参与调查</title>
		<link rel="stylesheet" type="text/css" href='<s:url value="/styles.css"/>'>
	</head>
	<body>
		<s:include value="/header.jsp" />
		<s:form action="EngageSurveyAction_doEngageSurvey" method="post" >
			<s:hidden name="currPid" value="%{currPage.id}" />
			<table>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<!-- 输出调查标题 -->
					<td colspan="2" class="tdHeader"><s:property value="#session.current_survey.title" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tdWhiteLine"></td>
				</tr>
				<tr>
					<!-- 页面标题 -->
					<td colspan="2" class="tdPHeaderL"><s:property value="currPage.title" /></td>
				</tr>
				<tr>
					<td width="30px"></td>
					<td>
						<table>
							<!-- 遍历问题集合 -->
							<s:iterator var="q" value="currPage.questions">
								<!-- 设置变量,对问题的id进行保持 -->
								<s:set var="qId" value="#q.id" />
								<!-- 设置变量,保持问题的题型 -->
								<s:set var="qt" value="#q.questionType" />
								<tr>
									<td class="tdQHeaderL"><s:property value="#q.title"/></td>
								</tr>
								<tr>
									<td class="tdOptionArea">
										<!-- 判断当前题型是否属于前四种 -->
										<s:if test='#qt lt 4'>
											<s:iterator var="option" value="#q.optionArr" status="st">
												<input type='<s:property value="#qt<2?'radio':'checkbox'" />'
													   name='q<s:property value="#qId" />'
													   value='<s:property value="#st.index" />'
													   <s:property value="setTag('q' + #qId,#st.index,'checked')" />
													   >
												<s:property />
												<s:if test="#qt == 1 || #qt == 3"><br></s:if>
											</s:iterator>
											<!-- 处理'其它'项内容,判断是否含有其他选项 -->
											<s:if test="#q.other">
												<input type='<s:property value="#qt<2?'radio':'checkbox'" />' 
														name='q<s:property value="#qId"/>' 
														value="other"
														<s:property value="setTag('q' + #qId,'other','checked')" />
														>其他
												<s:if test="#q.otherStyle == 1">
													<input type="text" 
															class="text" 
															name='q<s:property value="#qId"/>other'
															<s:property value="setText('q' + #qId + 'other')" />
															>
												</s:if>
												<!-- 其它选项类型是下拉列表 -->
												<s:elseif test="#q.otherStyle == 2">
													<select name='q<s:property value="#qId"/>other'>
														<s:iterator var="option" value="#q.otherSelectOptionArr" status="optst">
															<option value='<s:property value="#optst.index" />'
																	<s:property value="setTag('q' + #qId+'other',#optst.index,'selected')" />
																	><s:property /></option>
														</s:iterator>
													</select>
												</s:elseif>
											</s:if>
										</s:if>
										
										<!-- 非矩阵是下拉列表 -->
										<s:if test="#qt == 4">
											<select name='q<s:property value="#qId" />'>
												<s:iterator var="option" value="#q.optionArr" status="optst">
													<option value='<s:property value="#optst.index" />'
															<s:property value="setTag('q' + #qId,#optst.index,'selected')" />
															><s:property /></option>
												</s:iterator>
											</select>
										</s:if>
										
										<!-- 非矩阵式文本框 -->
										<s:if test="#qt == 5">
											<input type="text" 
												name='q<s:property value="#qId" />'
												<s:property value="setText('q' + #qId)" />
												>
										</s:if>
										
										<!-- 如果题型是矩阵式题型 -->
										<s:if test='#qt> 5'>
											<table>
												<!-- 列标题行(表头,表头第一列是空) -->
												<tr>
													<td></td>
													<!-- 循环输出列表标题数组 -->
													<s:iterator var="col" value="#q.matrixColTitleArr">
														<td><s:property value="#col" /></td>
													</s:iterator>
												</tr>
												<!-- 循环输出每一行,对行标题数组进行遍历 -->
												<s:iterator var="row" value="#q.matrixRowTitleArr" status="rowst">
													<tr>
														<td><s:property value="#row" /></td>
														<!-- 循环输出控件 -->
														<s:iterator var="col" value="#q.matrixColTitleArr" status="colst">
															<td>
																<!-- 矩阵式单选按钮 -->
																<s:if test="#qt == 6">
																	<input type="radio" 
																			name='q<s:property value="#qId+'_' + #rowst.index" />' 
																			value='<s:property value="#rowst.index + '_' + #colst.index"/>'
																			<s:property value="setTag('q' + #qId+'_' + #rowst.index,#rowst.index + '_' + #colst.index,'checked')" />
																			>
																</s:if>
																<!--  矩阵式复选 -->
																<s:elseif test="#qt == 7">
																	<input type="checkbox" 
																		name='q<s:property value="#qId" />' 
																		value='<s:property value="#rowst.index+'_' +#colst.index"/>'
																		<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index,'checked')" />
																		>
																</s:elseif>
																<!-- 矩阵式下拉列表 -->
																<s:elseif test="#qt == 8">
																	<select name='q<s:property value="#qId"/>'>
																		<s:iterator var="option" value="#q.matrixSelectOptionArr" status="optst">
																			<option value='<s:property value="#rowst.index+'_'+#colst.index+'_'+#optst.index"/>'
																					<s:property value="setTag('q' + #qId,#rowst.index + '_' + #colst.index+'_' +#optst.index,'selected')" />
																					><s:property value="#option"/></option>
																		</s:iterator>
																	</select>
																</s:elseif>
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
				<tr>
					<td colspan="2" align="center">
						<!-- 构造上一步按钮 -->
						<s:if test="currPage.orderno != #session.current_survey.minOrderno">
							<input type="submit" name='submit_pre' value='<s:property value="#session.current_survey.preText"/>' class="btn">
						</s:if>
						<!-- 构造下一步按钮 -->
						<s:if test="currPage.orderno != #session.current_survey.maxOrderno">
							<input type="submit" name='submit_next' value='<s:property value="#session.current_survey.nextText"/>' class="btn">
						</s:if>
						<!-- 构造完成按钮 -->
						<s:if test="currPage.orderno == #session.current_survey.maxOrderno">
							<input type="submit" name="submit_done" value='<s:property value="#session.current_survey.doneText"/>' class="btn">
						</s:if>
						<input type="submit" name="submit_exit" value='<s:property value="#session.current_survey.exitText"/>' class="btn">
					</td>
				</tr>
			</table>
			</s:form>
	</body>
</html>