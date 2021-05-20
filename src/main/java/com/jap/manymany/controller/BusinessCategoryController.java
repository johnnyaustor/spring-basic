/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.BusinessCategory;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.BusinessCategoryResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.service.BusinessCategoryService;
import java.net.URI;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/business-category")
public class BusinessCategoryController {
	private final BusinessCategoryService service;

	public BusinessCategoryController(BusinessCategoryService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(id)));
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, Object> filters) {
		if (filters.isEmpty())
			return ResponseEntity.ok(new DataResponse<>(service.findAll()));

		return ResponseEntity.ok(new DataResponse<>(service.findAll(filters)));
	}

	@GetMapping("/pageable")
	public ResponseEntity<?> findAllPageable(@RequestParam(required = false) Map<String, Object> filters, Pageable pageable) {
		return ResponseEntity.ok(new DataResponse<>(service.findAll(filters, pageable)));
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody BusinessCategory businessCategory, HttpServletRequest request) {
		BusinessCategoryResponse businessCategoryCreated = service.create(businessCategory);
		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = request.getRequestURI().concat("/").concat(businessCategoryCreated.getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> patch(@PathVariable Long id, @Valid @RequestBody BusinessCategory businessCategory) {
		service.update(id, businessCategory);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody BusinessCategory businessCategory) {
		BusinessCategoryResponse businessCategoryPut = service.put(id, businessCategory);
		if (businessCategoryPut.getId().equals(id)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/business-category/".concat(businessCategoryPut.getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
