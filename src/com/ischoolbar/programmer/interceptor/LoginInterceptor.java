package com.ischoolbar.programmer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录过滤拦截器，用来拦截spring的相关请求
 */

public class LoginInterceptor  implements HandlerInterceptor {
	/**
	 * 继承HandlerInterceptor接口，覆写三个方法
	 */

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//最后执行，用于释放资源处理异常
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		//在action执行后，生成视图前执行，这里有机会修改视图
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/**
		 * 实现对数据的预处理，如用户是否登录、编码、安全控制，方法返回true则继续执行action
		 */
		String url = request.getRequestURI();
		System.out.println("进入拦截器，url = " + url);
		Object user = request.getSession().getAttribute("user");
		if(user == null) {
			//表示未登录或者登录状态失效
			System.out.println("未登录或登录失效，url = " + url);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新去登录!");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}
		return true;
	}

}
