<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp"%>
<div class="form-row">
	<div class="col-auto">
		<label class="sr-only" for="q_name">用户名称</label> <input
			class="form-control" id="q_name" placeholder="用户名称" type="text">
	</div>
	<div class="col-auto">
		<p:action url="/user/data">
			<button class="btn btn-success btn-sm" onclick="query();">
				<i class=" mdi mdi-search-web m-r-5"></i> <span>查询</span>
			</button>
		</p:action>
		<p:action url="/user/save">
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
				<h4>修改菜单</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="updateForm">
				<input name="id" type="hidden"/>
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
						<input name="nickName" class="form-control" placeholder="输入名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">登录名</label>
						<div class="col-9">
							<input name="loginName" class="form-control" placeholder="输入登录名"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">描述</label>
						<div class="col-9">
							<input name="adminDesc" class="form-control" placeholder="输入描述"
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
				<h4>新增用户</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="addForm">
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
						<input name="nickName" class="form-control" placeholder="输入名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">登录名</label>
						<div class="col-9">
							<input name="loginName" class="form-control" placeholder="输入登录名"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">描述</label>
						<div class="col-9">
							<input name="adminDesc" class="form-control" placeholder="输入描述"
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

<div class="modal" id="roleManagerModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>
					<span id="action_menu_name"></span>授权管理
				</h4>
			</div>
			<div class="modal-body">
				<form id="roleManagerModelForm" class="row">
				<input type="hidden" id="role_manager_user_id" name="id">
					<table class="table table-hover table-striped">
					<thead>
					<tr>
						<th>菜单</th>
						<th>资源信息</th>
					</tr>
					</thead>
						<tbody>
						<c:forEach items="${roleMenus }" var = "menu">
							<tr>
								<td><h4><input type="checkbox" data-type="menu" name="menus" data-id="${menu.id }" value="${menu.id }">${menu.text }</h4></td>
								<td>
									<c:forEach items="${menu.actions }" var="nas">
										<input type="checkbox" data-type="action" name="actions" data-id="${nas.id }" value="${nas.id }">${nas.name }&nbsp;&nbsp;
									</c:forEach>
								</td>
							</tr>
								<c:forEach items="${menu.nodes }" var = "node">
									<tr>
										<td><h4>&nbsp;&nbsp;<input type="checkbox" name="menus" data-type="menu" data-id="${node.id }" value="${node.id }">${node.text }</h4></td>
										<td>
											<c:forEach items="${node.actions }" var="nas">
												<input type="checkbox" data-type="action" name="actions" data-id="${nas.id }" value="${nas.id }">${nas.name }&nbsp;&nbsp;
											</c:forEach>
										</td>
									</tr>
										<c:forEach items="${node.nodes }" var = "noden">
											<tr>
												<td><h4>&nbsp;&nbsp;&nbsp;&nbsp;<input name="menus" data-type="menu" data-id="${noden.id }" type="checkbox" value="${noden.id }">${noden.text }</h4></td>
												<td>
													<c:forEach items="${noden.actions }" var="nas">
														<input type="checkbox" data-type="action" name="actions" data-id="${nas.id }" value="${nas.id }">${nas.name }&nbsp;&nbsp;
													</c:forEach>
												</td>
											</tr>
										</c:forEach>
								</c:forEach>
						</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="roleAction()">授权</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	var query = function() {
		//先销毁表格  
		$('#dataTable').bootstrapTable('destroy');
		//初始化表格,动态从服务器加载数据  
		$("#dataTable")
				.bootstrapTable(
						{
							method : "post",
							url : "${path}/user/data",
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
										field : 'nick_name',
										title : '用户名称',

									},
									{
										field : 'login_name',
										title : '登录名',

									},
									{
										field : 'admin_desc',
										title : '描述信息',

									},
									{
										field : 'createtime',
										title : '创建时间',
										formatter : function(value, row) {
											if(value){
												return new Date(value).format("yyyy-MM-dd hh:mm:ss");
											}
										}
									},
									{
										field : 'last_login_time',
										title : '最后一次登录时间',
										formatter : function(value, row) {
											if(value){
												return new Date(value).format("yyyy-MM-dd hh:mm:ss");
											}
										}
									},
									{
										field : 'id',
										title : '操作',
										formatter : function(value, row) {
											var result = "";
											<p:action url="/user/delete">
											result += '<button onclick="deleteUser('
													+ value
													+ ')" class="btn btn-icon  btn-danger btn-sm"> <i class="dripicons-trash"></i>删除 </button>&nbsp;&nbsp;';
											</p:action>
											<p:action url="/user/update">
											result += '<button onclick="updateUser('
													+ value
													+ ')" class="btn btn-icon  btn-warning btn-sm"> <i class="dripicons-document-edit"></i>修改</button>&nbsp;&nbsp;';
											</p:action>
											<p:action url="/user/role">
											result += '<button onclick="userRole('
													+ value
													+ ')" class="btn btn-icon btn-success btn-sm"> <i class=" dripicons-paperclip"></i>授权管理</button>';
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
									"name" : $("#q_name").val()
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

	var updateUser = function(id) {
		post("${path}/user/find_id", {
			id : id
		}, function(data) {
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

	var add = function(id) {
		$('#addModel').modal({
			backdrop : 'static',
			keyboard : false
		});
	};

	var update = function() {
		$("#updateForm").sub("${path}/user/update", function(data) {
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
		$("#addForm").sub("${path}/user/save", function(data) {
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

	var deleteUser = function(id) {
		layer.confirm('是否删除用户？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {
			post("${path}/user/delete", {
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

	var roleAction = function(){
		$("#role_manager_user_id").val(temp_user_id);
		$("#roleManagerModelForm").sub("${path}/user/role",function(data){
			layer.msg("授权成功",{icon:1});
			$('#roleManagerModel').modal("hide");
		},function(data){
			layer.alert(data.msg,{icon:2});
		});
	}

	var userRole = function(id) {
		post("${path}/user/get_user_role",{id:id},function(data){
			var ins = $("#roleManagerModel input[type='checkbox']");
			for(var i = 0;i<ins.length;i++){
				ins[i].checked = false;
			}
			for(var i = 0;i<data.data.actionUsers.length;i++){
				var action = data.data.actionUsers[i];
				$("#roleManagerModel [data-type='action'][data-id='"+action.actionId+"'").get(0).checked = true;
			}
			for(var i = 0;i<data.data.menuUsers.length;i++){
				var menu = data.data.menuUsers[i];
				$("#roleManagerModel [data-type='menu'][data-id='"+menu.menuId+"'").get(0).checked = true;
			}
			$('#roleManagerModel').modal({
				backdrop : 'static',
				keyboard : false
			});
			temp_user_id = id;
		},function(data){
			layer.alert(data.msg,{icon:2});
		});
	};
	
	var temp_user_id = null;
</script>