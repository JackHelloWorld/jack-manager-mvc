<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="后台系统管理"
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<link rel="shortcut icon" href="${path}/static/images/favicon.ico">

<!-- App css -->
<link href="${path}/static/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/static/css/icons.css" rel="stylesheet"
	type="text/css" />
<link href="${path}/static/css/style.css" rel="stylesheet"
	type="text/css" />

<script src="${path}/static/js/modernizr.min.js"></script>

</head>

<body>

	<!-- Begin page -->
	<div class="accountbg"
		style="background: url('${path}/static/images/bg-1.jpg');background-size: cover;"></div>

	<div class="wrapper-page account-page-full"
		style="width: 100%; max-width: 540px;">

		<div class="card">
			<div class="card-block">

				<div class="account-box">

					<div class="card-box p-5">
						<h2 class="text-uppercase text-center pb-4">
							<a href="index.html" class="text-success"> <span><img
									src="${path}/static/images/logo.png" alt="" height="26"></span>
							</a>
						</h2>

						<form id="loginForm" class="" action="javascript:;">

							<div class="form-group m-b-20 row">
								<div class="col-12">
									<label for="login_name">登录名</label> <input class="form-control"
										type="text" id="login_name" name="login_name" placeholder="请输入登录名">
								</div>
							</div>

							<div class="form-group row m-b-20">
								<div class="col-12">
									<label for="login_pwd">密码</label> <input class="form-control"
										type="password" name="login_pwd" required="" id="login_pwd"
										placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group row m-b-20">
								<div class="col-12">
									<label for="login_code">验证码</label> <input class="form-control"
										type="text" name="login_code" required="" id="login_code"
										placeholder="请输入验证码">
								</div>
							</div>

							<div class="form-group row m-b-20">
								<div class="col-12">
									<img alt="" id="generate_code_id" src="${path }/generate_code"
										onclick="generate_code(this)" />
								</div>
							</div>

							<div class="form-group row text-center m-t-10">
								<div class="col-12">
									<a href="javascript:;" class="btn btn-block btn-custom waves-effect waves-light"
										onclick="login()">登录</a>
								</div>
							</div>

						</form>

					</div>
				</div>

			</div>
		</div>

		<div class="m-t-40 text-center">
			<p class="account-copyright">企业后台管理系统</p>
		</div>

	</div>
	<!-- jQuery  -->
	<script src="${path}/static/js/jquery.min.js"></script>
	<script src="${path}/static/js/popper.min.js"></script>
	<script src="${path}/static/js/bootstrap.min.js"></script>
	<script src="${path}/static/js/waves.js"></script>
	<script src="${path}/static/js/jquery.slimscroll.js"></script>
<script type="text/javascript" src="${path }/static/js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="${path }/static/js/tools.js"></script>

	<!-- App js -->
	<script src="${path}/static/js/jquery.core.js"></script>
	<script src="${path}/static/js/jquery.app.js"></script>
<script type="text/javascript">

var login = function(){
	$("#loginForm").sub("${path}/login",function(data){
		location.href="${path}/index"
	},function(data){
		layer.alert(data.msg,{icon:2,title:"登录错误"});
		$("#generate_code_id").get(0).click();
	},"登录中...");
}
var generate_code = function(t){
	$(t).attr("src","${path}/generate_code?time="+new Date().getTime());
}
</script>
</body>
</html>