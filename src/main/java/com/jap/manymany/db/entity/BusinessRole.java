/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.entity;

import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "business_role")
public class BusinessRole {
	@EmbeddedId private BusinessRolePkey pkey;

	@MapsId("roleId")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_role_id"))
	@ManyToOne
	private Role role;
}
