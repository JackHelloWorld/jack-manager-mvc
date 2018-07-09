package com.jack.manager.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jack.manager.common.utils.WxConst;


@Controller
@RequestMapping("/wechat")
public class WeChatController {

	@RequestMapping(value="/api",method=RequestMethod.GET)
	public void check(@RequestParam(value="signature",defaultValue="")String signature,
			@RequestParam(value="timestamp",defaultValue="")String timestamp,
			@RequestParam(value="nonce",defaultValue="")String nonce,
			@RequestParam(value="echostr",defaultValue="")String echostr,HttpServletResponse response){
		if(checkToken(signature, timestamp, nonce)){
			try {
				response.getWriter().print(echostr.trim());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean checkToken(String signature,String timestamp,String nonce){
		String[] arr = new String[]{WxConst.Config.token,timestamp,nonce};
		Arrays.sort(arr);
		StringBuffer stringBuffer = new StringBuffer();
		for (String string : arr) {
			stringBuffer.append(string);
		}
		String temp = getSha1(stringBuffer.toString());
		return temp.equals(signature);
	}


	public static String getSha1(String str){
		if(str==null||str.length()==0){
			return null;
		}

		char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9', 'a','b','c','d','e','f'};
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));
			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j*2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];      
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}



}
