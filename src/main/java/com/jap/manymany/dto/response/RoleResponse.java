/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class RoleResponse implements Serializable {
	private Long id;
	private Boolean deletable;
	private Boolean editable;
	private Integer level;
	private String name;
	private Boolean systemRole;
}
