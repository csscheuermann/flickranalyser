package com.seekret.html.common;

import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seekret.businesslogic.common.UserRolesEnum;
import com.seekret.endpoints.RatingService;
import com.seekret.html.webfrontend.HtmlRequestProcessor;
import com.seekret.html.webfrontend.HtmlStarterServlet;
import com.seekret.pojo.SeekretUser;
import com.seekret.pojo.SpotResultList;
import com.seekret.pojo.results.KeyResult;

public class HelperMethods {
	public static final String PAGE_TITLE = "SECRET PLACES";
	private static final Logger LOGGER = Logger.getLogger(HtmlStarterServlet.class.getName());
	private final HttpSession session;
	public static final String MESSAGE_SUCCESSFUL_CRON = "successfulCron";
	public static final String MESSAGE_ERROR_CRON = "errorCron";
	public static final String MESSAGE_SUCCESSFUL = "successful";
	public static final String MESSAGE_ERROR = "error";
	public static final String CRAWL_LONGITUDE = "crawlLongitude";
	public static final String CRAWL_LATITUDE = "crawlLatitude";
	public static final String CRAWL_ADDRESS = "crawlAddress";
	public static final String CLUSTER_KEY = "clusterKey";
	public static final String CLUSTER_RATING_VALUE = "clusterRating";
	public static final String ADDRESS_PARAM = "address";
	public static final String LONGITUDE_PARAM = "longitude";
	public static final String LATITUDE_PARAM = "latitude";
	public static final String SPOT = "spot";
	public static final String SPOT_NAME = "spotName";
	private KeyResult ratingResult;
	private KeyResult dismissResult;


	public HelperMethods(HttpSession session) {
		this.session = session;
		RatingService rs = new RatingService();
		this.ratingResult = rs.getAllRatingKeysOfSpecifiedUser(getCurrentUserEmail());
		this.dismissResult = rs.getAllDismissKeysOfSpecifiedUser(getCurrentUserEmail());
	}

