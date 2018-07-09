<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<title>客户信息绑定</title>
<link href="${path}/static/css/bootstrap.min.css-v=3.3.5.css"
	rel="stylesheet">
<link href="${path}/static/css/font-awesome.min.css-v=4.4.0.css"
	rel="stylesheet">
<link href="${path}/static/css/plugins/iCheck/custom.css"
	rel="stylesheet">
<link href="${path}/static/css/animate.min.css" rel="stylesheet">
<link href="${path}/static/css/style.min1.css" rel="stylesheet">
<link rel="stylesheet"
	href="${path}/static/css/plugins/bootstrap-table/bootstrap-table.min.css" />
<link href="${path}/static/css/plugins/treeview/bootstrap-treeview.css"
	rel="stylesheet">
<link href="${path}/static/css/plugins/sweetalert/sweetalert.css"
	rel="stylesheet">
<style type="text/css">
.gohome {
	display: none
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content"
		style="margin-left: 10px; margin-right: 10px;">
		<div class="row">
			<div class="ibox float-e-margins col-sm-12">
				<div class="ibox-content">
					<form class="form-horizontal" id="editForm">
						<div class="form-group">
							<div class="col-sm-12">
								<div class="input-group m-b">
									<span class="input-group-btn">
										<button type="button" class="btn btn-link">
											用户户号<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
										</button>
									</span> <input class="form-control" placeholder="请输入户号" name="ctu_no"
										type="number">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="input-group m-b">
									<span class="input-group-btn">
										<button type="button" class="btn btn-link">
											电话号码<span class="glyphicon glyphicon-phone"
												aria-hidden="true"></span>
										</button>
									</span> <input class="form-control" placeholder="请输入电话号码" name="phone"
										type="text">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="input-group m-b">
									<span class="input-group-btn">
										<button type="button" class="btn btn-link">
											公司名称<span class="glyphicon glyphicon-th-large"
												aria-hidden="true"></span>
										</button>
									</span> <input class="form-control" placeholder="请输入公司名称"
										name="company_name" type="text">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="input-group m-b">
									<span class="input-group-btn">
										<button type="button" class="btn btn-link">
											公司编码<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
										</button>
									</span> <input class="form-control" placeholder="请输入公司名称"
										name="company_no" type="number">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="${path}/static/js/jquery.min.js-v=2.1.4"></script>
	<script src="${path}/static/js/bootstrap.min.js-v=3.3.5"></script>
	<script src="${path}/static/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="${path}/static/js/content.min.js-v=1.0.0"></script>
	<script src="${path}/static/js/demo/peity-demo.min.js"></script>
	<script
		src="${path}/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${path}/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${path}/static/js/plugins/layer/layer.js"></script>
	<script src="${path}/static/js/tools.js"></script>
	<script src="${path}/static/js/plugins/treeview/bootstrap-treeview.js"></script>
	<script src="${path}/static/js/demo/treeview-demo.min.js"></script>
	<script src="${path}/static/plugins/laydate/laydate.js"></script>
	<script src="${path}/static/js/jquery.jqprint-0.3.js"></script>
	<script src="${path}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<p style="position: fixed; bottom: 0px; width: 100%;" onclick="bind();">
		<button type="button" class="btn btn-block  btn-primary">绑定</button>
	</p>
</body>
</html>
<script type="text/javascript">
	var wx_open_id = "${open_id}";
	var bind = function() {
		$("#editForm").sub("${path}/wechat/bind_go", function(data) {
			swal("用户绑定成功", "", "success");
		}, function(data) {
			swal("操作错误", data.msg, "error");
		});
	}
</script>
