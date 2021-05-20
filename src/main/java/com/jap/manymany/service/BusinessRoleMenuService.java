/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.BusinessRoleMenu;
import com.jap.manymany.db.entity.BusinessRoleMenuPkey;
import com.jap.manymany.dto.response.BusinessRoleMenuResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessRoleMenuService {
	BusinessRoleMenuResponse findById(BusinessRoleMenuPkey id);
	List<BusinessRoleMenuResponse> findAll();
	List<BusinessRoleMenuResponse> findAll(Map<String, Object> filters);
	Page<BusinessRoleMenuResponse> findAll(Map<String, Object> filters, Pageable pageable);
	BusinessRoleMenuResponse create(BusinessRoleMenu businessRoleMenu);
	BusinessRoleMenuResponse update(BusinessRoleMenuPkey id, BusinessRoleMenu businessRoleMenu);
	BusinessRoleMenuResponse put(BusinessRoleMenuPkey id, BusinessRoleMenu businessRoleMenu);
	void delete(BusinessRoleMenuPkey id);
}
