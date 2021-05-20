/* Generate by Johnny Austor Builder at Thu May 20 15:00:00 ICT 2021 */
package com.jap.manymany.exception;

public class ConflictException extends RuntimeException {
	public ConflictException() { super("Data Already Exist"); }
	public ConflictException(String message) { super(message); }
}
