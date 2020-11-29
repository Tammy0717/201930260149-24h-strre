<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>会员注册</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>

<script>
	function checkForm(){
	
		// 校验用户名:
		// 获得用户名文本框的值:
		var username = document.getElementById("username").value;
		if(username == null || username == ''){
			alert("用户名不能为空!");
			return false;
		}
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			alert("密码不能为空!");
			return false;
		}
		// 校验确认密码:
		var repassword = document.getElementById("repassword").value;
		if(repassword != password){
			alert("两次密码输入不一致!");
			return false;
		}
		
		// 获得email框的值:
		var email = document.getElementById("email").value;
		if(email == null || password == ''){
			alert("邮箱不能为空!");
			return false;
		}
		
		
		// 获得姓名框的值:
		var name = document.getElementById("name").value;
		if(name == null || name == ''){
			alert("姓名不能为空!");
			return false;
		}
		
		
		// 获得电话框的值:
		var phone = document.getElementById("phone").value;
		if(phone == null || phone == ''){
			alert("电话不能为空!");
			return false;
		}
		
		// 获得地址框的值:
		var address = document.getElementById("address").value;
		if(address == null || address == ''){
			alert("地址不能为空!");
			return false;
		}
	}
	
	
	function checkUsername(){
		 // 获得文件框值:
		var username = document.getElementById("username").value;
		
		if(username==""){
		    	document.getElementById("spanusername").innerHTML="<font color='red'>用户名不能为空</font>";
		    	document.getElementById("sub").disable=true;
		    	document.getElementById("sub").style. background="#909090";
		}else{
		// 1.创建异步交互对象
		var xhr = createXmlHttp();
		// 2.设置监听
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					document.getElementById("spanusername").innerHTML = xhr.responseText;
					var list=eval("("+xhr.responseText+")");
					alert(list);
					if(list=="x"){
						alert("sss");
					}else{
						alert("bbb");
					}
					document.getElementById("sub").disabled=false;
					document.getElementById("sub").style.background="#D1062D";
				}
			}
		}
		// 3.打开连接
		xhr.open("GET","${pageContext.request.contextPath}/user_findByName.action?time="+new Date().getTime()+"&username="+username,true);
		// 4.发送
		xhr.send(null); 
		
		}
	}
	
	

	
	
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;
		 }
	
	function change(){
		var img1 = document.getElementById("checkImg");
		img1.src="${pageContext.request.contextPath}/checkImg.action?"+new Date().getTime();
	}
	
	function checkpassword(){
		// 校验密码:
		// 获得密码框的值:
		var password = document.getElementById("password").value;
		if(password == null || password == ''){
			document.getElementById("spanpassword").innerHTML="<font color='red'>密码不能为空</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			document.getElementById("spanpassword").innerHTML="";
			document.getElementById("sub").disabled=false;
			document.getElementById("sub").style.background="#D1062D";
		}
	}
	
	
	function checkrepassword(){
		// 校验确认密码:
		// 获得确认密码框的值:
		var password = document.getElementById("password").value;
		
		// 校验确认密码:
		var repassword = document.getElementById("repassword").value;
		if(repassword != password){
			document.getElementById("spanrepassword").innerHTML="<font color='red'>两次密码不一致</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			document.getElementById("spanrepassword").innerHTML="";
			document.getElementById("sub").disabled=false;
			document.getElementById("sub").style.background="#D1062D";
		}
	}
	
	function checkemail(){
		// 校验邮箱:
		// 获得邮箱框的值:
		var email = document.getElementById("email").value;
		if(email == null || email == ''){
			document.getElementById("spanemail").innerHTML="<font color='red'>邮箱不能为空</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			
			if(!myreg.test(email)){
				document.getElementById("spanemail").innerHTML="<font color='red'>邮箱格式不正确</font>";
				document.getElementById("sub").disabled=true;
				document.getElementById("sub").style. background="#909090";
			}else{
				document.getElementById("spanemail").innerHTML="";
				document.getElementById("sub").disabled=false;
				document.getElementById("sub").style.background="#D1062D";
			}
		}
	}
	
	
	
	function checkname(){
		// 校验姓名密码:
		// 获得姓名框的值:
		var name = document.getElementById("name").value;
		
		if(name == null || name == ''){
			document.getElementById("spanname").innerHTML="<font color='red'>姓名不能为空</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			document.getElementById("spanname").innerHTML="";
			document.getElementById("sub").disabled=false;
			document.getElementById("sub").style.background="#D1062D";
		}
	}
	
	function checkphone(){
		// 校验电话密码:
		// 获得电话框的值:
		
		var phone = document.getElementById("phone").value;
		if(phone == null || phone == ''){
			document.getElementById("spanphone").innerHTML="<font color='red'>电话号码不能为空</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			var mobile=/^((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8}$/;
			if(!mobile.test(phone)){
				document.getElementById("spanphone").innerHTML="<font color='red'>电话号码错误</font>";
				document.getElementById("sub").disabled=true;
				document.getElementById("sub").style. background="#909090";
			}else{
				document.getElementById("spanphone").innerHTML="";
				document.getElementById("sub").disabled=false;
				document.getElementById("sub").style.background="#D1062D";
			}
			
		}
	}
	
	function checkaddress(){
		// 校验地址:
		// 获得地址框的值:
		var address = document.getElementById("address").value;
		if(address == null || address == ''){
			document.getElementById("spanaddress").innerHTML="<font color='red'>地址不能为空</font>";
			document.getElementById("sub").disabled=true;
			document.getElementById("sub").style. background="#909090";
		}else{
			document.getElementById("spanaddress").innerHTML="";
			document.getElementById("sub").disabled=false;
			document.getElementById("sub").style.background="#D1062D";
		}
	}
	
