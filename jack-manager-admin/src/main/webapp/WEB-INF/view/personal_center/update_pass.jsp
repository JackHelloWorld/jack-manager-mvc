<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>密码修改</title>
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
							<a href="${path }/index" class="text-success"> <span><img
									src="${path}/static/images/logo.png" alt="" height="26"></span>
							</a>
						</h2>

						<form id="updateForm" class="" action="javascript:;">

							<div class="form-group m-b-20 row">
								<div class="col-12">
									<label for="login_name">原密码</label> <input class="form-control"
										type="password" id="old_pwd" name="old_pwd" placeholder="请输入原密码">
								</div>
							</div>

							<div class="form-group row m-b-20">
								<div class="col-12">
									<label for="login_pwd">新密码</label> <input class="form-control"
										type="password" name="new_pwd" required="" id="new_pwd"
										placeholder="请输入新密码">
								</div>
							</div>
							<div class="form-group row m-b-20">
								<div class="col-12">
									<label for="login_code">确认密码</label> <input class="form-control"
										type="password" required="" id="pwd_code"
										placeholder="请输入确认密码">
								</div>
							</div>

							<div class="form-group row text-center m-t-10">
								<div class="col-12">
									<a href="javascript:;" class="btn btn-block btn-custom waves-effect waves-light"
										onclick="updatePwd()">修改</a>
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

var updatePwd = function(){
	if($("#new_pwd").val() != $("#pwd_code").val()){
		layer.msg("密码输入不一致",{icon:7});
		return;
	}
	$("#updateForm").sub("${path}/update_pwd",function(data){
		layer.alert("密码修改成功",{icon:1,title:"系统提示",end:function(){
			location.href="${path}/index"
		}});
	},function(data){
		layer.alert(data.msg,{icon:2,title:"系统提示"});
	},"修改密码中...");
}
</script>
</body>
</html>