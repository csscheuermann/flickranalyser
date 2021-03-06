package com.seekret.memcache;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class MemCacheConstants implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public final static Date MEMCACH_EXPIRE_DATE = new Date(new GregorianCalendar(2014,8,30).getTimeInMillis());
	
	public static final String TOP_TEN_SPOT_LIST = "topTenSpotList";
	public static final String SPOT_LIST_REFRESH_TIME = "spotRefreshTime";
}
