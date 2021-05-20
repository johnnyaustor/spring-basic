/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class OutletResponse implements Serializable {
	private Long id;
	private Boolean active;
	private String address;
	private Boolean deleted;
	private String email;
	private Date expiredAt;
	private String name;
	private String phone;
	private BusinessResponse business;
}
