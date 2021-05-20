/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException {
	private int statusCode;
	private String status;
	private String message;

	public AppException(int statusCode, String status, String message) {
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
	}
	@Override
	public String getMessage() { return String.format("[%s-%s] %s", this.statusCode, this.status, this.message); }
}
