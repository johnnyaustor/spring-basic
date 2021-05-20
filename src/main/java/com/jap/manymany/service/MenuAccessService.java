/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.MenuAccess;
import com.jap.manymany.db.entity.MenuAccessPkey;
import com.jap.manymany.dto.response.MenuAccessResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuAccessService {
	MenuAccessResponse findById(MenuAccessPkey id);
	List<MenuAccessResponse> findAll();
	List<MenuAccessResponse> findAll(Map<String, Object> filters);
	Page<MenuAccessResponse> findAll(Map<String, Object> filters, Pageable pageable);
	MenuAccessResponse create(MenuAccess menuAccess);
	MenuAccessResponse update(MenuAccessPkey id, MenuAccess menuAccess);
	MenuAccessResponse put(MenuAccessPkey id, MenuAccess menuAccess);
	void delete(MenuAccessPkey id);
}
