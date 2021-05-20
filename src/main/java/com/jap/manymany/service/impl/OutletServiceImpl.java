/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.Outlet;
import com.jap.manymany.db.repository.OutletRepository;
import com.jap.manymany.dto.response.OutletResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.OutletService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OutletServiceImpl implements OutletService {
	private final OutletRepository repository;
	private final ConvertService convertService;

	@Autowired
	public OutletServiceImpl(OutletRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public OutletResponse findById(Long id) {
		Outlet outlet = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(outlet);
	}

	@Override
	public List<OutletResponse> findAll() {
		List<Outlet> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<OutletResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<Outlet> list = repository.findAll((Specification<Outlet>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<OutletResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<Outlet> page = repository.findAll((Specification<Outlet>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public OutletResponse create(Outlet outlet) {
		try {
			Outlet save = repository.save(outlet);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public OutletResponse update(Long id, Outlet outlet) {
		Optional<Outlet> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			outlet.setId(id);
			Outlet save = repository.save(outlet);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public OutletResponse put(Long id, Outlet outlet) {
		if (id == 0)
			return create(outlet);
		return update(id, outlet);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<OutletResponse> convert(List<Outlet> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
