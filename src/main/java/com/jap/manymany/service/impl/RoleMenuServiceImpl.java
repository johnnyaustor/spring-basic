/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.RoleMenu;
import com.jap.manymany.db.entity.RoleMenuPkey;
import com.jap.manymany.db.repository.RoleMenuRepository;
import com.jap.manymany.dto.response.RoleMenuResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.RoleMenuService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
	private final RoleMenuRepository repository;
	private final ConvertService convertService;

	@Autowired
	public RoleMenuServiceImpl(RoleMenuRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public RoleMenuResponse findById(RoleMenuPkey id) {
		RoleMenu roleMenu = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(roleMenu);
	}

	@Override
	public List<RoleMenuResponse> findAll() {
		List<RoleMenu> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<RoleMenuResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<RoleMenu> list = repository.findAll((Specification<RoleMenu>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<RoleMenuResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<RoleMenu> page = repository.findAll((Specification<RoleMenu>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public RoleMenuResponse create(RoleMenu roleMenu) {
		try {
			RoleMenu save = repository.save(roleMenu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public RoleMenuResponse update(RoleMenuPkey id, RoleMenu roleMenu) {
		Optional<RoleMenu> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			roleMenu.setPkey(id);
			RoleMenu save = repository.save(roleMenu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public RoleMenuResponse put(RoleMenuPkey id, RoleMenu roleMenu) {
		if (id == null)
			return create(roleMenu);
		return update(id, roleMenu);
	}

	@Override
	public void delete(RoleMenuPkey id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<RoleMenuResponse> convert(List<RoleMenu> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
