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
<title>请稍后</title>
	</head>
	<body>
	<script src="${path}/static/js/config.js"></script>
	<script src="${path}/static/js/jquery.min.js-v=2.1.4"></script>
	<script src="${path}/static/js/tools.js"></script>
	<script type="text/javascript">
		var appId = "${appId}";
		toWeChatPage('http://'+"${view}",appId);
	</script>
	</body>
</html>