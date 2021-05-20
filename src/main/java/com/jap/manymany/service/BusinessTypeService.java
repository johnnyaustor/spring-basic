/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.BusinessType;
import com.jap.manymany.dto.response.BusinessTypeResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessTypeService {
	BusinessTypeResponse findById(Long id);
	List<BusinessTypeResponse> findAll();
	List<BusinessTypeResponse> findAll(Map<String, Object> filters);
	Page<BusinessTypeResponse> findAll(Map<String, Object> filters, Pageable pageable);
	BusinessTypeResponse create(BusinessType businessType);
	BusinessTypeResponse update(Long id, BusinessType businessType);
	BusinessTypeResponse put(Long id, BusinessType businessType);
	void delete(Long id);
}
