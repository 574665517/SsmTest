package com.ischoolbar.programmer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��¼��������������������spring���������
 */

public class LoginInterceptor  implements HandlerInterceptor {
	/**
	 * �̳�HandlerInterceptor�ӿڣ���д��������
	 */

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//���ִ�У������ͷ���Դ�����쳣
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		//��actionִ�к�������ͼǰִ�У������л����޸���ͼ
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/**
		 * ʵ�ֶ����ݵ�Ԥ�������û��Ƿ��¼�����롢��ȫ���ƣ���������true�����ִ��action
		 */
		String url = request.getRequestURI();
		System.out.println("������������url = " + url);
		Object user = request.getSession().getAttribute("user");
		if(user == null) {
			//��ʾδ��¼���ߵ�¼״̬ʧЧ
			System.out.println("δ��¼���¼ʧЧ��url = " + url);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "��¼״̬��ʧЧ��������ȥ��¼!");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}
		return true;
	}

}
