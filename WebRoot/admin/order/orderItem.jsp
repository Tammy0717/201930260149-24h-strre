<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table width="100%">
	<s:iterator var="orderItem" value="list">
	<tr>
		<td><img width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"></td>
		<td>商品名称：<s:property value="#orderItem.product.pname"/></td>
		<td>商品数量：<s:property value="#orderItem.count"/></td>
		<td>商品总价：<s:property value="#orderItem.subtotal"/></td>
	</tr>
	</s:iterator>
</table>