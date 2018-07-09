<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<title>缴费</title>
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
							<div class="left-t">计价月份</div>
							<div class="right-t">${chargeInfo.month }</div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">费用</div>
							<div class="right-t">
								<span style="color: red; font-weight: bold;">${chargeInfo.money }</span>元
							</div>
							<div class="clear"></div>
						</div>
						<div class="list-group-item">
							<div class="left-t">违约金</div>
							<div class="right-t">
								<span style="color: red; font-weight: bold;">${chargeInfo.weiyue==null?'-':chargeInfo.weiyue }</span>元
							</div>
							<div class="clear"></div>
						</div>
						<c:forEach items="${chargeInfo.chargeInfoLadders }" var="chargeInfoLadder">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h5 class="panel-title">
										<a data-toggle="collapse" href="javascript:;">第${chargeInfoLadder.ladderLevel }阶梯</a>
										<code class="pull-right">${chargeInfoLadder.money }元</code>
									</h5>
								</div>
								<div id="" class="panel-collapse collapse">
	                                <div class="panel-body">
	                                    <table style="width: 100%">
	                                    	<tbody>
	                                    		<tr>
	                                    		<td>单价</td>
	                                    		<td>${chargeInfoLadder.price }元</td>
	                                    		<td>用量</td>
	                                    		<td>${chargeInfoLadder.doage }</td>
	                                    		</tr>
	                                    	</tbody>
	                                    </table>
	                                </div>
	                            </div>
							</div>
						</c:forEach>
						<c:if test="${chargeInfo.isgo == 0 }">
							<div class="list-group-item">
								<a href="javascript:jf(${chargeInfo.id });" style="width: 100%" class="btn btn-primary btn-lg">缴费</a>
							</div>
						</c:if>
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
</body>
<script type="text/javascript">
	var wx_open_id = "${open_id}";
	var info_back_id = "${chargeInfo.id }";
	$(".panel-heading").click(function(){
		$(this).next().toggleClass("in")
	});
	var jf = function(id){
		post("${path}/wechat/personal_center/pay_info",{id:id},function(data){
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
	};
	
	var rollback = function(){
		post("${path}/wechat/personal_center/pay_info_rollback",{id:info_back_id},function(data){
			
		},function(data){
		});
	}
	
	//开始支付  
	function onBridgeReady(data){    
	    WeixinJSBridge.invoke(    
	        'getBrandWCPayRequest',data,
	        function(res){         
	            if(res.err_msg == "get_brand_wcpay_request:ok" ) {    
	            	swal("操作成功", "缴费成功", "success");
	            }else if (res.err_msg == "get_brand_wcpay_request:cancel")  {  
	            	rollback();
	            }else{
	            	rollback();
	               //支付失败 
	               swal("操作错误", res.err_msg, "error");
	            }       
	        }    
	    );     
	 }  
</script>
</html>
