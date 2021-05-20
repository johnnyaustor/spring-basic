/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.BusinessCategory;
import com.jap.manymany.db.repository.BusinessCategoryRepository;
import com.jap.manymany.dto.response.BusinessCategoryResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.BusinessCategoryService;
import com.jap.manymany.service.ConvertService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class BusinessCategoryServiceImpl implements BusinessCategoryService {
	private final BusinessCategoryRepository repository;
	private final ConvertService convertService;

	@Autowired
	public BusinessCategoryServiceImpl(BusinessCategoryRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public BusinessCategoryResponse findById(Long id) {
		BusinessCategory businessCategory = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(businessCategory);
	}

	@Override
	public List<BusinessCategoryResponse> findAll() {
		List<BusinessCategory> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<BusinessCategoryResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<BusinessCategory> list = repository.findAll((Specification<BusinessCategory>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<BusinessCategoryResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<BusinessCategory> page = repository.findAll((Specification<BusinessCategory>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public BusinessCategoryResponse create(BusinessCategory businessCategory) {
		try {
			BusinessCategory save = repository.save(businessCategory);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessCategoryResponse update(Long id, BusinessCategory businessCategory) {
		Optional<BusinessCategory> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			businessCategory.setId(id);
			BusinessCategory save = repository.save(businessCategory);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public BusinessCategoryResponse put(Long id, BusinessCategory businessCategory) {
		if (id == 0)
			return create(businessCategory);
		return update(id, businessCategory);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<BusinessCategoryResponse> convert(List<BusinessCategory> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
