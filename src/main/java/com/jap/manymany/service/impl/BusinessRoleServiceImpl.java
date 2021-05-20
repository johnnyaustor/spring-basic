/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.BusinessRole;
import com.jap.manymany.db.entity.BusinessRolePkey;
import com.jap.manymany.db.repository.BusinessRoleRepository;
import com.jap.manymany.dto.response.BusinessRoleResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.BusinessRoleService;
import com.jap.manymany.service.ConvertService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BusinessRoleServiceImpl implements BusinessRoleService {
	private final BusinessRoleRepository repository;
	private final ConvertService convertService;

	@Autowired
	public BusinessRoleServiceImpl(BusinessRoleRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public BusinessRoleResponse findById(BusinessRolePkey id) {
		BusinessRole businessRole = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(businessRole);
	}

	@Override
	public List<BusinessRoleResponse> findAll() {
		List<BusinessRole> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<BusinessRoleResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<BusinessRole> list = repository.findAll((Specification<BusinessRole>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<BusinessRoleResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<BusinessRole> page = repository.findAll((Specification<BusinessRole>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public BusinessRoleResponse create(BusinessRole businessRole) {
		try {
			BusinessRole save = repository.save(businessRole);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessRoleResponse update(BusinessRolePkey id, BusinessRole businessRole) {
		Optional<BusinessRole> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			businessRole.setPkey(id);
			BusinessRole save = repository.save(businessRole);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessRoleResponse put(BusinessRolePkey id, BusinessRole businessRole) {
		if (id == null)
			return create(businessRole);
		return update(id, businessRole);
	}

	@Override
	public void delete(BusinessRolePkey id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<BusinessRoleResponse> convert(List<BusinessRole> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
