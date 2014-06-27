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
		header.append(getHTMLHeaderUnclosed());
		header.append("</head>");
		return header.toString();

	}


	public static String getHTMLHeaderUnclosed(){

		StringBuilder header = new StringBuilder();

		header.append("<head>");
		header.append("<meta charset='utf-8'>");
		header.append("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		header.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		header.append("<meta name='description' content=''>");
		header.append("<meta name='author' content=''>");
		header.append("<link rel='icon' href='../../favicon.ico'>");

		header.append("<title>SEEKRET</title>");

		header.append("<!-- Bootstrap core CSS -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/bootstrap.min.css' rel='stylesheet'>");

		header.append("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->");
		header.append("<!--[if lt IE 9]>");
		header.append("<script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>");
		header.append("<script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>");
		header.append("<![endif]-->");

		header.append("<!-- Custom styles for this template -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/carousel.css' rel='stylesheet'>");
		return header.toString();

	}
	public static String createCarusel(){
		StringBuilder bodyBegin = new StringBuilder();

		bodyBegin.append("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
		bodyBegin.append("<!-- Indicators -->");
		bodyBegin.append("<ol class='carousel-indicators'>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='1'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='2'></li>");
		bodyBegin.append("</ol>");
		bodyBegin.append("<div class='carousel-inner'>");
		bodyBegin.append("<div class='item active'>");
		bodyBegin.append("<div class='carousel_img'>");
		bodyBegin.append("<div class='carousel-caption'>");
		bodyBegin.append("<div class='container-fluid'>");

		bodyBegin.append("<div class='row'>");
		bodyBegin.append("  <div class='col-xs-6 col-md-4'>Note: If you're viewing this page via a <code>file://</code> URL, the 'next' and 'previous' Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p></div>");
		bodyBegin.append(" <div class='col-xs-12 col-md-8 image-iphone'>&nbsp;</div>");
		bodyBegin.append("</div>");

		bodyBegin.append("</div>");

		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("<a class='left carousel-control' href='#myCarousel' role='button' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span></a>");
		bodyBegin.append("<a class='right carousel-control' href='#myCarousel' role='button' data-slide='next'><span class='glyphicon glyphicon-chevron-right'></span></a>");
		bodyBegin.append("</div><!-- /.carousel -->");


		return bodyBegin.toString();
	}


	public static String createCaruselMap(){
		StringBuilder bodyBegin = new StringBuilder();

		bodyBegin.append("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
		bodyBegin.append("<!-- Indicators -->");
		bodyBegin.append("<ol class='carousel-indicators'>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='1'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='2'></li>");
		bodyBegin.append("</ol>");
		bodyBegin.append("<div class='carousel-inner'>");
		bodyBegin.append("<div class='item active'>");
		bodyBegin.append("<div class='carousel_img'>");
		bodyBegin.append("<div class='carousel-caption'>");
		bodyBegin.append("<div class='container-fluid'>");

		bodyBegin.append("<div class='container-fluid'>");
		bodyBegin.append("<div class='row'>");
		bodyBegin.append("<div class='col-xs-12'><div id='map-canvas' style='height:800px; width:400px'></div></div>");
		bodyBegin.append("</div>");
		bodyBegin.append("<div class='row'>");
		bodyBegin.append(" <div class='col-xs-12'>");
		bodyBegin.append("	All additional Infos, in case click on map.");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");


		bodyBegin.append("</div>");

		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("<a class='left carousel-control' href='#myCarousel' role='button' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span></a>");
		bodyBegin.append("<a class='right carousel-control' href='#myCarousel' role='button' data-slide='next'><span class='glyphicon glyphicon-chevron-right'></span></a>");
		bodyBegin.append("</div><!-- /.carousel -->");


		return bodyBegin.toString();
	}




	public static String createBodyBegin(){
		StringBuilder bodyBegin = new StringBuilder();
		bodyBegin.append("<body>");
		return bodyBegin.toString();
	}



	public static String createBodyEnd(){
		StringBuilder bodyEnd = new StringBuilder();



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

		navigation.append("<div class='navbar-wrapper'>");
		navigation.append("<div class='container'>");

		navigation.append("<div class='navbar navbar-inverse navbar-static-top' role='navigation'>");
		navigation.append("<div class='container'>");
		navigation.append("<div class='navbar-header'>");
		navigation.append("<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>");
		navigation.append("<span class='sr-only'>Toggle navigation</span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("</button>");
		navigation.append("<a class='navbar-brand' href='#'><img src='/res_html/img/banner.png' width='150px' alt='SEEKRET'/></a>");
		navigation.append("</div>");
		navigation.append("<div class='navbar-collapse collapse'>");
		navigation.append("<ul class='nav navbar-nav'>");
		navigation.append("<li class='active'><a href='#'>Home</a></li>");
		navigation.append("<li><a href='#about'>About</a></li>");
		navigation.append("<li><a href='#contact'>Contact</a></li>");
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
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");

		navigation.append("</div>");
		navigation.append("</div>");

		return navigation.toString();
	}













}