	public static <T> T instantiate(String className, Class<T> type) {
		try {
			return type.cast(Class.forName(className).newInstance());
		} catch (InstantiationException e) {
			LOGGER.log(Level.SEVERE, "COULD NOT INITIATE " + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.log(Level.SEVERE, "ILLEGALACCESSEXCEPTION" + e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "CLASSNOTFOUNDEXCEPTION" + e.getMessage(), e);
		}
		return null;
	}
	public String getFilterStrategyButtons(String spotName){
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add(spotName);
		SpotResultList spotResultList = new SpotResultList(linkedList);
		return getFilterStrategyButtons(spotResultList);
	}


	public String getFilterStrategyButtons(SpotResultList topSpots){


		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");

		StringBuilder getFilterStrategyButtons = new StringBuilder();
		getFilterStrategyButtons.append("<script type='text/javascript'>");
		getFilterStrategyButtons.append("function 	setSpotNameToInput(spotname, inputElementId){");
		getFilterStrategyButtons.append("var spotName = document.getElementById(inputElementId);");

		//TODO COS WTF :) Das bitte ??ndern
		
		getFilterStrategyButtons.append("$('#doNotConsiderDismissedClusters').val($('#doNotConsiderDismissedClusters').is(':checked'));");
		getFilterStrategyButtons.append("$('#doNotConsiderClusterWithoutPictures').val($('#doNotConsiderClusterWithoutPictures').is(':checked'));");
		getFilterStrategyButtons.append("$('#useFlusterFlag').val($('#useFlusterFlag').is(':checked'));");
		
		getFilterStrategyButtons.append("spotName.setAttribute('value', spotname);");
		getFilterStrategyButtons.append("	document.forms['FilterForm'].submit();}");
		getFilterStrategyButtons.append("</script>");


		getFilterStrategyButtons.append("<form role='form' name='FilterForm' action='https://seekret-dev.appspot.com/?showView=SpotMap' method='post'>");
		getFilterStrategyButtons.append(createFilterSettings());
		getFilterStrategyButtons.append("<input type='hidden' name='location' id='locationSetByJQuery'>"); 

		getFilterStrategyButtons.append("<div class='container'>");
		getFilterStrategyButtons.append("<div class='row'>");
		getFilterStrategyButtons.append("<div class='col-xs-4'> <h4>Name</h4> </div>");
		getFilterStrategyButtons.append("<div class='col-xs-8'><h4>Cluster Algos</h4></div></div>");
		if (topSpots != null){
			for (String spotName : topSpots.getTopSpots())	{
				getFilterStrategyButtons.append("<div class='row bottom-sapce' > ");
				getFilterStrategyButtons.append("<div class='col-xs-4'>");
				getFilterStrategyButtons.append(spotName);
				getFilterStrategyButtons.append("</div>");
				getFilterStrategyButtons.append("<div class='col-xs-8'> ");
				getFilterStrategyButtons.append("<button type='submit' class='btn' name='strategy' onClick=\"setSpotNameToInput('"+spotName+"', 'locationSetByJQuery')\" value='DoNotFilterStrategy'>No Filter</button>&nbsp;");

				if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
					getFilterStrategyButtons.append("<button type='submit' class='btn' name='strategy' onClick=\"setSpotNameToInput('"+spotName+"', 'locationSetByJQuery')\" value='ManyViewsAndFewPOIsFilterStrategy'>ManyViews FewPOIs</button>&nbsp;");
					getFilterStrategyButtons.append("<button type='submit' class='btn' name='strategy' onClick=\"setSpotNameToInput('"+spotName+"', 'locationSetByJQuery')\" value='RelativeRatioViewsAndPOIsFilterStrategy'>RelativeRatioViewsAndPOIs</button>&nbsp;");
					getFilterStrategyButtons.append("<button type='submit' class='btn' name='strategy' onClick=\"setSpotNameToInput('"+spotName+"', 'locationSetByJQuery')\" value='ManyViewsAndFixedAmountOfPOIsFilterStrategy'>ManyViewsAndFixedAmountOfPOIs</button>&nbsp;");
					getFilterStrategyButtons.append("<button type='submit' class='btn' name='strategy' onClick=\"setSpotNameToInput('"+spotName+"', 'locationSetByJQuery')\" value='SeekretFinderStrategy'>SeekretFinderStrategy</button>&nbsp;");
					
				
				}
				getFilterStrategyButtons.append("</div></div>");
			}
		}
		getFilterStrategyButtons.append("</div>");
		getFilterStrategyButtons.append("</div>");
		getFilterStrategyButtons.append("</form>");
		return getFilterStrategyButtons.toString(); 

	}

	private String createFilterSettings(){
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		StringBuilder createFilterSettings = new StringBuilder();
		if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
			createFilterSettings.append("<div class='container'>");

			createFilterSettings.append("<div class='alert alert-info bottom-sapce-30 bottom-padding-30'>");
			createFilterSettings.append("<h3> Filter Settings </h3>");
			createFilterSettings.append("<div class='row'>");
			createFilterSettings.append("<div class='col-xs-1'>  ");
			createFilterSettings.append("      <input type='checkbox' name='dissmissCluster' id='doNotConsiderDismissedClusters'> ");
			createFilterSettings.append("  </div>");
			createFilterSettings.append("<div class='col-xs-11'>Do not consider dismissed clusters</div>");
			createFilterSettings.append("</div>");
			createFilterSettings.append("<div class='row'>");
			createFilterSettings.append("<div class='col-xs-1'>  ");
			createFilterSettings.append("      <input type='checkbox' name='doNotConsiderPicturelessCluster' id='doNotConsiderClusterWithoutPictures'> ");
			createFilterSettings.append("  </div>");
			createFilterSettings.append("<div class='col-xs-11'>do not consider cluster without pictures</div>");
			createFilterSettings.append("</div>");
			createFilterSettings.append("<div class='row'>");
			createFilterSettings.append("<div class='col-xs-1'>  ");
			createFilterSettings.append("      <input type='checkbox' name='useFluster' id='useFlusterFlag'> ");
			createFilterSettings.append("  </div>");
			createFilterSettings.append("<div class='col-xs-11'>Use fluster</div>");
		
			createFilterSettings.append("</div>");

			createFilterSettings.append("</div>");
			createFilterSettings.append("</div>");
		}

