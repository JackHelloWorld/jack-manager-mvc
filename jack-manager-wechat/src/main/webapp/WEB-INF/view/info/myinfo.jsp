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
<title>我的</title>
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
					<div class="list-group">
						<div class="list-group-item">
							<div class="left-t">公司编码</div>
							<div class="right-t" id="companyId"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">户号</div>
							<div class="right-t" id="no"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">名称</div>
							<div class="right-t" id="name"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">营业厅</div>
							<div class="right-t" id="lobby"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">片区</div>
							<div class="right-t" id="district"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">小区</div>
							<div class="right-t" id="area"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">详细地址</div>
							<div class="right-t" id="addr"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">开户时间</div>
							<div class="right-t" id="createtime"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">身份证号</div>
							<div class="right-t" id="idcard"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item" onclick="updateEmail();">
							<div class="left-t">邮箱地址</div>
							<div class="right-t">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</div>
							<div class="right-t" id="email"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item" onclick="updatePhone();">
							<div class="left-t">电话号码</div>
							<div class="right-t">
								<span class="glyphicon glyphicon-chevron-right"></span>
							</div>
							<div class="right-t" id="phone"></div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">预存余额</div>
							<div class="right-t" id="yucun"
								style="color: red; font-weight: bold;">0.00</div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">充值余额</div>
							<div class="right-t" id="money"
								style="color: red; font-weight: bold;">0.00</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="${path}/static/js/jquery.min.js-v=2.1.4"></script>
	<script src="${path}/static/js/bootstrap.min.js-v=3.3.5"></script>
	<script src="${path}/static/js/plugins/peity/jquery.peity.min.js"></script>
	<script src="${path}/static/js/content.min.js-v=1.0.0"></script>
	<script src="${path}/static/js/demo/peity-demo.min.js"></script>
	<script	src="${path}/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="${path}/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${path}/static/js/plugins/layer/layer.js"></script>
	<script src="${path}/static/js/tools.js"></script>
	<script src="${path}/static/js/plugins/treeview/bootstrap-treeview.js"></script>
	<script src="${path}/static/js/demo/treeview-demo.min.js"></script>
	<script src="${path}/static/plugins/laydate/laydate.js"></script>
	<script src="${path}/static/js/jquery.jqprint-0.3.js"></script>
	<script src="${path}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
</body>
<script type="text/javascript">
	var wx_open_id = "${open_id}";
	$(function() {
		post("${path}/wechat/personal_center/info_data", {}, function(data) {
			$("#companyId").html(data.data.companyId);
			$("#phone").html(data.data.phone);
			$("#addr").html(data.data.addr);
			$("#no").html(data.data.no);
			$("#name").html(data.data.name);
			$("#lobby").html(data.data.lobbyName);
			$("#idcard").html(data.data.idcard);
			$("#email").html(data.data.email);
			$("#phone").html(data.data.phone);
			$("#yucun").html(data.data.yucun);
			$("#money").html(data.data.money);
			$("#district").html(data.data.districtName);
			$("#area").html(data.data.areaName);
			$("#createtime").html(new Date(data.data.createtime).format("yyyy年MM月dd日"));
		}, function(data) {
			layer.msg(data.msg);
		});
	});
	var updateEmail = function() {
		window.location.href = "${path}/to_page?page=info/updateEmail&open_id=${open_id}";
	}
	var updatePhone = function() {
		window.location.href = "${path}/to_page?page=info/updatePhone&open_id=${open_id}";
	}
</script>
</html>
