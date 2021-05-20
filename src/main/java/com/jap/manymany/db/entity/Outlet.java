/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.entity;

import java.util.*;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "outlet")
@EqualsAndHashCode(callSuper = true)
public class Outlet extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean active;
	private String address;
	private Boolean deleted;
	private String email;
	private Date expiredAt;
	private String name;
	private String phone;

	@ManyToOne
	private Business business;
}
