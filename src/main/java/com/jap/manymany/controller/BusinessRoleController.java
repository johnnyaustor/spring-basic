/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.BusinessRole;
import com.jap.manymany.db.entity.BusinessRolePkey;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.BusinessRoleResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.service.BusinessRoleService;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/business-role")
public class BusinessRoleController {
	private final BusinessRoleService service;

	public BusinessRoleController(BusinessRoleService service) {
		this.service = service;
	}

	@GetMapping("/business-id/{businessId}/role-id/{roleId}")
	public ResponseEntity<?> findById(@PathVariable Long businessId, @PathVariable Long roleId) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(new BusinessRolePkey(businessId, roleId))));
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
	public ResponseEntity<?> create(@Valid @RequestBody BusinessRole businessRole) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		BusinessRoleResponse businessRoleCreated = service.create(businessRole);
		String uri = "/v1/business-role".concat("/business-id/").concat(businessRoleCreated.getBusinessId().toString()).concat("/role-id/").concat(businessRoleCreated.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/business-id/{businessId}/role-id/{roleId}")
	public ResponseEntity<?> patch(@PathVariable Long businessId, @PathVariable Long roleId, @Valid @RequestBody BusinessRole businessRole) {
		service.update(new BusinessRolePkey(businessId, roleId), businessRole);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/business-id/{businessId}/role-id/{roleId}")
	public ResponseEntity<?> put(@PathVariable Long businessId, @PathVariable Long roleId, @Valid @RequestBody BusinessRole businessRole) {
		BusinessRoleResponse businessRolePut = service.put(new BusinessRolePkey(businessId, roleId), businessRole);
		if (businessRolePut.getBusinessId().equals(businessId) && businessRolePut.getRole().getId().equals(roleId)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/business-role".concat("/business-id/").concat(businessRolePut.getBusinessId().toString()).concat("/role-id/").concat(businessRolePut.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/business-id/{businessId}/role-id/{roleId}")
	public ResponseEntity<?> delete(@PathVariable Long businessId, @PathVariable Long roleId) {
		service.delete(new BusinessRolePkey(businessId, roleId));
		return ResponseEntity.noContent().build();
	}

}
