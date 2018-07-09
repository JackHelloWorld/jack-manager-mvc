package com.jack.manager.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.utils.Tools;
import com.jack.manager.common.utils.WxConst;
import com.jack.manager.controller.utils.BaseController;
import com.jack.manager.service.wechat.WxUtilService;

@Controller
public class IndexController extends BaseController{

	@Resource
	WxUtilService service;

	@RequestMapping("/")
	@ResponseBody
	public ResultTools index(){
		return ResultTools.SUCCESS("Service is up and running.....");
	}

	@RequestMapping("/MP_verify_5EIClqN7UsNC0tUx.txt")
	public void fileAction(HttpServletResponse response,HttpServletRequest request){
		try {
			request.getRequestDispatcher("/static/file/MP_verify_5EIClqN7UsNC0tUx.txt").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/view")
	public void view(HttpServletRequest request,HttpServletResponse response,String view){

		response.setContentType("text/html");  
		response.setCharacterEncoding("UTF-8");  
		try {
			request.setCharacterEncoding("UTF-8");
			String redirect_uri;
			redirect_uri = URLEncoder.encode(view, "UTF-8");
			StringBuffer url=new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?redirect_uri="+redirect_uri+  
					"&appid="+WxConst.Config.appId+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect");  
			response.sendRedirect(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/to_page")
	public String toPage(HttpServletRequest request,String page){
		returnParameter(request);
		return page;
	}

	@RequestMapping("/pay.action")
	@ResponseBody
	public String payAction(HttpServletRequest request){
		try {
			InputStream inStream = request.getInputStream();
			int _buffer_size = 1024;
			if (inStream != null) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] tempBytes = new byte[_buffer_size];
				int count = -1;
				while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
					outStream.write(tempBytes, 0, count);
				}
				tempBytes = null;
				outStream.flush();
				String result = new String(outStream.toByteArray(), "UTF-8");
				Document doc = DocumentHelper.parseText(result);
				Map<String, Object> resultMap = Tools.dom2Map(doc);
				return service.payAction(resultMap);
			}else{
				return "ERROR";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "ERROR";
		}
	}

}
