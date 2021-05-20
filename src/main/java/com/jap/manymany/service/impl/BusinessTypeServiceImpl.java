/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.BusinessType;
import com.jap.manymany.db.repository.BusinessTypeRepository;
import com.jap.manymany.dto.response.BusinessTypeResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.BusinessTypeService;
import com.jap.manymany.service.ConvertService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {
	private final BusinessTypeRepository repository;
	private final ConvertService convertService;

	@Autowired
	public BusinessTypeServiceImpl(BusinessTypeRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public BusinessTypeResponse findById(Long id) {
		BusinessType businessType = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(businessType);
	}

	@Override
	public List<BusinessTypeResponse> findAll() {
		List<BusinessType> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<BusinessTypeResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<BusinessType> list = repository.findAll((Specification<BusinessType>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<BusinessTypeResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<BusinessType> page = repository.findAll((Specification<BusinessType>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public BusinessTypeResponse create(BusinessType businessType) {
		try {
			BusinessType save = repository.save(businessType);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessTypeResponse update(Long id, BusinessType businessType) {
		Optional<BusinessType> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			businessType.setId(id);
			BusinessType save = repository.save(businessType);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessTypeResponse put(Long id, BusinessType businessType) {
		if (id == 0)
			return create(businessType);
		return update(id, businessType);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<BusinessTypeResponse> convert(List<BusinessType> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
