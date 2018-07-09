<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp"%>
<div class="form-row">
	<div class="col-auto">
		<label class="sr-only" for="inlineFormInput">菜单名称</label> <input
			class="form-control" id="menuText" placeholder="菜单名称" type="text">
	</div>
	<div class="col-auto">
		<p:action url="/menu/data">
			<button class="btn btn-success btn-sm" onclick="query();">
				<i class=" mdi mdi-search-web m-r-5"></i> <span>查询</span>
			</button>
		</p:action>
		<p:action url="/menu/save">
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
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
							<input name="id" type="hidden"> <input name="text"
								class="form-control" placeholder="输入菜单名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">菜单地址</label>
						<div class="col-9">
							<input name="url" class="form-control" placeholder="输入菜单地址"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">图标</label>
						<div class="col-9">
							<input name="icon" class="form-control" placeholder="输入图标"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">排序字</label>
						<div class="col-9">
							<input name="goorder" class="form-control" placeholder="输入排序字"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">描述</label>
						<div class="col-9">
							<input name="desc" class="form-control" placeholder="输入描述"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">父级菜单</label>
						<div class="col-9">
							<select name="parentid" class="form-control">
								<option value="0">选择菜单</option>
								<c:forEach items="${select_menus }" var="menu">
									<option value="${menu.id }">${menu.text }</option>
								</c:forEach>
							</select>
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
				<h4>新增菜单</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="addForm">
					<div class="form-group row">
						<label class="col-3 col-form-label">名称</label>
						<div class="col-9">
							<input name="id" type="hidden"> <input name="text"
								class="form-control" placeholder="输入菜单名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">菜单地址</label>
						<div class="col-9">
							<input name="url" class="form-control" placeholder="输入菜单地址"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">图标</label>
						<div class="col-9">
							<input name="icon" class="form-control" placeholder="输入图标"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">排序字</label>
						<div class="col-9">
							<input name="goorder" class="form-control" placeholder="输入排序字"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">描述</label>
						<div class="col-9">
							<input name="desc" class="form-control" placeholder="输入描述"
								type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">父级菜单</label>
						<div class="col-9">
							<select name="parentid" class="form-control">
								<option value="0">选择菜单</option>
								<c:forEach items="${select_menus }" var="menu">
									<option value="${menu.id }">${menu.text }</option>
								</c:forEach>
							</select>
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

