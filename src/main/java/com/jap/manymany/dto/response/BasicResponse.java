/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BasicResponse {
	private int statusCode;
	private String status;
	private String message;

	public BasicResponse(){
		this(HttpStatus.OK);
	}
	public BasicResponse(String message) {
		this(HttpStatus.OK, message);
	}
	public BasicResponse(HttpStatus httpStatus) {
		this(httpStatus, "");
	}
	public BasicResponse(HttpStatus httpStatus, String message) {
		this.statusCode = httpStatus.value();
		this.status = httpStatus.getReasonPhrase();
		this.message = message;
	}
}
