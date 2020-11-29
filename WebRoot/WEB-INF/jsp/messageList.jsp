<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>留言页面</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<%@ include file="heard.jsp"%>

		<%@ include file="menu.jsp"%>

	</div>
	<div class="container cart">
				<%-- <div class="step step1" algin="center">
				
				<ul>
					<li class="current"></li>
					<font size="16px" color="#E80E38" bgColor="#E80E38"><strong>用户留言 &nbsp; &nbsp; &nbsp; &nbsp;仅供参考</strong></font>
				</ul>
			</div> --%>

				<table>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #08EE4B">

								<td align="center" width="18%">序号</td>
								<td align="center" width="17%">用户昵称</td>
								<td align="center" width="17%">留言</td>
								<td align="center" width="17%">留言日期</td>

							</tr>
							<s:iterator var="message" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="#status.count" /></td>

									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="10%"><s:property value="#message.user.username" />
									</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="50%"><strong><font color="red"><s:property
													value="#message.message" />
										</font>
									</strong></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="30%"><s:property value="#message.messagedate" />
										</span></td>
								</tr>
							</s:iterator>
						</table></td>
				</tr>
				<tr align="center">
					<td colspan="7">第<s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" />页 <s:if test="pageBean.page != 1">
							<a
								href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=1">首页</a>|
								<a
								href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if> <s:if test="pageBean.page != pageBean.totalPage">
							<a
								href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a
								href="${ pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if></td>
				</tr>
		</table>
	</div>

	<%@ include file="tail.jsp"%>
</body>
</html>