<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="ck" uri="http://ckeditor.com"%> 
    
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
<%@ include file="heard.jsp" %>
	<%@ include file="menu.jsp" %>
	
</div>	

<div class="container cart">

		<div class="span24">
		
				<table>
					<tbody>
					<tr>
						<th colspan="5">订单编号:<s:property value="model.oid"/>&nbsp;&nbsp;&nbsp;&nbsp;</th>
					</tr>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
						<s:iterator var="orderItem" value="model.orderItems">
						<tr>
							<td width="60">
								<img src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"/>
							</td>
							<td>
								<s:property value="#orderItem.product.pname"/>
							</td>
							<td>
								<s:property value="#orderItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#orderItem.count"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#orderItem.subtotal"/></span>
							</td>
							
						</tr>
						</s:iterator>
				</tbody>
			</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
					商品金额: <strong id="effectivePrice">￥<s:property value="model.total"/>元</strong>
				</div>
		</div>
		
		<form action="${ pageContext.request.contextPath }/order_saveMessage.action" method="post">
		<input type="hidden" name="oid" value="<s:property value="model.oid"/>"/>
    <textarea rows="10" cols="10" id="my_txt" name="my_txt"></textarea>  
    <ck:replace replace="my_txt" basePath="ckeditor"></ck:replace>  
    <button type="submit">留言评价</button>
    </form>
	</div>
<%@ include file="tail.jsp" %>
</body>
</html>