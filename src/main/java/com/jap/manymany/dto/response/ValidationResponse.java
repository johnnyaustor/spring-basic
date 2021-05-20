/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.dto.response;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationResponse extends BasicResponse {
	private List<Validation> validation = new LinkedList<>();
	public ValidationResponse(int errorStatus, String status, String message, List<Validation> validation) {
		super(errorStatus, status, message);
		this.validation = validation;
	}
}
