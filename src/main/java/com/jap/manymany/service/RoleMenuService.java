/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.RoleMenu;
import com.jap.manymany.db.entity.RoleMenuPkey;
import com.jap.manymany.dto.response.RoleMenuResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleMenuService {
	RoleMenuResponse findById(RoleMenuPkey id);
	List<RoleMenuResponse> findAll();
	List<RoleMenuResponse> findAll(Map<String, Object> filters);
	Page<RoleMenuResponse> findAll(Map<String, Object> filters, Pageable pageable);
	RoleMenuResponse create(RoleMenu roleMenu);
	RoleMenuResponse update(RoleMenuPkey id, RoleMenu roleMenu);
	RoleMenuResponse put(RoleMenuPkey id, RoleMenu roleMenu);
	void delete(RoleMenuPkey id);
}
