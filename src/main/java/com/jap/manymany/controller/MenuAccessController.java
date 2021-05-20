/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.MenuAccess;
import com.jap.manymany.db.entity.MenuAccessPkey;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.dto.response.MenuAccessResponse;
import com.jap.manymany.service.MenuAccessService;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/menu-access")
public class MenuAccessController {
	private final MenuAccessService service;

	public MenuAccessController(MenuAccessService service) {
		this.service = service;
	}

	@GetMapping("/menu-id/{menuId}")
	public ResponseEntity<?> findById(@PathVariable Long menuId) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(new MenuAccessPkey(menuId))));
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
	public ResponseEntity<?> create(@Valid @RequestBody MenuAccess menuAccess) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		MenuAccessResponse menuAccessCreated = service.create(menuAccess);
		String uri = "/v1/menu-access".concat("/menu-id/").concat(menuAccessCreated.getMenu().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/menu-id/{menuId}")
	public ResponseEntity<?> patch(@PathVariable Long menuId, @Valid @RequestBody MenuAccess menuAccess) {
		service.update(new MenuAccessPkey(menuId), menuAccess);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/menu-id/{menuId}")
	public ResponseEntity<?> put(@PathVariable Long menuId, @Valid @RequestBody MenuAccess menuAccess) {
		MenuAccessResponse menuAccessPut = service.put(new MenuAccessPkey(menuId), menuAccess);
		if (menuAccessPut.getMenu().getId().equals(menuId)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/menu-access".concat("/menu-id/").concat(menuAccessPut.getMenu().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/menu-id/{menuId}")
	public ResponseEntity<?> delete(@PathVariable Long menuId) {
		service.delete(new MenuAccessPkey(menuId));
		return ResponseEntity.noContent().build();
	}

}