<div class="modal" id="actionManagerModel" tabindex="-1" role="dialog"
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
				<h4>
					<span id="action_menu_name"></span>资源管理
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-row">
					<div class="col-auto">
						<p:action url="/menu/action/add">
							<button class="btn btn-success btn-sm" onclick="addAction();">
								<i class="fa fa-plus"></i> <span>新增</span>
							</button>
						</p:action>
					</div>
				</div>
				<div class="row">
					<table id="actionTable"></table>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal" id="addActionModel" role="dialog"
	aria-labelledby="addActionModel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="showManagerModel();" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>新增资源</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="addActionForm">
					<input type="hidden" name="menuId" id="AddmenuId"/>
					<div class="form-group row">
						<label class="col-3 col-form-label">资源名称</label>
						<div class="col-9">
							<input name="name" class="form-control" placeholder="输入资源名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">资源地址</label>
						<div class="col-9">
							<input name="url" class="form-control" placeholder="输入资源地址"
								type="text">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="saveAction()">保存</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="updateActionModel" role="dialog"
	aria-labelledby="updateActionModel" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog custom-dialog succ-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="showManagerModel();" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4>修改资源</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="updateActionForm">
					<input type="hidden" name="menuId"/>
					<input type="hidden" name="id"/>
					<div class="form-group row">
						<label class="col-3 col-form-label">资源名称</label>
						<div class="col-9">
						<input name="name"
								class="form-control" placeholder="输入资源名称" type="text">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-3 col-form-label">资源地址</label>
						<div class="col-9">
							<input name="url" class="form-control" placeholder="输入资源地址"
								type="text">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn w-lg btn-rounded btn-custom" onclick="updateActionGo()">保存</button>
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
							url : "${path}/menu/data",
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
										field : 'm_text',
										title : '菜单名称',

									},
									{
										field : 'm_url',
										title : '菜单地址',

									},
									{
										field : 'm_icon',
										title : '图标',

									},
									{
										field : 'm_desc',
										title : '描述',
									},
									{
										field : 'm_goorder',
										title : '排序字',
									},
									{
										field : 'id',
										title : '操作',
										formatter : function(value, row) {
											var result = "";
											<p:action url="/menu/delete">
											result += '<button onclick="deleteMenu('
													+ value
													+ ')" class="btn btn-icon  btn-danger btn-sm"> <i class="dripicons-trash"></i>删除 </button>&nbsp;&nbsp;';
											</p:action>
											<p:action url="/menu/update">
											result += '<button onclick="updateMenu('
													+ value
													+ ')" class="btn btn-icon  btn-warning btn-sm"> <i class="dripicons-document-edit"></i>修改</button>&nbsp;&nbsp;';
											</p:action>
											<p:action url="/menu/action">
											result += '<button onclick="actionMenu('
													+ value
													+ ')" class="btn btn-icon  btn-success btn-sm"> <i class=" dripicons-paperclip"></i>资源管理</button>';
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
									"text" : $("#menuText").val()
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

	var queryAction = function(id) {
		//先销毁表格  
		$('#actionTable').bootstrapTable('destroy');
		//初始化表格,动态从服务器加载数据  
		$("#actionTable")
				.bootstrapTable(
						{
							method : "post",
							url : "${path}/menu/action/data",
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
										title : '资源名称',

									},
									{
										field : 'a_url',
										title : '资源地址',

									},
									{
										field : 'id',
										title : '操作',
										formatter : function(value, row) {
											var result = "";
											<p:action url="/menu/action/delete">
											result += '<button onclick="deleteAction('
													+ value
													+ ')" class="btn btn-icon  btn-danger btn-sm"> <i class="dripicons-trash"></i>删除 </button>&nbsp;&nbsp;';
											</p:action>
											<p:action url="/menu/action/update">
											result += '<button onclick="updateAction('
													+ value
													+ ')" class="btn btn-icon  btn-warning btn-sm"> <i class="dripicons-document-edit"></i>修改</button>&nbsp;&nbsp;';
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
									"menu_id" : id
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

	var updateMenu = function(id) {
		post("${path}/menu/find_id", {
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

	var updateAction = function(id) {
		post("${path}/menu/action/find_id", {
			id : id
		}, function(data) {
			$("#updateActionModel").initValue(data.data);
			$('#updateActionModel').modal({
				backdrop : 'static',
				keyboard : false
			});
			$('#actionManagerModel').modal("hide");
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
		$("#updateForm").sub("${path}/menu/update", function(data) {
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
		$("#addForm").sub("${path}/menu/save", function(data) {
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

	var deleteMenu = function(id) {
		layer.confirm('是否删除菜单？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {
			post("${path}/menu/delete", {
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

	var deleteAction = function(id) {
		layer.confirm('是否删除此资源？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {
			post("${path}/menu/action/delete", {
				id : id
			}, function(data) {
				queryAction(temp_menu_id);
				layer.msg("删除成功",{icon:1});
			}, function(data) {
				layer.alert(data.msg, {
					icon : 2
				});
			});
		}, function() {

		});
	};

	var actionMenu = function(id) {
		queryAction(id);
		$('#actionManagerModel').modal({
			backdrop : 'static',
			keyboard : false
		});
		temp_menu_id = id;
	};
	
	var temp_menu_id = null;
	
	var addAction = function(){
		$('#actionManagerModel').modal("hide")
		$('#addActionModel input').val("");
		$('#addActionModel').modal({
			backdrop : 'static',
			keyboard : false
		});
		$("#AddmenuId").val(temp_menu_id);
	}
	var showManagerModel = function(){
		$('#actionManagerModel').modal({
			backdrop : 'static',
			keyboard : false
		});
	};
	
	var saveAction = function(){
		$("#addActionForm").sub("${path}/menu/action/add",function(data){
			$('#addActionModel').modal("hide");
			queryAction(temp_menu_id);
			layer.msg("添加成功",{icon:1});
			showManagerModel();
		},function(data){
			layer.alert(data.msg,{icon:2});
		});
	}
	
	var updateActionGo = function(){
		$("#updateActionForm").sub("${path}/menu/action/update",function(data){
			$('#updateActionModel').modal("hide");
			queryAction(temp_menu_id);
			layer.msg("修改成功",{icon:1});
			showManagerModel();
		},function(data){
			layer.alert(data.msg,{icon:2});
		});
	}
</script>