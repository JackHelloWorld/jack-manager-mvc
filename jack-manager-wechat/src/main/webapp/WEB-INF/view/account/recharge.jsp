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
<title>充值</title>
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
								<input class="form-control" placeholder="请输入充值金额" name="money" id="money"
										type="number">
							</div>
						</div>
						<div style="width: 100%">
							<div style="width: 48%;float: left; padding-right: 10px">
								<a href="javascript:;" style="width: 100%" money="50" class="btn btn-white btn-lg">50元</a>
							</div>
							<div style="width: 48%;float: left;">
								<a href="javascript:;" style="width: 100%" money="100" class="btn btn-white btn-lg">100元</a>
							</div>
						</div>
						<div style="width: 100%">
							<div style="width: 48%;float: left;padding-right: 10px">
								<a href="javascript:;" style="width: 100%" money="150" class="btn btn-white btn-lg">150元</a>
							</div>
							<div style="width: 48%;float: left;">
								<a href="javascript:;" style="width: 100%" money="200" class="btn btn-white btn-lg">200元</a>
							</div>
							<div style="clear: both;"></div>
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
	<p style="position: fixed; bottom: 0px; width: 100%;" onclick="recharge();">
		<button type="button" class="btn btn-block  btn-primary">充值</button>
	</p>
</body>
</html>
<script type="text/javascript">
	var wx_open_id = "oua43v4gY0nHDDMazNx54zw-2LII";
	$("a[money]").click(function(){
		$("a[money]").removeClass("btn-primary").addClass("btn-white");
		$(this).removeClass("btn-white").addClass("btn-primary");
		$("#money").val($(this).attr("money"));
	});
	var recharge = function() {
		var money = $("#money").val();
		if(money==''){
			swal("操作错误", "请输入充值金额", "error");
			return;
		}
		post("${path}/wechat/personal_center/charge_pay_info",{money:money},function(data){
			 if (typeof WeixinJSBridge == "undefined"){    
			       if( document.addEventListener ){    
			           document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);    
			       }else if (document.attachEvent){    
			           document.attachEvent('WeixinJSBridgeReady', onBridgeReady);     
			           document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);    
			       }    
			    }else{    
			       onBridgeReady(data.data);    
			    }
		},function(data){
			swal("操作错误", data.msg, "error");
		});
	}
	
	//开始支付  
	function onBridgeReady(data){    
	    WeixinJSBridge.invoke(    
	        'getBrandWCPayRequest',data,
	        function(res){         
	            if(res.err_msg == "get_brand_wcpay_request:ok" ) {    
	            	swal("操作成功", "充值成功", "success");
	            }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {  
	                layer.msg("已取消")
	            }else{  
	               //支付失败 
	               swal("操作错误", res.err_msg, "error");
	            }       
	        }    
	    );     
	 }  
</script>
