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
<title>设置邮箱</title>
<link href="${path}/static/css/bootstrap.min.css-v=3.3.5.css"
	rel="stylesheet">
<link href="${path}/static/css/font-awesome.min.css-v=4.4.0.css"
	rel="stylesheet">
<link href="${path}/static/css/plugins/iCheck/custom.css" rel="stylesheet">
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

.left-t {
	text-align: left;
	float: left;
	font-size: 15px;
	font-weight: bold;
}

.right-t {
	text-align: right;
	float: right;
}

.clear {
	clear: both;
}

.ibox-content {
	padding: 0px;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content"
		style="margin-left: 10px; margin-right: 10px;">
		<div class="row">
			<div class="ibox float-e-margins col-sm-12">
				<div class="ibox-content">
					<div class="list-group"></div>
					<div class="list-group">
						<div class="list-group-item form-group">
							<input class="form-control" id="email" placeholder="请输入邮箱" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<p style="position: fixed; bottom: 0px; width: 100%;" onclick="save();">
		<button type="button" class="btn btn-block  btn-primary">保存</button>
	</p>
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
	<script src="${path}/static/js/config.js"></script>
	<script src="${path}/static/js/jquery.jqprint-0.3.js"></script>
	<script src="${path}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript">
	var wx_open_id = "${open_id}";
	$(function() {
		post("${path}/wechat/personal_center/info_data", {}, function(data) {
			$("#email").val(data.data.email);
		}, function(data) {
			layer.msg(data.msg);
		});
	});
	var save = function(){
		var email = $("#email").val();
		if(!notNull(email)){
			swal("操作错误", "请输入邮箱", "error");
			return;
		}
		post("${path}/wechat/personal_center/update_email",{email:email},function(){
			window.location.href = "${path}/to_page?page=info/myinfo&open_id=${open_id}";
		},function(data){
			swal("操作错误", data.msg, "error");
		});
	};
	</script>
</body>
</html>
