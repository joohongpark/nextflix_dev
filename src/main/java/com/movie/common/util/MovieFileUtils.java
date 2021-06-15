package com.movie.common.util;

import java.text.DecimalFormat;

public class MovieFileUtils {
	
	public static String getFancySize(long size) {
		DecimalFormat form = new DecimalFormat("#,###.0");
		if(size < 1024) {
			return size + "byte";
		} else if(size < 1024*1024) {
			return form.format(size/1024.0) + "KB";
		} else if(size < 1024*1024*1024) {
			return form.format(size / (1024.0*1024.0)) + "MB";
		} else {
			return form.format(size/(1024*1024*1024.0)) + "GB";
		}
	}
	
	 public static void main(String[] args) {
//		  StudyFileUtils.getFancySize(256) -> 256byte
//		  StudyFileUtils.getFancySize(15210) -> 15.2KB
//		  StudyFileUtils.getFancySize(12935630) -> 12.9MB
		 System.out.println(MovieFileUtils.getFancySize(256));
		 System.out.println(MovieFileUtils.getFancySize(15210));
		 System.out.println(MovieFileUtils.getFancySize(12935630));
		 
		 String job = "JB03";
		 System.out.println("JB03".equals(job) ? "Hello":"World"); 
		 
		 
		 
	}
	
}
