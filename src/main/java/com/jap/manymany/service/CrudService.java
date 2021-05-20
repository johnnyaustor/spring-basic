/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T,ID> {
	T findById(ID id);
	List<T> findAll();
	List<T> findAll(Map<String, Object> filters);
	Page<T> findAll(Map<String, Object> filters, Pageable pageable);
	T create(T t);
	T update(ID id, T t);
	T put(ID id, T t);
	void delete(ID id);
}
