/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.db.entity;

import java.util.*;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "outlet_user")
public class OutletUser {
	@EmbeddedId private OutletUserPkey pkey;

	@MapsId("outletId")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_outlet_id"))
	@ManyToOne
	private Outlet outlet;

	@MapsId("userId")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_user_id"))
	@ManyToOne
	private User user;
}
