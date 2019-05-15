package jc.com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import jc.com.utils.ResultUtil;

public class BaseControl {
	
	protected void writeString(HttpServletResponse response,ResultUtil ru) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(ObjToString(ru));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void writeString(HttpServletResponse response,String str) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected String ObjToString(ResultUtil ru) {
		return JSONArray.toJSONString(ru);
	}; 
	
	protected String initHtml(String userId) {
		String html = "";
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset=\"utf-8\">";
		html += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui\">";
		html += "<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">";
		html += " <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">";
		html += " <title>LOGIN</title>";
		html += "<link rel=\"stylesheet\" href=\"/jc/common/framework7/css/framework7.ios.min.css\">";
		html += "<link rel=\"stylesheet\" href=\"/jc/common/framework7/css/framework7.ios.colors.min.css\">";
		html += "<script type=\"text/javascript\" src=\"/jc/common/framework7/js/framework7.min.js\"></script>";
		html += "<script type=\"text/javascript\" src=\"/jc/common/jquery/jquery-1.10.2.min.js\"></script>";
		html += "<script type=\"text/javascript\" src=\"/jc/common/underscore.js\"></script>";
		html += "<script type=\"text/javascript\" src=\"/jc/common/mobileFrame.js\"></script>";
		html += "<script type=\"text/javascript\" src=\"/jc/common/mobileUtil.js\"></script>";
		html += "<script type=\"text/javascript\" src=\"/jc/index/js/index.js\"></script>";
		html += "<link rel=\"stylesheet\" href=\"/jc/index/css/login.css\">";
		html += "</head>";
		html += "<body>";
//		html += "<input type=\"hidden\" value=\""+userId+"\" id=\"userId\"/>";
		html += "<div class=\"statusbar-overlay\"></div>";
		html += "<div class=\"views\">";
		html += "<div class=\"view view-main\">";
		html += "<div class=\"navbar navbar_all\">";
		html += "</div>";
		html += " <div class=\"pages navbar-through toolbar-through pages_all\">";
		html += " </div>";
		html += "<div class=\"toolbar toolbar_all\">";
		html += " </div>";
		html += "</div>";
		html += "</div>"; 
		html += "</body>";
		html += "</html>"; 
		return html;
		
	}; 
}
