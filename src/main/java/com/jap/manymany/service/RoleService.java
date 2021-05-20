/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.Role;
import com.jap.manymany.dto.response.RoleResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
	RoleResponse findById(Long id);
	List<RoleResponse> findAll();
	List<RoleResponse> findAll(Map<String, Object> filters);
	Page<RoleResponse> findAll(Map<String, Object> filters, Pageable pageable);
	RoleResponse create(Role role);
	RoleResponse update(Long id, Role role);
	RoleResponse put(Long id, Role role);
	void delete(Long id);
}
