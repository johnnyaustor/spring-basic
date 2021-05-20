/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.Role;
import com.jap.manymany.db.repository.RoleRepository;
import com.jap.manymany.dto.response.RoleResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.RoleService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleRepository repository;
	private final ConvertService convertService;

	@Autowired
	public RoleServiceImpl(RoleRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public RoleResponse findById(Long id) {
		Role role = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(role);
	}

	@Override
	public List<RoleResponse> findAll() {
		List<Role> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<RoleResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<Role> list = repository.findAll((Specification<Role>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<RoleResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<Role> page = repository.findAll((Specification<Role>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public RoleResponse create(Role role) {
		try {
			Role save = repository.save(role);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public RoleResponse update(Long id, Role role) {
		Optional<Role> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			role.setId(id);
			Role save = repository.save(role);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public RoleResponse put(Long id, Role role) {
		if (id == 0)
			return create(role);
		return update(id, role);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<RoleResponse> convert(List<Role> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
