/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.service.impl;

import com.jap.manymany.db.entity.User;
import com.jap.manymany.db.repository.UserRepository;
import com.jap.manymany.dto.response.UserResponse;
import com.jap.manymany.exception.NotFoundException;
import com.jap.manymany.exception.UnprocessableException;
import com.jap.manymany.service.ConvertService;
import com.jap.manymany.service.UserService;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final ConvertService convertService;

	@Autowired
	public UserServiceImpl(UserRepository repository, ConvertService convertService) {
		this.repository = repository;
		this.convertService = convertService;
	}

	@Override
	public UserResponse findById(Long id) {
		User user = repository.findById(id).orElseThrow(NotFoundException::new);
		return convertService.convert(user);
	}

	@Override
	public List<UserResponse> findAll() {
		List<User> list = repository.findAll();
		return convert(list);
	}

	@Override
	public List<UserResponse> findAll(Map<String, Object> filters) {
		if (filters.isEmpty())
			return findAll();

		List<User> list = repository.findAll((Specification<User>) (root, query, criteriaBuilder) -> null);
		return convert(list);
	}

	@Override
	public Page<UserResponse> findAll(Map<String, Object> filters, Pageable pageable) {
		Page<User> page = repository.findAll((Specification<User>) (root, query, criteriaBuilder) -> null, pageable);
		return page.map(convertService::convert);
	}

	@Override
	public UserResponse create(User user) {
		try {
			User save = repository.save(user);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public UserResponse update(Long id, User user) {
		Optional<User> byId = repository.findById(id);
		if (!byId.isPresent())
			throw new UnprocessableException("Id Not Found!");

		try {
			user.setId(id);
			User save = repository.save(user);
			return convertService.convert(save);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	@Override
	public UserResponse put(Long id, User user) {
		if (id == 0)
			return create(user);
		return update(id, user);
	}

	@Override
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception ex) {
			throw new UnprocessableException(ex.getMessage());
		}
	}

	private List<UserResponse> convert(List<User> list) {
		if (list.isEmpty())
			return new ArrayList<>();
		return list.stream().map(convertService::convert).collect(Collectors.toList());
	}}
