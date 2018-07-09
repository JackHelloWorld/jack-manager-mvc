var base_path = "";
$.fn.sub = function(url,success,error,msg){
	if(!msg) msg = "请稍后...";
	var layerIndex = layer.msg(msg, {icon: 16,time:0,shade: 0.5,});
	var para = $(this).serialize()+"&open_id="+wx_open_id;
	$.ajax({
			cache: false,
			type: "POST",
			url:base_path+url,
			dataType:'json',
			data:para,
			error: function(erro){
				layer.alert("操作异常");
				layer.close(layerIndex);
			},
			success: function(data) {
				if(data.code==0){
					success(data);
				}else{
					error(data);
				}
				layer.close(layerIndex);
			}
		});
};
$.fn.initValue = function(data){
	$(this).find("[name]").each(function(){
		var name = $(this).attr("name");
		console.log(name);
		if(data[name]){
			$(this).val(data[name]);
		}
	});
};
var post = function(url,data,success,error,msg){
	if(!msg) msg = "请稍后...";
	var layerIndex = layer.msg(msg, {icon: 16,time:0,shade: 0.5});
	if(data){
		data.open_id=wx_open_id;
	}else{
		data = {open_id:wx_open_id};
	}
	$.ajax({
		cache: false,
		type: "POST",
		url:base_path+url,
		dataType:'json',
		data:data,
		error: function(erro){
			layer.alert("操作异常");
			layer.close(layerIndex);
		},
		success: function(data) {
			layer.close(layerIndex);
			if(data.code==0){
				success(data);
			}else{
				error(data);
			}
		}
	});
};
$.fn.initSelect = function(url,data,value,text,title,def,end){
	var This = $(this);
	This.html("");
	if(!title) title="请选择";
	if(!def) def="";
	post(url,data,function(data){
		This.append('<option value="'+def+'">'+title+'</option>');
		for(var i = 0;i<data.data.length;i++){
			var t = data.data[i][text];
			var v = data.data[i][value];
			This.append('<option value="'+v+'">'+t+'</option>');
		}
		if(end){
			end(data);
		}
	},function(data){
		layer.alert(data.msg,{icon:2});
	});
}

var moneyToUpperCase = function(money){
	//汉字的数字
	  var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
	  //基本单位
	  var cnIntRadice = new Array('', '拾', '佰', '仟');
	  //对应整数部分扩展单位
	  var cnIntUnits = new Array('', '万', '亿', '兆');
	  //对应小数部分单位
	  var cnDecUnits = new Array('角', '分', '毫', '厘');
	  //整数金额时后面跟的字符
	  var cnInteger = '整';
	  //整型完以后的单位
	  var cnIntLast = '元';
	  //最大处理的数字
	  var maxNum = 999999999999999.9999;
	  //金额整数部分
	  var integerNum;
	  //金额小数部分
	  var decimalNum;
	  //输出的中文金额字符串
	  var chineseStr = '';
	  //分离金额后用的数组，预定义
	  var parts;
	  if (money == '') { return ''; }
	  money = parseFloat(money);
	  if (money >= maxNum) {
	    //超出最大处理数字
	    return '';
	  }
	  if (money == 0) {
	    chineseStr = cnNums[0] + cnIntLast + cnInteger;
	    return chineseStr;
	  }
	  //转换为字符串
	  money = money.toString();
	  if (money.indexOf('.') == -1) {
	    integerNum = money;
	    decimalNum = '';
	  } else {
	    parts = money.split('.');
	    integerNum = parts[0];
	    decimalNum = parts[1].substr(0, 4);
	  }
	  //获取整型部分转换
	  if (parseInt(integerNum, 10) > 0) {
	    var zeroCount = 0;
	    var IntLen = integerNum.length;
	    for (var i = 0; i < IntLen; i++) {
	      var n = integerNum.substr(i, 1);
	      var p = IntLen - i - 1;
	      var q = p / 4;
	      var m = p % 4;
	      if (n == '0') {
	        zeroCount++;
	      } else {
	        if (zeroCount > 0) {
	          chineseStr += cnNums[0];
	        }
	        //归零
	        zeroCount = 0;
	        chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
	      }
	      if (m == 0 && zeroCount < 4) {
	        chineseStr += cnIntUnits[q];
	      }
	    }
	    chineseStr += cnIntLast;
	  }
	  //小数部分
	  if (decimalNum != '') {
	    var decLen = decimalNum.length;
	    for (var i = 0; i < decLen; i++) {
	      var n = decimalNum.substr(i, 1);
	      if (n != '0') {
	        chineseStr += cnNums[Number(n)] + cnDecUnits[i];
	      }
	    }
	  }
	  if (chineseStr == '') {
	    chineseStr += cnNums[0] + cnIntLast + cnInteger;
	  } else if (decimalNum == '') {
	    chineseStr += cnInteger;
	  }
	  return chineseStr;
}
Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1,
					RegExp.$1.length == 1 ? date[k] : ("00" + date[k])
							.substr(("" + date[k]).length));
		}
	}
	return format;
}
var notNull = function(val) {
	if (val == null || $.trim(val) == '')
		return false;
	return true;
}
Date.prototype.format = function(format) {
	var date = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S+" : this.getMilliseconds()
	};
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + '')
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in date) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1,
					RegExp.$1.length == 1 ? date[k] : ("00" + date[k])
							.substr(("" + date[k]).length));
		}
	}
	return format;
};
/**微信页面跳转*/
var toWeChatPage = function(uri,appId){
	location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+encodeURIComponent(uri)+"&scope=snsapi_userinfo&response_type=code&state=STATE&connect_redirect=1#wechat_redirect";
}


var getParam = function(name) {  
	   var url = location.search; //获取url中"?"符后的字串  
	   var theRequest = new Object();  
	   if (url.indexOf("?") != -1) {  
	      var str = url.substr(1);  
	      strs = str.split("&");  
	      for(var i = 0; i < strs.length; i ++) {  
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
	      }  
	   }  
	   for(var key in theRequest){
		   if($.trim(key)==$.trim(name))
			   return theRequest[key];
	   }
	   return null;
	}   

