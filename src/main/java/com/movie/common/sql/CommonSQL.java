package com.movie.common.sql;

public class CommonSQL {
	public final static String PRE_PAGING_QRY 
	    = "SELECT * FROM ( SELECT rownum as rn, a.* FROM (  ";
	public final static String POST_PAGING_QRY 
	    = " ) a WHERE rownum <= ? ) b WHERE rn between ? and ?  ";
	
}
