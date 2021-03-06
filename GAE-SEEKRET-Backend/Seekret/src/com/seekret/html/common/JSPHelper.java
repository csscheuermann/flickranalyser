package com.seekret.html.common;

import javax.servlet.http.HttpServletRequest;

import com.seekret.businesslogic.common.UserRolesEnum;
import com.seekret.pojo.SeekretUser;

public class JSPHelper {

	public static boolean isUserAdmin(HttpServletRequest request){
		SeekretUser loggedInUser = (SeekretUser) request.getAttribute("currentUser");
		return loggedInUser.getUserGroup().equals(UserRolesEnum.ADMIN.name());
	}
}
