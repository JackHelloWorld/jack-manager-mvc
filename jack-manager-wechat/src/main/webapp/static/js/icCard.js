var ICObj = new Object();
var pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDvDObe83o90iz5uQq4xdzl0J9L58E9t/giuLyKmXq/Q8Ruo0Bd4RTtfCBOXXIo1HjHDKi3rLBfFWdQr3XXgd7NkU9wy6h4Vb99+eK4bzIPJLnCR7Xk+zDngG/Ob5OtELbUWTv1xwD6WNNxNzddhEofBvN4iEzrto2gX7DsUSeoMQIDAQAB";
var baseIcCard = function(param1,success,error,layerIndex){
	param1.action_quhao = companyId;
	var param = ICObj.paramToRsa(param1);
	 $.ajax({
		cache: false,
		type: "GET",
		url: "http://127.0.0.1:21391/ic_card",
		dataType:'json',
		data:param,
		success: function(data) {
			layer.close(layerIndex);
			if(data.code!=0){
				error(data.message);
			}else{
				success(data.data);
			}
		},
		error: function(data){
		 layer.alert("操作失败,请检查驱动程序是否启动!",{icon:2});
		 layer.close(layerIndex);
	 },
	});
}

ICObj.kh = function(checkUrl,param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "kh_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	$.ajax({
		cache: false,
		type: "POST",
		url: checkUrl,
		dataType:'json',
		data:{},
		success: function(data) {
			if(data.code==0){
				baseIcCard(param,success,error,layerIndex);
			}else{
				error(data.msg);
			}
		},
		error: function(data){
		 layer.alert("操作失败,请检查网络是否正常!",{icon:2});
		 layer.close(layerIndex);
	 },
	});
};

ICObj.cx = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "cx_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.cz = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "cz_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.jb = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "jb_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.jq = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "jq_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.qk = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "qk_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.ql = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "ql_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.initAction = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "init_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.read = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "read_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.sz = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "sz_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.tg = function(param,success,error){
	if(param=='' || param==null) param = new Object();
	param.action = "tg_action";
	var layerIndex = layer.msg('请稍后...', {icon: 16,time:0,shade: [0.1, '#f5f5f5']});
	baseIcCard(param,success,error,layerIndex);
};

ICObj.paramToRsa = function(param1){
	var param = new Object();
	var encrypt=new JSEncrypt();
	encrypt.setPublicKey(pubKey);
	for(var name in param1){
		param[name] = encrypt.encrypt(param1[name]+"");
	}
	return param;
}

ICObj.paramToBase46 = function(param1){
	var param = new Object();
	for(var name in param1){
		param[name] = $.base64.encode(param1[name]);
	}
	return param;
}