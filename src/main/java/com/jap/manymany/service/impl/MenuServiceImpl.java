/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.Menu;
import com.jap.manymany.db.repository.MenuRepository;
import com.jap.manymany.dto.response.MenuResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.MenuService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
	private final MenuRepository repository;
	private final ConvertService convertService;

	@Autowired
	public MenuServiceImpl(MenuRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public MenuResponse findById(Long id) {
		Menu menu = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(menu);
	}

	@Override
	public List<MenuResponse> findAll() {
		List<Menu> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<MenuResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<Menu> list = repository.findAll((Specification<Menu>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<MenuResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<Menu> page = repository.findAll((Specification<Menu>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public MenuResponse create(Menu menu) {
		try {
			Menu save = repository.save(menu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public MenuResponse update(Long id, Menu menu) {
		Optional<Menu> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			menu.setId(id);
			Menu save = repository.save(menu);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public MenuResponse put(Long id, Menu menu) {
		if (id == 0)
			return create(menu);
		return update(id, menu);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<MenuResponse> convert(List<Menu> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
