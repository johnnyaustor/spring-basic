/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.Validation;
import com.jap.manymany.dto.response.ValidationResponse;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
	@ExceptionHandler(AppException.class)
	public final ResponseEntity<Object> handleAppException(AppException ex) {
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		BasicResponse response = new BasicResponse(httpStatus.value(), httpStatus.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(UnprocessableException.class)
	public final ResponseEntity<Object> handleUnprocessableException(UnprocessableException ex) {
		HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
		BasicResponse response = new BasicResponse(httpStatus.value(), httpStatus.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(ConflictException.class)
	public final ResponseEntity<Object> handleConflictException(ConflictException ex) {
		HttpStatus httpStatus = HttpStatus.CONFLICT;
		BasicResponse response = new BasicResponse(httpStatus.value(), httpStatus.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(response, httpStatus);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		BasicResponse response = new BasicResponse(httpStatus.value(), httpStatus.getReasonPhrase(), ex.getMessage());
		return new ResponseEntity<>(response, httpStatus);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		List<Validation> validation = errors(ex);
		ValidationResponse validationResponse = new ValidationResponse(httpStatus.value(), httpStatus.getReasonPhrase(), ex.getMessage(), validation);
		return new ResponseEntity<>(validationResponse, httpStatus);
	}

	protected List<Validation> errors(MethodArgumentNotValidException ex) {
		List<Validation> validations = new LinkedList<>();
		Class clazz = ex.getParameter().getParameterType();
		ex.getBindingResult().getFieldErrors().forEach((fieldError -> {
			try {
				Field fe = clazz.getDeclaredField(fieldError.getField());
				fe.setAccessible(true);

				JsonProperty jsonProperty = fe.getDeclaredAnnotation(JsonProperty.class);
				String fieldName = (jsonProperty!=null) ?
					(!jsonProperty.value().isEmpty()) ? jsonProperty.value() : fieldError.getField()
						: fieldError.getField();

				Validation validation = new Validation(fieldName, fieldError.getDefaultMessage());
				validations.add(validation);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}));
		return validations;
	}

}
