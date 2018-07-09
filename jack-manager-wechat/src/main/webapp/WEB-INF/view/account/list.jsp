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
<title>账户记录</title>
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
					<div class="list-group" id="list">
					</div>
					<div style="width: 100%;text-align: center;"><a href="javascript:req_list();" more="true" id="more">加载更多</a></div>
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
	var pageNumber = 0;
	$(function() {
		req_list();
	});
	var req_list = function(){
		pageNumber++;
		var more = $("#more").attr("more");
		if(more=="false"){
			return;
		}
		post("${path}/account/record", {pageNumber:pageNumber}, function(data) {
			var list = data.data.rows;
			if(list.length==0){
				$("#more").html("没有更多").attr("more",false);
			}
			for(var i = 0;i<list.length;i++){
				var row = list[i];
				var type = '';
				 switch (row.businessCode) {
				case 1:
					type = '支付水费';
					break;
				case 6:
					type = '充值';
					break;

				default:
					break;
				}
				var html = 
					'		<div class="list-group-item">'+
					'			<div class="left-t">'+
					'				<div>'+
					'					<div>'+(row.direction==1?'收入':'支出')+'</div><br>'+
					'					<div>'+(row.direction==1?'+':'-')+'<span style="color: red">'+row.money+'&yen;</span></div>'+
					'				</div>'+
					'			</div>'+
					'			<div class="right-t">'+
					'				<div>'+
					'					<div>'+new Date(row.createTime).format('yyyy-MM-dd hh:mm:ss')+'</div><br>'+
					'					<div>'+type+'</div>'+
					'				</div>'+
					'			</div>'+
					'			<div class="clear"></div>'+
					'		</div>';
				$("#list").append(html);
			}
		}, function(data) {
			layer.msg(data.msg);
		});
	}
</script>
</html>
