/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class BusinessRoleMenuResponse implements Serializable {
	private BusinessResponse business;
	private MenuResponse menu;
	private RoleResponse role;
}
