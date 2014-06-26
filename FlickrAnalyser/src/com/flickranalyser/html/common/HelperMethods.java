package com.flickranalyser.html.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.html.webfrontend.HtmlStarterServlet;

public class HelperMethods {

	public static final String PAGE_TITLE = "SECRET PLACES";
	private static final Logger LOGGER = Logger.getLogger(HtmlStarterServlet.class.getName());
	
	public static <T> T instantiate(final String className, final Class<T> type){
		try{
			return type.cast(Class.forName(className).newInstance());
		} catch(final InstantiationException e){
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch(final IllegalAccessException e){
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch(final ClassNotFoundException e){
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return null;
	}




	public static String getHTMLHeader(){
		StringBuilder header = new StringBuilder();

		header.append("<head>");
		header.append("<meta charset='utf-8'>");
		header.append("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		header.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		header.append("<meta name='description' content=''>");
		header.append("<meta name='author' content=''>");
		header.append("<link rel='icon' href='../../favicon.ico'>");

		header.append("<title>"+ PAGE_TITLE + "</title>");

		header.append("<!-- Bootstrap core CSS -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/bootstrap.min.css' rel='stylesheet'>");

		header.append("<!-- Custom styles for this template -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/navbar.css' rel='stylesheet'>");

		header.append("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->");
		header.append("<!--[if lt IE 9]>");
		header.append("<script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>");
		header.append("<script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>");
		header.append("<![endif]-->");
		header.append("</head>");

		return header.toString();

	}

	public static String createBodyBegin(){
		StringBuilder bodyBegin = new StringBuilder();
		bodyBegin.append("<body>");
		bodyBegin.append("<div class='container'>");
		return bodyBegin.toString();
	}

	public static String createBodyEnd(){
		StringBuilder bodyEnd = new StringBuilder();

		bodyEnd.append("</div> <!-- /container -->");
		bodyEnd.append("<!-- Bootstrap core JavaScript");
		bodyEnd.append("================================================== -->");
		bodyEnd.append("<!-- Placed at the end of the document so the pages load faster -->");
		bodyEnd.append("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>");
		bodyEnd.append("<script src='/res_html/bootstrap-3.2.0-dist/js/bootstrap.min.js'></script>");
		bodyEnd.append("</body>");

		return bodyEnd.toString();
	}


	public static String createNavigation(){

		StringBuilder navigation = new StringBuilder();

		navigation.append("<!-- Static navbar -->");
		navigation.append("<div class='navbar navbar-default' role='navigation'>");
		navigation.append("<div class='container-fluid'>");
		navigation.append("<div class='navbar-header'>");
		navigation.append("<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>");
		navigation.append("<span class='sr-only'>Toggle navigation</span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("</button>");
		navigation.append("<a class='navbar-brand' href='#'>Project name</a>");
		navigation.append("</div>");
		navigation.append("<div class='navbar-collapse collapse'>");
		navigation.append("<ul class='nav navbar-nav'>");
		navigation.append("<li class='active'><a href='#'>Link</a></li>");
		navigation.append("<li><a href='#'>Link</a></li>");
		navigation.append("<li><a href='#'>Link</a></li>");
		navigation.append("<li class='dropdown'>");
		navigation.append("<a href='#' class='dropdown-toggle' data-toggle='dropdown'>Dropdown <span class='caret'></span></a>");
		navigation.append("<ul class='dropdown-menu' role='menu'>");
		navigation.append("<li><a href='#'>Action</a></li>");
		navigation.append("<li><a href='#'>Another action</a></li>");
		navigation.append("<li><a href='#'>Something else here</a></li>");
		navigation.append("<li class='divider'></li>");
		navigation.append("<li class='dropdown-header'>Nav header</li>");
		navigation.append("<li><a href='#'>Separated link</a></li>");
		navigation.append("<li><a href='#'>One more separated link</a></li>");
		navigation.append("</ul>");
		navigation.append("</li>");
		navigation.append("</ul>");
		navigation.append("<ul class='nav navbar-nav navbar-right'>");
		navigation.append("<li class='active'><a href='./'>Default</a></li>");
		navigation.append("<li><a href='../navbar-static-top/'>Static top</a></li>");
		navigation.append("<li><a href='../navbar-fixed-top/'>Fixed top</a></li>");
		navigation.append("</ul>");
		navigation.append("</div><!--/.nav-collapse -->");
		navigation.append("</div><!--/.container-fluid -->");
		navigation.append("</div>");

		return navigation.toString();
	}













}
