package com.jack.manager.common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jack.manager.common.config.Const;
import com.jack.manager.common.config.ResultDic;
import com.jack.manager.common.config.ResultTools;
import com.jack.manager.common.exception.BusinessException;

/**
 * 系统拦截器
 * @author Administrator
 *
 */
public class SysInterceptor implements HandlerInterceptor {
	
	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	/**
	 * 最后执行！！！
	 */
	public void afterCompletion(HttpServletRequest request,	HttpServletResponse response, Object handler, Exception exception)	throws Exception {
				
		if(exception != null){
			ResultTools resultTools = null;
			if(exception instanceof BusinessException)
				resultTools = ((BusinessException)exception).getResultTools();
			else 
				resultTools = ResultTools.ERROR(ResultDic.SYS_ERROR);
			request.getRequestDispatcher("/error/common?code="+resultTools.get("code")+"&msg="+resultTools.get("msg")).forward(request, response);  
		}
	}

	/**
	 * Action执行之后，生成视图之前执行！！
	 */
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,	ModelAndView modelAndView) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("utf-8");
		request.setAttribute("static_images", Const.FTP.staticImages);
		if(handler instanceof HandlerMethod && Const.IS_TEST){
			HandlerMethod method = (HandlerMethod)handler;
			StringBuilder sb = new StringBuilder("\nRequest action report -------- ").append(sdf.get().format(new Date())).append(" ------------------------------\n");
			String className = (method.getBean().getClass().getName());
			sb.append("Controller  : ").append(method.getBean().getClass().getName()).append(".(").append(className.trim().substring(className.lastIndexOf('.')+1, className.length())).append(".java:1)");
			sb.append("\nMethod : ").append(method);
			sb.append("\nParameters : ");
			@SuppressWarnings("unchecked")
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				sb.append(name).append(" = ").append(request.getParameter(name)).append("   ");
			}
			sb.append("\n--------------------------------------------------------------------------------\n");
			
			System.out.print(sb.toString());
			
		}
		return true;
	}

}

