/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.BusinessCategory;
import com.jap.manymany.dto.response.BusinessCategoryResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessCategoryService {
	BusinessCategoryResponse findById(Long id);
	List<BusinessCategoryResponse> findAll();
	List<BusinessCategoryResponse> findAll(Map<String, Object> filters);
	Page<BusinessCategoryResponse> findAll(Map<String, Object> filters, Pageable pageable);
	BusinessCategoryResponse create(BusinessCategory businessCategory);
	BusinessCategoryResponse update(Long id, BusinessCategory businessCategory);
	BusinessCategoryResponse put(Long id, BusinessCategory businessCategory);
	void delete(Long id);
}
