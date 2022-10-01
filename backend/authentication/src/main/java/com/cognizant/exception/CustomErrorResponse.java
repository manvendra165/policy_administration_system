package com.cognizant.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is for customizing the exception handler
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

	private String message;
	//public String getMessage() {
	//	return message;
	//}
	//public void setMessage(String message) {
		//this.message = message;
	//}
	//public LocalDateTime getDateTime() {
		//return dateTime;
	//}
	//public void setDateTime(LocalDateTime dateTime) {
	//	this.dateTime = dateTime;
	//}
	private LocalDateTime dateTime;
}
