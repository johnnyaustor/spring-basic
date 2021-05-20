/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class DataResponse<T> extends BasicResponse {
	private T data;
	public DataResponse(T data) {
		this(HttpStatus.OK, data);
	}
	public DataResponse(HttpStatus httpStatus, T data) {
		super(httpStatus);
		this.data = data;
	}
}
