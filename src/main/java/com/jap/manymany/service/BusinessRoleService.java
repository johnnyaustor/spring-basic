/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.BusinessRole;
import com.jap.manymany.db.entity.BusinessRolePkey;
import com.jap.manymany.dto.response.BusinessRoleResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessRoleService {
	BusinessRoleResponse findById(BusinessRolePkey id);
	List<BusinessRoleResponse> findAll();
	List<BusinessRoleResponse> findAll(Map<String, Object> filters);
	Page<BusinessRoleResponse> findAll(Map<String, Object> filters, Pageable pageable);
	BusinessRoleResponse create(BusinessRole businessRole);
	BusinessRoleResponse update(BusinessRolePkey id, BusinessRole businessRole);
	BusinessRoleResponse put(BusinessRolePkey id, BusinessRole businessRole);
	void delete(BusinessRolePkey id);
}
