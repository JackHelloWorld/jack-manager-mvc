<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp"%>
<div class="form-row">
	<div class="col-auto">
		<label class="sr-only" for="q_name">公司名称</label> <input
			class="form-control" id="q_name" placeholder="公司名称" type="text">
	</div>
	<div class="col-auto">
		<label class="sr-only" for="q_status">公司状态</label> 
		<select class="form-control" id="q_status" placeholder="公司状态">
			<option value="0">正常</option>
			<option value="1">已到期</option>
			<option value="2">已删除</option>
		</select>
	</div>
	<div class="col-auto">
		<p:action url="/company/data">
			<button class="btn btn-success btn-sm" onclick="query();">
				<i class=" mdi mdi-search-web m-r-5"></i> <span>查询</span>
			</button>
		</p:action>
		<p:action url="/company/save">
			<button class="btn btn-success btn-sm" onclick="add();">
				<i class="fa fa-plus"></i> <span>新增</span>
			</button>
		</p:action>
	</div>
</div>
<table id="dataTable"></table>
<%@include file="../footer.jsp"%>


<div class="modal" id="editModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<input type="hidden" name="id">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>修改公司</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="updateForm">
				<input name="id" type="hidden"/>
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
						<input name="name" class="form-control" placeholder="输入名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">公司描述</label>
						<div class="col-9">
							<input name="description" class="form-control" placeholder="输入公司描述"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">管理员登陆名</label>
						<div class="col-9">
							<input name="managerName" class="form-control" placeholder="输入管理员登陆名"
								type="text">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="update()">保存</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="addModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<input type="hidden" name="id">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>新增公司</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="addForm">
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
						<input name="name" class="form-control" placeholder="输入名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">公司描述</label>
						<div class="col-9">
							<input name="description" class="form-control" placeholder="输入公司描述"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">管理员登陆名</label>
						<div class="col-9">
							<input name="managerName" class="form-control" placeholder="输入管理员登陆名"
								type="text">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="save()">保存</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="renewalModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<input type="hidden" name="id">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>公司续期</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="renewalForm">
					<input type="hidden" name="id"/>
					<div class="form-group row">
						<label class="col-3 col-form-label">天数</label>
						<div class="col-9">
						<input name="day" class="form-control" placeholder="输入续期天数(整数)" type="number" step="1">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="renewalSave()">保存</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	var query = function() {
		//先销毁表格  
		$('#dataTable').bootstrapTable('destroy');
		//初始化表格,动态从服务器加载数据  
		$("#dataTable").bootstrapTable({
			method : "post",
			url : "${path}/company/data",
			striped : true, //表格显示条纹  
			pagination : true, //启动分页  
			pageSize : 10, //每页显示的记录数  
			pageNumber : 1, //当前第几页  
			pageList : [ 10, 20, 50, 100 ], //记录数可选列表  
			height : $(window).height() * 0.7,
			search : false, //是否启用查询  
			cache : false,
			selectItemName : "id",
			paginationPreText : '上一页',
			paginationNextText : '下一页',
			showColumns : false, //显示下拉框勾选要显示的列  
			showRefresh : false, //显示刷新按钮 ,
			clickToSelect : true,
			contentType : "application/x-www-form-urlencoded",
			columns : [
					{
						field : 'a_name',
						title : '公司名称',

					},
					{
						field : 'a_desc',
						title : '公司描述',

					},
					{
						field : 'a_manager_name',
						title : '管理员登陆名',

					},
					{
						field : 'create_time',
						title : '创建时间',
						formatter : function(value, row) {
							if(value){
								return new Date(value).format("yyyy-MM-dd hh:mm:ss");
							}
						}
					},
					{
						field : 'expire_time',
						title : '到期时间',
						formatter : function(value, row) {
							if(value){
								return new Date(value).format("yyyy-MM-dd hh:mm:ss");
							}
						}
					},
					{
						field : 'a_status',
						title : '状态',
						formatter : function(value, row) {
							switch (value) {
							case 0:
								return "正常";
								
							case 1:
								return "已过期";
								
							case 2:
								return "已删除";

							default:
								return "未知";
							}
						}
					},
					{
						field : 'id',
						title : '操作',
						formatter : function(value, row) {
							
							if(row.a_status == 2){
								return '无';
							}
							var result = "";
							<p:action url="/company/delete">
							result += '<button onclick="deleteCompany('
									+ value
									+ ')" class="btn btn-icon  btn-danger btn-sm"> <i class="dripicons-trash"></i>删除 </button>&nbsp;&nbsp;';
							</p:action>
							<p:action url="/company/update">
							result += '<button onclick="updateCompany('
									+ value
									+ ')" class="btn btn-icon  btn-warning btn-sm"> <i class="dripicons-document-edit"></i>修改</button>&nbsp;&nbsp;';
							</p:action>
							<p:action url="/company/renewal">
							result += '<button onclick="renewal('
									+ value
									+ ')" class="btn btn-icon btn-success btn-sm"> <i class=" dripicons-paperclip"></i>续期</button>';
							</p:action>
							return result;
						}
					}, ],
			sidePagination : "server", //表示服务端请求  
			queryParamsType : "undefined",

			queryParams : function queryParams(params) { //设置查询参数  
				var param = {
					pageNumber : params.pageNumber,
					pageSize : params.pageSize,
					"name" : $("#q_name").val(),
					"status" : $("#q_status").val(),
				};
				return param;
			},
			onLoadSuccess : function(data) { //加载成功时执行  
				if (data.code && data.code == -1) {
					layer.alert(data.msg, {
						icon : 2
					});
				}
			},
			onLoadError : function() { //加载失败时执行  
				layer.msg("加载数据失败", {
					time : 1500,
					icon : 2
				});
			}
		});
	};

	$(function() {
		query();
	});

	var updateCompany = function(id) {
		post("${path}/company/find_id", {
			id : id
		}, function(data) {
			$("#editModel input").val("");
			$("#editModel").initValue(data.data);
			$('#editModel').modal({
				backdrop : 'static',
				keyboard : false
			});
		}, function(data) {
			layer.alert(data.msg, {
				icon : 2
			});
		});
	};
	
	var renewal = function(id) {
		$("#renewalModel input").val("");
		$("#renewalModel input[name='id']").val(id);
		$('#renewalModel').modal({
			backdrop : 'static',
			keyboard : false
		});
	};

	var add = function(id) {
		$('#addModel').modal({
			backdrop : 'static',
			keyboard : false
		});
	};

	var update = function() {
		$("#updateForm").sub("${path}/company/update", function(data) {
			$('#editModel').modal("hide");
			layer.alert("修改成功", {
				icon : 1,
				end : function() {
					location = location;
				}
			});
		}, function(data) {
			layer.alert(data.msg, {
				icon : 2
			});
		});
	}

	var save = function() {
		$("#addForm").sub("${path}/company/save", function(data) {
			$('#addModel').modal("hide");
			layer.alert("添加成功", {
				icon : 1,
				end : function() {
					location = location;
				}
			});
		}, function(data) {
			layer.alert(data.msg, {
				icon : 2
			});
		});
	}

	var renewalSave = function() {
		$("#renewalForm").sub("${path}/company/renewal", function(data) {
			$('#addModel').modal("hide");
			layer.alert("续期成功", {
				icon : 1,
				end : function() {
					location = location;
				}
			});
		}, function(data) {
			layer.alert(data.msg, {
				icon : 2
			});
		});
	}

	var deleteCompany = function(id) {
		layer.confirm('是否删除此公司？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {
			post("${path}/company/delete", {
				id : id
			}, function(data) {
				layer.alert("删除成功", {
					icon : 1,
					end : function() {
						location = location;
					}
				});
			}, function(data) {
				layer.alert(data.msg, {
					icon : 2
				});
			});
		}, function() {

		});
	};
</script>