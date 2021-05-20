/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.Business;
import com.jap.manymany.db.repository.BusinessRepository;
import com.jap.manymany.dto.response.BusinessResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.BusinessService;
import com.jap.manymany.service.ConvertService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
	private final BusinessRepository repository;
	private final ConvertService convertService;

	@Autowired
	public BusinessServiceImpl(BusinessRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public BusinessResponse findById(Long id) {
		Business business = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(business);
	}

	@Override
	public List<BusinessResponse> findAll() {
		List<Business> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<BusinessResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<Business> list = repository.findAll((Specification<Business>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<BusinessResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<Business> page = repository.findAll((Specification<Business>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public BusinessResponse create(Business business) {
		try {
			Business save = repository.save(business);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessResponse update(Long id, Business business) {
		Optional<Business> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			business.setId(id);
			Business save = repository.save(business);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessResponse put(Long id, Business business) {
		if (id == 0)
			return create(business);
		return update(id, business);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<BusinessResponse> convert(List<Business> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
