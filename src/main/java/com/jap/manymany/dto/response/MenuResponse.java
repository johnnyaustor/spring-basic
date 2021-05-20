/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class MenuResponse implements Serializable {
	private Long id;
	private Boolean active;
	private String icon;
	private Boolean isMobile;
	private String link;
	private Long parentId;
	private Integer sorting;
	private String text;
}
