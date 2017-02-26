package com.programming4phone.weather.watcher.collector;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Error invoking the World Weather Online web service")  
public class WwoException extends RuntimeException{
	private static final long serialVersionUID = -5763015773393831752L;

	WwoException(String message){
		super(message);
	}

}
