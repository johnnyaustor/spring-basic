/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.RoleMenu;
import com.jap.manymany.db.entity.RoleMenuPkey;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.dto.response.RoleMenuResponse;
import com.jap.manymany.service.RoleMenuService;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/role-menu")
public class RoleMenuController {
	private final RoleMenuService service;

	public RoleMenuController(RoleMenuService service) {
		this.service = service;
	}

	@GetMapping("/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> findById(@PathVariable Long menuId, @PathVariable Long roleId) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(new RoleMenuPkey(menuId, roleId))));
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
	public ResponseEntity<?> create(@Valid @RequestBody RoleMenu roleMenu) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		RoleMenuResponse roleMenuCreated = service.create(roleMenu);
		String uri = "/v1/role-menu".concat("/menu-id/").concat(roleMenuCreated.getMenu().getId().toString()).concat("/role-id/").concat(roleMenuCreated.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> patch(@PathVariable Long menuId, @PathVariable Long roleId, @Valid @RequestBody RoleMenu roleMenu) {
		service.update(new RoleMenuPkey(menuId, roleId), roleMenu);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> put(@PathVariable Long menuId, @PathVariable Long roleId, @Valid @RequestBody RoleMenu roleMenu) {
		RoleMenuResponse roleMenuPut = service.put(new RoleMenuPkey(menuId, roleId), roleMenu);
		if (roleMenuPut.getMenu().getId().equals(menuId) && roleMenuPut.getRole().getId().equals(roleId)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/role-menu".concat("/menu-id/").concat(roleMenuPut.getMenu().getId().toString()).concat("/role-id/").concat(roleMenuPut.getRole().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/menu-id/{menuId}/role-id/{roleId}")
	public ResponseEntity<?> delete(@PathVariable Long menuId, @PathVariable Long roleId) {
		service.delete(new RoleMenuPkey(menuId, roleId));
		return ResponseEntity.noContent().build();
	}

}
