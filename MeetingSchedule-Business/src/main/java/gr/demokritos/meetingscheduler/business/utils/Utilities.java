package gr.demokritos.meetingscheduler.business.utils;

import org.apache.commons.lang3.StringUtils;

import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;

public class Utilities {
	public static String booleanToString(Boolean booleanParam) {
		if(booleanParam!=null) {
			if(booleanParam) {
				return DbConstants.YES;
			} else {
				return DbConstants.NO;
			}
		}
		return null;
	}
	
	public static Boolean stringToBoolean(String param) {
		if(!StringUtils.isBlank(param)) {
			if(param.equalsIgnoreCase(DbConstants.YES)) {
				return true;
			} else {
				return false;
			}
		}
		return null;
	}
}
