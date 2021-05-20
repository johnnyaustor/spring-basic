/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.Outlet;
import com.jap.manymany.dto.response.OutletResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutletService {
	OutletResponse findById(Long id);
	List<OutletResponse> findAll();
	List<OutletResponse> findAll(Map<String, Object> filters);
	Page<OutletResponse> findAll(Map<String, Object> filters, Pageable pageable);
	OutletResponse create(Outlet outlet);
	OutletResponse update(Long id, Outlet outlet);
	OutletResponse put(Long id, Outlet outlet);
	void delete(Long id);
}
