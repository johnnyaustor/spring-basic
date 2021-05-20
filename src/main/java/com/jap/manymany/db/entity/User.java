/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.entity;

import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne
	private Role role;
}
