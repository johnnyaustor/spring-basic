/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.BusinessRoleMenu;
import com.jap.manymany.db.entity.BusinessRoleMenuPkey;
import com.jap.manymany.db.repository.BusinessRoleMenuRepository;
import com.jap.manymany.dto.response.BusinessRoleMenuResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.BusinessRoleMenuService;
import com.jap.manymany.service.ConvertService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BusinessRoleMenuServiceImpl implements BusinessRoleMenuService {
	private final BusinessRoleMenuRepository repository;
	private final ConvertService convertService;

	@Autowired
	public BusinessRoleMenuServiceImpl(BusinessRoleMenuRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public BusinessRoleMenuResponse findById(BusinessRoleMenuPkey id) {
		BusinessRoleMenu businessRoleMenu = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(businessRoleMenu);
	}

	@Override
	public List<BusinessRoleMenuResponse> findAll() {
		List<BusinessRoleMenu> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<BusinessRoleMenuResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<BusinessRoleMenu> list = repository.findAll((Specification<BusinessRoleMenu>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<BusinessRoleMenuResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<BusinessRoleMenu> page = repository.findAll((Specification<BusinessRoleMenu>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public BusinessRoleMenuResponse create(BusinessRoleMenu businessRoleMenu) {
		try {
			BusinessRoleMenu save = repository.save(businessRoleMenu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessRoleMenuResponse update(BusinessRoleMenuPkey id, BusinessRoleMenu businessRoleMenu) {
		Optional<BusinessRoleMenu> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			businessRoleMenu.setPkey(id);
			BusinessRoleMenu save = repository.save(businessRoleMenu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessRoleMenuResponse put(BusinessRoleMenuPkey id, BusinessRoleMenu businessRoleMenu) {
		if (id == null)
			return create(businessRoleMenu);
		return update(id, businessRoleMenu);
	}

	@Override
	public void delete(BusinessRoleMenuPkey id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<BusinessRoleMenuResponse> convert(List<BusinessRoleMenu> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
