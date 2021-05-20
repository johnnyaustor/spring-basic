/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.entity;

import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role_menu")
public class RoleMenu {
	@EmbeddedId private RoleMenuPkey pkey;

	@MapsId("menuId")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_menu_id"))
	@ManyToOne
	private Menu menu;

	@MapsId("roleId")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_role_id"))
	@ManyToOne
	private Role role;
}
