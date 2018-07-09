<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="/WEB-INF/p.tld" prefix="p"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理系统</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="后台管理系统" name="description" />
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
<link rel="stylesheet"
	href="${path}/static/css/plugins/bootstrap-table/bootstrap-table.min.css" />
<script src="${path}/static/js/modernizr.min.js"></script>

</head>

<body>

	<!-- Navigation Bar-->
	<header id="topnav">
		<div class="topbar-main">
			<div class="container-fluid">

				<!-- Logo container-->
				<div class="logo">
					<!-- Image Logo -->
					<a href="${path }/index" class="logo"> <img
						src="${path}/static/images/logo_sm.png" alt="" height="26"
						class="logo-small"> <img
						src="${path}/static/images/logo.png" alt="" height="22"
						class="logo-large">
					</a>

				</div>
				<!-- End Logo container-->


				<div class="menu-extras topbar-custom">
					<ul class="list-unstyled topbar-right-menu float-right mb-0">
						<li class="menu-item">
							<!-- Mobile menu toggle--> <a class="navbar-toggle nav-link">
								<div class="lines">
									<span></span> <span></span> <span></span>
								</div>
						</a> <!-- End mobile menu toggle-->
						</li>
						<li class="dropdown notification-list hide-phone"><a
							class="nav-link dropdown-toggle waves-effect nav-user"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <i
								class="mdi mdi-earth"></i> 后台管理系统 </i>
						</a></li>

						<li class="dropdown notification-list"><a
							class="nav-link dropdown-toggle waves-effect nav-user"
							data-toggle="dropdown" href="#" role="button"
							aria-haspopup="false" aria-expanded="false"> <img
								src="${path}/static/images/users/avatar-1.jpg" alt="user"
								class="rounded-circle"> <span class="ml-1 pro-user-name">管理员
									<i class="mdi mdi-chevron-down"></i>
							</span>
						</a>
							<div class="dropdown-menu dropdown-menu-right profile-dropdown ">
								<!-- item-->
								<div class="dropdown-item noti-title">
									<h6 class="text-overflow m-0">欢迎使用 !</h6>
								</div>

								<!-- item-->
								<a href="${path }/update_pass_page"
									class="dropdown-item notify-item"> <i class="fi-cog"></i> <span>密码修改</span>
								</a>

								<!-- item-->
								<a href="${path }/logout" class="dropdown-item notify-item">
									<i class="fi-power"></i> <span>退出系统</span>
								</a>

							</div></li>
					</ul>
				</div>
				<!-- end menu-extras -->

				<div class="clearfix"></div>

			</div>
			<!-- end container -->
		</div>
		<!-- end topbar-main -->

		<div class="navbar-custom">
			<div class="container-fluid">
				<div id="navigation">
					<!-- Navigation Menu-->
					<ul class="navigation-menu">
						<li class="has-submenu"><a href="${path }/index"><i class="fa fa-server"></i>控制中心</a></li>
						<c:forEach items="${view_menus }" var="menu">
							<li class="has-submenu"><a
								href="${menu.nodes != null && fn:length(menu.nodes)>0 ? 'javascript:;' : path.concat(menu.url)}"><i
									class="${menu.icon }"></i>${menu.text }</a> <c:if
									test="${menu.nodes != null && fn:length(menu.nodes) >0}">
									<ul class="submenu">
										<c:forEach items="${menu.nodes }" var="node">
											<li
												class="${node.nodes != null && fn:length(node.nodes)>0 ? 'has-submenu' : ''}">
												<a
												href="${node.nodes != null && fn:length(node.nodes)>0 ? 'javascript:;' : path.concat(node.url)}"><i
													style="color: black;" class="${node.icon }"></i>&nbsp;&nbsp;${node.text }</a>
												<c:if
													test="${node.nodes != null && fn:length(node.nodes) >0}">
													<ul class="submenu">
														<c:forEach items="${node.nodes }" var="nn">
															<li
																class="${nn.nodes != null && fn:length(nn.nodes)>0 ? 'has-submenu' : ''}">
																<a
																href="${nn.nodes != null && fn:length(nn.nodes)>0 ? 'javascript:;' : path.concat(nn.url)}"><i
																	style="color: black;" class="${nn.icon }"></i>&nbsp;&nbsp;${nn.text }</a>
															</li>
														</c:forEach>
													</ul>
												</c:if>
											</li>
										</c:forEach>
									</ul>
								</c:if></li>
						</c:forEach>
					</ul>
					<!-- End navigation menu -->
				</div>
				<!-- end #navigation -->
			</div>
			<!-- end container -->
		</div>
		<!-- end navbar-custom -->
	</header>
	<!-- End Navigation Bar-->
	<div class="wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-12">
					<div class="page-title-box">
						<div class="btn-group pull-right">
							<ol class="breadcrumb hide-phone p-0 m-0">
								<li class="breadcrumb-item"><a href="${path }/index">JackAdmin</a></li>
								<c:forEach items="${page_title_bar }" var="menu">
									<li class="breadcrumb-item"><a href="javascript:;">${menu.text }</a></li>
								</c:forEach>
								<li class="breadcrumb-item active">${page_title }</li>
							</ol>
						</div>
						<h4 class="page-title">${page_title }</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card-box">
