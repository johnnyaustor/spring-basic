/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.BusinessRoleMenu;
import com.jap.manymany.db.entity.BusinessRoleMenuPkey;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.BusinessRoleMenuResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.service.BusinessRoleMenuService;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/business-role-menu")
public class BusinessRoleMenuController {
	private final BusinessRoleMenuService service;

	public BusinessRoleMenuController(BusinessRoleMenuService service) {
		this.service = service;
	}

	@GetMapping("/business-id/{businessId}/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> findById(@PathVariable Long businessId, @PathVariable Long menuId, @PathVariable Long roleId) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(new BusinessRoleMenuPkey(businessId, menuId, roleId))));
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
	public ResponseEntity<?> create(@Valid @RequestBody BusinessRoleMenu businessRoleMenu) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		BusinessRoleMenuResponse businessRoleMenuCreated = service.create(businessRoleMenu);
		String uri = "/v1/business-role-menu".concat("/business-id/").concat(businessRoleMenuCreated.getBusiness().getId().toString()).concat("/menu-id/").concat(businessRoleMenuCreated.getMenu().getId().toString()).concat("/role-id/").concat(businessRoleMenuCreated.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/business-id/{businessId}/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> patch(@PathVariable Long businessId, @PathVariable Long menuId, @PathVariable Long roleId, @Valid @RequestBody BusinessRoleMenu businessRoleMenu) {
		service.update(new BusinessRoleMenuPkey(businessId, menuId, roleId), businessRoleMenu);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/business-id/{businessId}/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> put(@PathVariable Long businessId, @PathVariable Long menuId, @PathVariable Long roleId, @Valid @RequestBody BusinessRoleMenu businessRoleMenu) {
		BusinessRoleMenuResponse businessRoleMenuPut = service.put(new BusinessRoleMenuPkey(businessId, menuId, roleId), businessRoleMenu);
		if (businessRoleMenuPut.getBusiness().getId().equals(businessId) && businessRoleMenuPut.getMenu().getId().equals(menuId) && businessRoleMenuPut.getRole().getId().equals(roleId)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/business-role-menu".concat("/business-id/").concat(businessRoleMenuPut.getBusiness().getId().toString()).concat("/menu-id/").concat(businessRoleMenuPut.getMenu().getId().toString()).concat("/role-id/").concat(businessRoleMenuPut.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/business-id/{businessId}/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> delete(@PathVariable Long businessId, @PathVariable Long menuId, @PathVariable Long roleId) {
		service.delete(new BusinessRoleMenuPkey(businessId, menuId, roleId));
		return ResponseEntity.noContent().build();
	}

}
