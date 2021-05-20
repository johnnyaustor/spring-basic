/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.dto.response;

import java.io.Serializable;
import java.util.*;
import lombok.Data;

@Data
public class BusinessResponse implements Serializable {
	private Long id;
	private String address;
	private String city;
	private String description;
	private String email;
	private Date expiredAt;
	private String facebook;
	private String instagram;
	private Boolean isDeleted;
	private Boolean isTrial;
	private String kecamatan;
	private String ktpName;
	private String ktpNumber;
	private String ktpPhoto;
	private String latitude;
	private String logo;
	private String longitude;
	private String name;
	private String npwpName;
	private String npwpNumber;
	private String npwpPhoto;
	private Date payExpiredAt;
	private String phone;
	private String postalCode;
	private String province;
	private Date trialEndAt;
	private String twitter;
	private String website;
	private BusinessCategoryResponse businessCategory;
}
