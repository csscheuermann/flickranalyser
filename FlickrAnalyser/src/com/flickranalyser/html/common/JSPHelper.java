package com.flickranalyser.html.common;

import javax.servlet.http.HttpServletRequest;

import com.flickranalyser.businesslogic.common.UserRolesEnum;
import com.flickranalyser.pojo.SeekretUser;

public class JSPHelper {

	public static boolean isUserAdmin(HttpServletRequest request){
		SeekretUser loggedInUser = (SeekretUser) request.getAttribute("currentUser");
		return loggedInUser.getUserGroup().equals(UserRolesEnum.ADMIN.name());
	}
}
