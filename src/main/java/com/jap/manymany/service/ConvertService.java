/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.Business;
import com.jap.manymany.db.entity.BusinessCategory;
import com.jap.manymany.db.entity.BusinessRole;
import com.jap.manymany.db.entity.BusinessRoleMenu;
import com.jap.manymany.db.entity.BusinessType;
import com.jap.manymany.db.entity.Menu;
import com.jap.manymany.db.entity.MenuAccess;
import com.jap.manymany.db.entity.Outlet;
import com.jap.manymany.db.entity.OutletUser;
import com.jap.manymany.db.entity.Role;
import com.jap.manymany.db.entity.RoleMenu;
import com.jap.manymany.db.entity.User;
import com.jap.manymany.dto.response.BusinessCategoryResponse;
import com.jap.manymany.dto.response.BusinessResponse;
import com.jap.manymany.dto.response.BusinessRoleMenuResponse;
import com.jap.manymany.dto.response.BusinessRoleResponse;
import com.jap.manymany.dto.response.BusinessTypeResponse;
import com.jap.manymany.dto.response.MenuAccessResponse;
import com.jap.manymany.dto.response.MenuResponse;
import com.jap.manymany.dto.response.OutletResponse;
import com.jap.manymany.dto.response.OutletUserResponse;
import com.jap.manymany.dto.response.RoleMenuResponse;
import com.jap.manymany.dto.response.RoleResponse;
import com.jap.manymany.dto.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class ConvertService {
	public OutletResponse convert(Outlet outlet) {
		if (outlet == null)
			return null;
		OutletResponse response = new OutletResponse();
		response.setId(outlet.getId());
		response.setActive(outlet.getActive());
		response.setAddress(outlet.getAddress());
		response.setDeleted(outlet.getDeleted());
		response.setEmail(outlet.getEmail());
		response.setExpiredAt(outlet.getExpiredAt());
		response.setName(outlet.getName());
		response.setPhone(outlet.getPhone());
		response.setBusiness(convert(outlet.getBusiness()));
		return response;
	}
	public MenuResponse convert(Menu menu) {
		if (menu == null)
			return null;
		MenuResponse response = new MenuResponse();
		response.setId(menu.getId());
		response.setActive(menu.getActive());
		response.setIcon(menu.getIcon());
		response.setIsMobile(menu.getIsMobile());
		response.setLink(menu.getLink());
		response.setParentId(menu.getParentId());
		response.setSorting(menu.getSorting());
		response.setText(menu.getText());
		return response;
	}
	public BusinessTypeResponse convert(BusinessType businessType) {
		if (businessType == null)
			return null;
		BusinessTypeResponse response = new BusinessTypeResponse();
		response.setId(businessType.getId());
		response.setName(businessType.getName());
		return response;
	}
	public BusinessResponse convert(Business business) {
		if (business == null)
			return null;
		BusinessResponse response = new BusinessResponse();
		response.setId(business.getId());
		response.setAddress(business.getAddress());
		response.setCity(business.getCity());
		response.setDescription(business.getDescription());
		response.setEmail(business.getEmail());
		response.setExpiredAt(business.getExpiredAt());
		response.setFacebook(business.getFacebook());
		response.setInstagram(business.getInstagram());
		response.setIsDeleted(business.getIsDeleted());
		response.setIsTrial(business.getIsTrial());
		response.setKecamatan(business.getKecamatan());
		response.setKtpName(business.getKtpName());
		response.setKtpNumber(business.getKtpNumber());
		response.setKtpPhoto(business.getKtpPhoto());
		response.setLatitude(business.getLatitude());
		response.setLogo(business.getLogo());
		response.setLongitude(business.getLongitude());
		response.setName(business.getName());
		response.setNpwpName(business.getNpwpName());
		response.setNpwpNumber(business.getNpwpNumber());
		response.setNpwpPhoto(business.getNpwpPhoto());
		response.setPayExpiredAt(business.getPayExpiredAt());
		response.setPhone(business.getPhone());
		response.setPostalCode(business.getPostalCode());
		response.setProvince(business.getProvince());
		response.setTrialEndAt(business.getTrialEndAt());
		response.setTwitter(business.getTwitter());
		response.setWebsite(business.getWebsite());
		response.setBusinessCategory(convert(business.getBusinessCategory()));
		return response;
	}
	public RoleResponse convert(Role role) {
		if (role == null)
			return null;
		RoleResponse response = new RoleResponse();
		response.setId(role.getId());
		response.setDeletable(role.getDeletable());
		response.setEditable(role.getEditable());
		response.setLevel(role.getLevel());
		response.setName(role.getName());
		response.setSystemRole(role.getSystemRole());
		return response;
	}
	public BusinessCategoryResponse convert(BusinessCategory businessCategory) {
		if (businessCategory == null)
			return null;
		BusinessCategoryResponse response = new BusinessCategoryResponse();
		response.setId(businessCategory.getId());
		response.setName(businessCategory.getName());
		response.setBusinessType(convert(businessCategory.getBusinessType()));
		return response;
	}
	public MenuAccessResponse convert(MenuAccess menuAccess) {
		if (menuAccess == null)
			return null;
		MenuAccessResponse response = new MenuAccessResponse();
		response.setLink(menuAccess.getLink());
		response.setMenu(convert(menuAccess.getMenu()));
		return response;
	}
	public BusinessRoleMenuResponse convert(BusinessRoleMenu businessRoleMenu) {
		if (businessRoleMenu == null)
			return null;
		BusinessRoleMenuResponse response = new BusinessRoleMenuResponse();
		response.setBusiness(convert(businessRoleMenu.getBusiness()));
		response.setMenu(convert(businessRoleMenu.getMenu()));
		response.setRole(convert(businessRoleMenu.getRole()));
		return response;
	}
	public UserResponse convert(User user) {
		if (user == null)
			return null;
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setActive(user.getActive());
		response.setBusinessId(user.getBusinessId());
		response.setEmail(user.getEmail());
		response.setEmailVerified(user.getEmailVerified());
		response.setForgotPasswordExpiredAt(user.getForgotPasswordExpiredAt());
		response.setForgotPasswordToken(user.getForgotPasswordToken());
		response.setIsDeleted(user.getIsDeleted());
		response.setIsOwner(user.getIsOwner());
		response.setLastName(user.getLastName());
		response.setName(user.getName());
		response.setPassword(user.getPassword());
		response.setPhone(user.getPhone());
		response.setPhoneVerified(user.getPhoneVerified());
		response.setPhoto(user.getPhoto());
		response.setTokenVerification(user.getTokenVerification());
		response.setUsername(user.getUsername());
		response.setRole(convert(user.getRole()));
		return response;
	}
	public OutletUserResponse convert(OutletUser outletUser) {
		if (outletUser == null)
			return null;
		OutletUserResponse response = new OutletUserResponse();
		response.setOutlet(convert(outletUser.getOutlet()));
		response.setUser(convert(outletUser.getUser()));
		return response;
	}
	public RoleMenuResponse convert(RoleMenu roleMenu) {
		if (roleMenu == null)
			return null;
		RoleMenuResponse response = new RoleMenuResponse();
		response.setMenu(convert(roleMenu.getMenu()));
		response.setRole(convert(roleMenu.getRole()));
		return response;
	}
	public BusinessRoleResponse convert(BusinessRole businessRole) {
		if (businessRole == null)
			return null;
		BusinessRoleResponse response = new BusinessRoleResponse();
		response.setBusinessId(businessRole.getPkey().getBusinessId());
		response.setRole(convert(businessRole.getRole()));
		return response;
	}
}
