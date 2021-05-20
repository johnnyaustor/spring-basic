/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class UserResponse implements Serializable {
	private Long id;
	private Boolean active;
	private Long businessId;
	private String email;
	private Boolean emailVerified;
	private Date forgotPasswordExpiredAt;
	private String forgotPasswordToken;
	private Boolean isDeleted;
	private Boolean isOwner;
	private String lastName;
	private String name;
	private String password;
	private String phone;
	private Boolean phoneVerified;
	private String photo;
	private String tokenVerification;
	private String username;
	private RoleResponse role;
}
