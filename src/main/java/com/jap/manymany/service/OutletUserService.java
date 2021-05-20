/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.OutletUser;
import com.jap.manymany.db.entity.OutletUserPkey;
import com.jap.manymany.dto.response.OutletUserResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OutletUserService {
	OutletUserResponse findById(OutletUserPkey id);
	List<OutletUserResponse> findAll();
	List<OutletUserResponse> findAll(Map<String, Object> filters);
	Page<OutletUserResponse> findAll(Map<String, Object> filters, Pageable pageable);
	OutletUserResponse create(OutletUser outletUser);
	OutletUserResponse update(OutletUserPkey id, OutletUser outletUser);
	OutletUserResponse put(OutletUserPkey id, OutletUser outletUser);
	void delete(OutletUserPkey id);
}
