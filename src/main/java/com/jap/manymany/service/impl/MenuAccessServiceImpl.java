/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.MenuAccess;
import com.jap.manymany.db.entity.MenuAccessPkey;
import com.jap.manymany.db.repository.MenuAccessRepository;
import com.jap.manymany.dto.response.MenuAccessResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.MenuAccessService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MenuAccessServiceImpl implements MenuAccessService {
	private final MenuAccessRepository repository;
	private final ConvertService convertService;

	@Autowired
	public MenuAccessServiceImpl(MenuAccessRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public MenuAccessResponse findById(MenuAccessPkey id) {
		MenuAccess menuAccess = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(menuAccess);
	}

	@Override
	public List<MenuAccessResponse> findAll() {
		List<MenuAccess> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<MenuAccessResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<MenuAccess> list = repository.findAll((Specification<MenuAccess>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<MenuAccessResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<MenuAccess> page = repository.findAll((Specification<MenuAccess>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public MenuAccessResponse create(MenuAccess menuAccess) {
		try {
			MenuAccess save = repository.save(menuAccess);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public MenuAccessResponse update(MenuAccessPkey id, MenuAccess menuAccess) {
		Optional<MenuAccess> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			menuAccess.setPkey(id);
			MenuAccess save = repository.save(menuAccess);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public MenuAccessResponse put(MenuAccessPkey id, MenuAccess menuAccess) {
		if (id == null)
			return create(menuAccess);
		return update(id, menuAccess);
	}

	@Override
	public void delete(MenuAccessPkey id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<MenuAccessResponse> convert(List<MenuAccess> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