</script>
</head>
<body>
<div class="container header">
	<%@ include file="heard.jsp" %>
	
	<%@ include file="menu.jsp" %>

</div>	<div class="container register">
		<div class="span24">
		
			<div class="wrap">
				<div class="main clearfix">
				<s:if test="#session.existUser == null">
					<div class="title">
						<strong>当前尚未登录，请登录</strong>
					</div>
					</s:if>	
					<s:else>
					<div class="title">
						<strong>会员中心</strong>
					</div>
					</s:else>
					<div>
						<s:actionerror />
					</div>
				
				
					<form id="registerForm" action="${ pageContext.request.contextPath }/user_update.action"  method="post" novalidate="novalidate" onsubmit="return checkForm();">
						<table>
						<input type="hidden" name="uid" value="<s:property value="#session.existUser.uid"/>" />
						<input type="hidden" name="state" value="<s:property value="#session.existUser.state"/>" />
						<input type="hidden" name="code" value="<s:property value="#session.existUser.code"/>" />
							<tbody><tr>
								<th>
									<span class="requiredField">*</span>用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20" onblur="checkUsername()" value="<s:property value="#session.existUser.username"/>"/>
									<span id="spanusername" ></span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off" onblur="checkpassword()"/>
									<span><s:fielderror fieldName="password"/></span><span id="spanpassword" style="color:red;font-weight:bold"></span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>确认密码:
								</th>
								<td>
									<input id="repassword" type="password" name="repassword" class="text" maxlength="20" autocomplete="off" onblur="checkrepassword()"/>
									<span id="spanrepassword" style="color:red;font-weight:bold"></span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>E-mail:
								</th>
								<td>
									<input type="text" id="email" name="email" class="text" maxlength="200" onblur="checkemail()" value="<s:property value="#session.existUser.email"/>">
									<span><s:fielderror fieldName="email"/></span><span id="spanemail" ></span>
								</td>
							</tr>
									<tr>
										<th>
											姓名:
										</th>
										<td>
												<input type="text" id="name" name="name" class="text" maxlength="200" onblur="checkname()" value="<s:property value="#session.existUser.name"/>" />
												<span><s:fielderror fieldName="name"/></span><span id="spanname"></span>
										</td>
									</tr>
									
									<tr>
										<th>
											电话:
										</th>
										<td>
												<input type="text" id="phone" name="phone" class="text" onblur="checkphone()" value="<s:property value="#session.existUser.phone"/>" />
												<span id="spanphone"></span>
										</td>
									</tr>
									
									<tr>
										<th>
											地址:
										</th>
										<td>
												<input type="text" id="address" name="addr" class="text" maxlength="200" onblur="checkaddress()"  value="<s:property value="#session.existUser.addr"/>"/>
												<span><s:fielderror fieldName="addr"/></span><span id="spanaddress"></span>
										</td>
									</tr>
								
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<button type="submit" id="sub" class="submit" value="同意以下协议并注册">修改</button>
								</td>
							</tr>
							
							
						</tbody></table>
						
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="tail.jsp" %>
	
<div id="_my97DP" style="position: absolute; top: -1970px; left: -1970px;"><iframe style="width: 190px; height: 191px;" src="./会员注册 - Powered By Mango Team_files/My97DatePicker.htm" frameborder="0" border="0" scrolling="no"></iframe></div></body></html>