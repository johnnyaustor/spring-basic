/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.OutletUser;
import com.jap.manymany.db.entity.OutletUserPkey;
import com.jap.manymany.db.repository.OutletUserRepository;
import com.jap.manymany.dto.response.OutletUserResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.OutletUserService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class OutletUserServiceImpl implements OutletUserService {
	private final OutletUserRepository repository;
	private final ConvertService convertService;

	@Autowired
	public OutletUserServiceImpl(OutletUserRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public OutletUserResponse findById(OutletUserPkey id) {
		OutletUser outletUser = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(outletUser);
	}

	@Override
	public List<OutletUserResponse> findAll() {
		List<OutletUser> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<OutletUserResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<OutletUser> list = repository.findAll((Specification<OutletUser>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<OutletUserResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<OutletUser> page = repository.findAll((Specification<OutletUser>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public OutletUserResponse create(OutletUser outletUser) {
		try {
			OutletUser save = repository.save(outletUser);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public OutletUserResponse update(OutletUserPkey id, OutletUser outletUser) {
		Optional<OutletUser> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			outletUser.setPkey(id);
			OutletUser save = repository.save(outletUser);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public OutletUserResponse put(OutletUserPkey id, OutletUser outletUser) {
		if (id == null)
			return create(outletUser);
		return update(id, outletUser);
	}

	@Override
	public void delete(OutletUserPkey id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<OutletUserResponse> convert(List<OutletUser> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
