/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import com.jap.manymany.db.entity.User;
import com.jap.manymany.dto.response.UserResponse;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	UserResponse findById(Long id);
	List<UserResponse> findAll();
	List<UserResponse> findAll(Map<String, Object> filters);
	Page<UserResponse> findAll(Map<String, Object> filters, Pageable pageable);
	UserResponse create(User user);
	UserResponse update(Long id, User user);
	UserResponse put(Long id, User user);
	void delete(Long id);
}
