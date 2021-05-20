/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.Business;
import com.jap.manymany.dto.response.BusinessResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusinessService {
	BusinessResponse findById(Long id);
	List<BusinessResponse> findAll();
	List<BusinessResponse> findAll(Map<String, Object> filters);
	Page<BusinessResponse> findAll(Map<String, Object> filters, Pageable pageable);
	BusinessResponse create(Business business);
	BusinessResponse update(Long id, Business business);
	BusinessResponse put(Long id, Business business);
	void delete(Long id);
}