		return createFilterSettings.toString();
	}
	
	
	public String createCrawlSettings(){
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		StringBuilder createFilterSettings = new StringBuilder();
		if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
			createFilterSettings.append("<div class='container'>");

			createFilterSettings.append("<div class='alert alert-info bottom-sapce-30 bottom-padding-30'>");
			createFilterSettings.append("<h3> Crawl Settings </h3>");
			createFilterSettings.append("<div class='row'>");
			createFilterSettings.append("<div class='col-xs-3'>  ");
			createFilterSettings.append("      <input type='checkbox' id='onlyExcludedPictures'> ");
			createFilterSettings.append("  </div>");
			createFilterSettings.append("<div class='col-xs-9'>Get only the excluded pictures</div>");
			createFilterSettings.append("</div>");

			createFilterSettings.append("</div>");
			createFilterSettings.append("</div>");
		}

		return createFilterSettings.toString();
	}
	
	
	public String getHTMLHeader() {
		StringBuilder header = new StringBuilder();
		header.append(getHTMLHeaderUnclosed());
		header.append("</head>");
		return header.toString();
	}

	public String getHTMLHeaderUnclosed() {
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
		header.append("<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js'></script>");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/bootstrap.min.css' rel='stylesheet'>");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/custom.css' rel='stylesheet'>");
		header.append("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->");
		header.append("<!--[if lt IE 9]>");
		header.append("<script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>");
		header.append("<script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>");
		header.append("<![endif]-->");

		header.append("<!-- Custom styles for this template -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/carousel.css' rel='stylesheet'>");
		return header.toString();
	}

	public String createCarusel() {
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
		bodyBegin.append("<div class='container'>");

		bodyBegin.append("<div class='row'>");
		bodyBegin.append("  <div class='col-xs-6 col-md-4'><h1>A place I will never forget</h1></div>");
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

	public String createMap(String mapDivId) {
		StringBuilder bodyBegin = new StringBuilder();

		bodyBegin.append("<div class='container'>");
		bodyBegin.append("<div class='row'>");
		bodyBegin.append("<div class='col-xs-12'><div id='" + mapDivId + "' style='margin-bottom: 20px; height:400px; width:100%'></div></div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");

		return bodyBegin.toString();
	}

	public String createBodyBegin() {
		StringBuilder bodyBegin = new StringBuilder();
		bodyBegin.append("<body>");
		return bodyBegin.toString();
	}

	public String createBodyEnd() {
		StringBuilder bodyEnd = new StringBuilder();

		bodyEnd.append("<!-- Bootstrap core JavaScript");
		bodyEnd.append("================================================== -->");
		bodyEnd.append("<!-- Placed at the end of the document so the pages load faster -->");
		bodyEnd.append("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>");
		bodyEnd.append("<script src='/res_html/bootstrap-3.2.0-dist/js/bootstrap.min.js'></script>");
		bodyEnd.append("</body>");

		return bodyEnd.toString();
	}

	public String createNavigation(boolean isIndexPage) {
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		StringBuilder navigation = new StringBuilder();

		if (isIndexPage)
			navigation.append("<div class='navbar-wrapper'>");
		else {
			navigation.append("<div class='navbar-wrapper-normal'>");
		}

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
		navigation.append("<li class='active'><a href='/'>Home</a></li>");

		if (currentUser.getEmail().equals(HtmlRequestProcessor.GUEST_USER.getEmail())) {
			navigation.append("<li><a href='?showView=Login'>Login</a></li>");
		} else {
			navigation.append("<li><a href='?showView=TopSpots'>Top Spots</a></li>");
			if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
				navigation.append("<li><a href='?showView=SearchSpots'>Search Spot</a></li>");
			}
			navigation.append("<li><a href='?showView=Logout'>Logout</a></li>");
		}
		navigation.append("</li>");
		navigation.append("</ul>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");

		return navigation.toString();
	}

	public String getMarketingForMainPage() {
		StringBuilder marketing = new StringBuilder();

		marketing.append("  <!-- Marketing messaging and featurettes");
		marketing.append("================================================== -->");
		marketing.append("<!-- Wrap the rest of the page in another container to center all the content. -->");
		marketing.append("<div class='container marketing'>");
		marketing.append("<!-- Three columns of text below the carousel -->");
		marketing.append("<div class='row'>");
		marketing.append("<div class='col-lg-4'>");
		marketing
		.append("<img class='img-circle' src='/res_html/img/frontpage/mountain.svg' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>What's Seekret?</h2>");
		marketing
		.append("<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("<div class='col-lg-4'>");
		marketing
		.append("<img class='img-circle' src='data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>Our Mission</h2>");
		marketing
		.append("<p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("<div class='col-lg-4'>");
		marketing
		.append("<img class='img-circle' src='data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>Who we are</h2>");
		marketing
		.append("<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("  </div><!-- /.row -->");

		return marketing.toString();
	}

	public String getFooter() {
		StringBuilder footerBuilder = new StringBuilder();
		footerBuilder.append("<!-- FOOTER -->");
		footerBuilder.append("  <footer>");
		footerBuilder.append("   <p class='pull-right'><a href='#'>Back to top</a></p>");
		footerBuilder.append("   <p>DVCS 2014 <a href='#'>Privacy</a> &middot; <a href='#'>Terms</a></p>");
		footerBuilder.append(" </footer>");
		footerBuilder.append(" </div><!-- /.container -->");

		return footerBuilder.toString();
	}

	public String createSpotInfo() {
		StringBuilder spotInfo = new StringBuilder();
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
			spotInfo.append("<div id='spotInfoContainer' class='container showGroup background-grey'>");
			// spotInfo.append("<div class='row'>");
			// spotInfo.append("<div class='col-xs-12'>");
			// spotInfo.append("<h2>INFORMATION ABOUT LOCATION</h2>");
			// spotInfo.append(" </div>");
			// spotInfo.append(" </div>");

			spotInfo.append("<div class='row'>");
			spotInfo.append("<div class='col-md-6'>\t<div><h4>Address</h4></div> \t\t\t<div id='spotaddress'> \t\t</div> </div>");
			spotInfo.append("<div class='col-md-3'>\t<div><h4>Overall Max #POIs</h4></div> \t\t\t<div id='poiCount'>\t\t\t</div> </div>");
			spotInfo.append("<div class='col-md-3'>\t<div><h4>Overall Max #Views</h4></div> \t\t\t<div id='spotOverallview'>\t</div> </div>");

			spotInfo.append(" </div>");
			spotInfo.append(" </div>");
		}
		return spotInfo.toString();
	}

	public String createSeekretSpotInformation() {
		StringBuilder seekretSpotInformation = new StringBuilder();
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
			seekretSpotInformation.append("<div id='seekretSpotInfoContainer' class='container showGroup background-grey'>");
			// seekretSpotInformation.append("<div class='row'>");
			// seekretSpotInformation.append("<div class='col-xs-12'>");
			// seekretSpotInformation.append("<h2>SEEKRET SPOT INFORMATION</h2>");
			// seekretSpotInformation.append(" </div>");
			// seekretSpotInformation.append(" </div>");

			seekretSpotInformation.append("<div class='row'>");
			seekretSpotInformation.append("<div class='col-md-6'>\t<div><h4>Address</h4></div> \t\t\t<div id='clusterAddress'> \t\t</div> </div>");
			seekretSpotInformation.append("<div class='col-md-3'>\t<div><h4>Max #POIs</h4></div> \t\t\t<div id='maxClusterPOIs'> \t\t</div> </div>");
			seekretSpotInformation.append("<div class='col-md-3'>\t<div><h4>Max #Views</h4></div> \t\t\t<div id='maxClusterViews'> \t\t</div> </div>");
			seekretSpotInformation.append(" </div>");

			seekretSpotInformation.append("<div class='row bottom-sapce'>");
			seekretSpotInformation.append("<div class='col-md-6'>\t&nbsp; </div>");
			seekretSpotInformation.append("<div class='col-md-3'>\t<div><h4>#POIs</h4></div> \t\t\t<div id='clusterPOIs'> \t\t</div> </div>");
			seekretSpotInformation.append("<div class='col-md-3'>\t<div><h4>#Views</h4></div> \t\t\t<div id='clusterViews'> \t\t</div> </div>");
			seekretSpotInformation.append(" </div>");

			seekretSpotInformation.append("<div class='row'>");
			seekretSpotInformation.append("<div class='col-md-4'><h4>Download</h4></div>");
			seekretSpotInformation.append("<div class='col-md-2'><button type='button' class='btn btn-default'>KML</button></div>");
			seekretSpotInformation.append("<div class='col-md-2'> <button type='button' class='btn btn-default'>PDF</button></div>");
			seekretSpotInformation.append("<div class='col-md-2'> <button type='button' class='btn btn-default'>PDF</button></div>");

			seekretSpotInformation.append(" </div>");
			seekretSpotInformation.append(" </div>");
		}
		return seekretSpotInformation.toString();
	}

	public String createTopPicturesContainer() {
		StringBuilder topPicturesContainer = new StringBuilder();

		topPicturesContainer.append("<div id='topPicturesContainer' class='container showGroup background-grey'>");

		// topPicturesContainer.append("<div class='row'>");
		// topPicturesContainer.append("<div class='col-xs-12'>");
		// topPicturesContainer.append("<h2>TOP THREE PICTURES</h2>");
		// topPicturesContainer.append(" </div>");
		// topPicturesContainer.append(" </div>");

		topPicturesContainer.append("<div class='row'>");
		topPicturesContainer.append("<div class='col-md-4 centeralized-div'> <div id='picture1'> </div> </div>");
		topPicturesContainer.append("<div class='col-md-4 centeralized-div'> <div id='picture2'> </div> </div>");
		topPicturesContainer.append("<div class='col-md-4 centeralized-div'> <div id='picture3'> </div> </div>");
		topPicturesContainer.append(" </div>");

		topPicturesContainer.append(" </div>");

		return topPicturesContainer.toString();
	}

	public String createRatingInformationContainer() {
		StringBuilder ratingInformationContainer = new StringBuilder();
		SeekretUser currentUser = (SeekretUser) this.session.getAttribute("currentUser");
		if (currentUser.getUserGroup().equals(UserRolesEnum.ADMIN.name())) {
			ratingInformationContainer.append("<div id='ratingInformationContainer' class='container showGroup background-grey'>");

			// ratingInformationContainer.append("<div class='row'>");
			// ratingInformationContainer.append("<div class='col-xs-12'>");
			// ratingInformationContainer.append("<h2>RATING INFORMATION</h2>");
			// ratingInformationContainer.append(" </div>");
			// ratingInformationContainer.append(" </div>");

			ratingInformationContainer.append("<div class='row'>");
			ratingInformationContainer
			.append("<div class='col-md-4 centeralized-div'>\t<div><h4>Seekretmeter</h4></div>  \t\t<div id='seekretMeter'>\t\t\t\t\t<canvas id='touristicness' width='200' height='200'></canvas> \t\t</div> \t\t\t\t</div>");
			ratingInformationContainer
			.append("<div class='col-md-4 centeralized-div'>\t<div><h4>Relative POI Count</h4></div> \t<div id='relativePOICountChart'> \t\t<canvas id='poiCountRelative' width='200' height='200'></canvas>\t</div>  \t</div>");
			ratingInformationContainer
			.append("<div class='col-md-4 centeralized-div'>\t<div><h4>Realtive View Count</h4></div> <div id='relativeViewCountChart'>\t\t<canvas id='viewCountRelative' width='200' height='200'></canvas> \t</div> \t</div>");
			ratingInformationContainer.append(" </div>");

			ratingInformationContainer.append("<div class='row'>");
			ratingInformationContainer.append("<div class='col-md-4 centeralized-div'>\t&nbsp; \t\t\t\t</div>");
			ratingInformationContainer
			.append("<div class='col-md-4 centeralized-div'>\t<div><h4>Absolute POI Count</h4></div> \t<div id='absolutePOICountChart'><canvas id='poiCountOverall' width='200' height='200'></canvas> </div>  \t</div>");
			ratingInformationContainer
			.append("<div class='col-md-4 centeralized-div'>\t<div><h4>Absolute View Count</h4></div> <div id='absoluteViewCountChart'><canvas id='viewCountOverall' width='200' height='200'></canvas> </div> \t</div>");

			ratingInformationContainer.append(" </div>");

			ratingInformationContainer.append(" </div>");
		}
		return ratingInformationContainer.toString();
	}

	public String createVoteButtons() {
		StringBuilder createVoteButtons = new StringBuilder();

		createVoteButtons.append("<div class='container showGroup' id='voteButtonContainer'>");
		createVoteButtons.append("<div class='row'>");
		createVoteButtons.append("<div class='col-md-4 centeralized-div'><button type='submit' id='btnDismiss'  class='round-button dismiss'>Dismiss</button></div>");
		createVoteButtons.append("<div class='col-md-4 centeralized-div'><button type='submit' id='btnTouristic'  class='round-button touristic' >Touristic</button>\t</div>");
		createVoteButtons.append("<div class='col-md-4 centeralized-div'><button type='submit' id='btnSeekret'  class='round-button seekret' >Seekret</button>\t</div>");
		createVoteButtons.append(" </div>");
		createVoteButtons.append(" </div>");

		return createVoteButtons.toString();
	}

	public SeekretUser getCurrentUser(){
		return (SeekretUser) this.session.getAttribute("currentUser");
	}

	public String getCurrentUserEmail(){
		return ((SeekretUser) this.session.getAttribute("currentUser")).getEmail();
	}

	public boolean checkIfClusterWasAlreadyRated(String clusterDatastoreKey){
		Map<String, String> keys = ratingResult.getKeys();
		String searchKey = getCurrentUserEmail() + clusterDatastoreKey;
		if(keys.containsKey(searchKey)){
			return true;
		}
		return false;
	}


	public boolean checkIfClusterWasAlreadyDismissed(String clusterDatastoreKey){
		Map<String, String> keys = dismissResult.getKeys();
		String searchKey = getCurrentUserEmail() + clusterDatastoreKey;
		if(keys.containsKey(searchKey)){
			return true;
		}
		return false;
	}


	public String createWhoWeAre(){
		StringBuilder createWhoWeAre = new StringBuilder();

		createWhoWeAre.append("<div class='container bottom-sapce-30'>");
		createWhoWeAre.append("<div class='row'>");
		createWhoWeAre.append("<div class='col-md-3'><div id='firstWorker'><img class='who-we-are' src='/res_html/img/photos/constantin.jpg' alt='constantin'/></div></div>");
		createWhoWeAre.append("<div class='col-md-3'><div id='firstWorker'><img class='who-we-are' src='/res_html/img/photos/julia.jpg' alt='julia'/></div></div>");
		createWhoWeAre.append("<div class='col-md-3'><div id='firstWorker'><img class='who-we-are' src='/res_html/img/photos/simon.jpg' alt='simon'/></div></div>");
		createWhoWeAre.append("<div class='col-md-3'><div id='firstWorker'><img class='who-we-are' src='/res_html/img/photos/daniel.jpg' alt='daniel'/></div></div>");
		createWhoWeAre.append(" </div>");
		createWhoWeAre.append(" </div>");

		return createWhoWeAre.toString();

	}


	public String createVoteResultField() {
		StringBuilder createVoteResultField = new StringBuilder();

		createVoteResultField.append("<div id='voteResultField' class='container'>");
		createVoteResultField.append("<div class='row'>");
		createVoteResultField.append("<div class='col-md-12'><div class='alert alert-info' id='voteResultMessage'></div></div>");
		createVoteResultField.append(" </div>");
		createVoteResultField.append(" </div>");

		return createVoteResultField.toString();
	}
	
	public boolean isFlusterFlag(HttpServletRequest request){
		return	Boolean.parseBoolean(request.getParameter("useFluster"));
	}
	
}