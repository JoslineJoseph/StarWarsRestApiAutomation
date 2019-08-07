package com.loyaltyone.utility;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;



public class LoggerUtility {

	private static Logger log ;

	public static List <String> messageList = new ArrayList<>();
	String name;
	public LoggerUtility(String name) {
		this.name = name;
		log = Logger.getLogger(name);
	}


	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		LoggerUtility.log = log;
	}


	public void info(String message) { log.info(message); messageList.add(name
			+":"+ log.getLevel()+ " - "+ message + "\n"); }


	public void error(String message) {
		log.error(message);
		messageList.add(name +":"+ log.getLevel()+ " - "+ message + "\n");

	}

	public void warning(String message) {
		log.warn(message);
		messageList.add(name +":"+ log.getLevel()+ " - "+ message + "\n");

	}

}

