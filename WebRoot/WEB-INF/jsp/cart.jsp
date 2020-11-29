<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>


</head>
<body>
<div class="container header">
	<%@ include file="heard.jsp" %>
	<%@ include file="menu.jsp" %>
	
</div>	<div class="container cart">
		<s:if test="#session.existUser == null">
					<div class="title">
						<strong>当前尚未登录，请登录</strong>
					</div>
		</s:if>
		<s:else>	
		<s:if test="#session.cart.list.size() != 0">
		<div class="span24">
			<div class="step step1">
				购物车信息
			</div>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
						<s:iterator var="shopCartItem" value="#session.cart.list">
						<tr>
								<input type="hidden" id="pid"  name="pid" value="<s:property value="#shopCartItem.pid"/>" />
							<td width="60">
								<img src="${pageContext.request.contextPath}/<s:property value="#shopCartItem.image"/>">
							</td>
							<td>
								<a target="_blank"><s:property value="#shopCartItem.pname"/></a>
							</td>
							<td>
								￥<s:property value="#shopCartItem.price"/>
							</td>
							<td class="quantity" width="60">
								<s:property value="#shopCartItem.pcount"/>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#shopCartItem.ptotal"/></span>
							</td>
							<td>
								<a href="${ pageContext.request.contextPath }/cart_removeCart.action?pid=<s:property value="#shopCartItem.pid"/>" class="delete">删除</a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
					<em id="promotion"></em>
							
					赠送积分: <em id="effectivePoint"><s:property value="#session.cart.total"/></em>
					商品金额: <strong id="effectivePrice">￥<s:property value="#session.cart.total"/>元</strong>
				</div>
				<div class="bottom">
					<a href="${ pageContext.request.contextPath }/cart_clearCart.action" id="clear" class="clear">清空购物车</a>
					<a href="${ pageContext.request.contextPath }/order_saveOrder.action" id="submit" class="submit">提交订单</a>
				</div>
				
		</div>
		</s:if>
		<s:else>
			<div class="span24">
				<div class="step step1">
					<span><h2>亲!您还没有购物!请先去购物!</h2></span>
				</div>
			</div>
		</s:else>
		
		</s:else>
	</div>

</body></html>