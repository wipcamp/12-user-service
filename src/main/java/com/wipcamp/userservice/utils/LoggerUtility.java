package com.wipcamp.userservice.utils;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtility {

	public static void logUserError(Logger logger, String message, String functionName, long wipId, Exception e) {
		logger.error(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message + ": " + e);
	}

	public static void logUserError(Logger logger, String message, String functionName, String lineId, Exception e) {
		logger.error(functionName + ": " + lineId + ": " + LoggerStatus.FAILED + ": " + message + ": " + e);
	}

	public static void logUserError(Logger logger, String message, String functionName, long wipId) {
		logger.error(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logUserError(Logger logger, String message, String functionName, String wipId) {
		logger.error(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logError(Logger logger, String message, String functionName, Exception e) {
		logger.error(functionName + ": " + LoggerStatus.FAILED + ": " + message + ": " + e);
	}

	public static void logError(Logger logger, String message, String functionName) {
		logger.error(functionName + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logUserSuccessInfo(Logger logger, String message, String functionName, long wipId) {
		logger.info(functionName + ": " + wipId + ": " + LoggerStatus.SUCCESS + ": " + message);
	}

	public static void logUserSuccessInfo(Logger logger, String message, String functionName, String lineId) {
		logger.info(functionName + ": " + lineId + ": " + LoggerStatus.SUCCESS + ": " + message);
	}

	public static void logSuccessInfo(Logger logger, String message, String functionName) {
		logger.info(functionName + ": " + LoggerStatus.SUCCESS + ": " + message);
	}

	public static void logUserFailInfo(Logger logger, String message, String functionName, long wipId) {
		logger.info(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logUserSuccessWarning(Logger logger, String message, String functionName, long wipId) {
		logger.warn(functionName + ": " + wipId + ": " + LoggerStatus.SUCCESS + ": " + message);
	}

	public static void logUserFailWarning(Logger logger, String message, String functionName, long wipId) {
		logger.warn(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logSuccessWarning(Logger logger, String message, String functionName, long wipId) {
		logger.warn(functionName + ": " + wipId + ": " + LoggerStatus.SUCCESS + ": " + message);
	}

	public static void logFailWarning(Logger logger, String message, String functionName, long wipId) {
		logger.warn(functionName + ": " + wipId + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logFailWarning(Logger logger, String message, String functionName, String id) {
		logger.warn(functionName + ": " + id + ": " + LoggerStatus.FAILED + ": " + message);
	}

	public static void logFailWarning(Logger logger, String message, String functionName) {
		logger.warn(functionName + ": " + LoggerStatus.FAILED + ": " + message);
	}

}
