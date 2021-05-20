/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.Menu;
import com.jap.manymany.dto.response.MenuResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {
	MenuResponse findById(Long id);
	List<MenuResponse> findAll();
	List<MenuResponse> findAll(Map<String, Object> filters);
	Page<MenuResponse> findAll(Map<String, Object> filters, Pageable pageable);
	MenuResponse create(Menu menu);
	MenuResponse update(Long id, Menu menu);
	MenuResponse put(Long id, Menu menu);
	void delete(Long id);
}
