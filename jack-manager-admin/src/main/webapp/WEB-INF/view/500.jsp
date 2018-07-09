<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>系统错误</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="后台管理系统" name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="${path}/static/images/favicon.ico">

        <!-- App css -->
        <link href="${path}/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${path}/static/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="${path}/static/css/style.css" rel="stylesheet" type="text/css" />

        <script src="${path}/static/js/modernizr.min.js"></script>

    </head>

    <body>

        <!-- Begin page -->
        <div class="accountbg" style="background: url('${path}/static/images/bg-1.jpg');background-size: cover;"></div>

        <div class="wrapper-page account-page-full" style="width: 100%; max-width: 540px;">

            <div class="card">
                <div class="card-block">

                    <div class="account-box">

                        <div class="card-box p-5">
                            <h2 class="text-uppercase text-center pb-4">
                                <a href="${path }/index" class="text-success">
                                    <span><img src="${path}/static/images/logo.png" alt="" height="26"></span>
                                </a>
                            </h2>

                            <div class="text-center">
                                <h1 class="text-error">500</h1>
                                <h4 class="text-uppercase text-danger mt-3">系统错误</h4>
                                <p class="text-muted mt-3">网络错误,服务器异常</p>

                                <a class="btn btn-md btn-block btn-custom waves-effect waves-light mt-3" href="${path }/index"> 返回首页</a>
                            </div>

                        </div>
                    </div>

                </div>
            </div>

            <div class="m-t-40 text-center">
                <p class="account-copyright">后台管理系统</p>
            </div>
        </div>
        <!-- jQuery  -->
        <script src="${path}/static/js/jquery.min.js"></script>
        <script src="${path}/static/js/popper.min.js"></script>
        <script src="${path}/static/js/bootstrap.min.js"></script>
        <script src="${path}/static/js/waves.js"></script>
        <script src="${path}/static/js/jquery.slimscroll.js"></script>

        <!-- App js -->
        <script src="${path}/static/js/jquery.core.js"></script>
        <script src="${path}/static/js/jquery.app.js"></script>

    </body>
</html>