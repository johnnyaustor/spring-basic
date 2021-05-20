/* Generate by Johnny Austor Builder at Thu May 20 15:00:02 ICT 2021 */
package com.jap.manymany.controller;

import com.jap.manymany.db.entity.OutletUser;
import com.jap.manymany.db.entity.OutletUserPkey;
import com.jap.manymany.dto.response.BasicResponse;
import com.jap.manymany.dto.response.DataResponse;
import com.jap.manymany.dto.response.OutletUserResponse;
import com.jap.manymany.service.OutletUserService;
import java.net.URI;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/outlet-user")
public class OutletUserController {
	private final OutletUserService service;

	public OutletUserController(OutletUserService service) {
		this.service = service;
	}

	@GetMapping("/outlet-id/{outletId}/user-id/{userId}")
	public ResponseEntity<?> findById(@PathVariable Long outletId, @PathVariable Long userId) {
		return ResponseEntity.ok(new DataResponse<>(service.findById(new OutletUserPkey(outletId, userId))));
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
	public ResponseEntity<?> create(@Valid @RequestBody OutletUser outletUser) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		OutletUserResponse outletUserCreated = service.create(outletUser);
		String uri = "/v1/outlet-user".concat("/outlet-id/").concat(outletUserCreated.getOutlet().getId().toString()).concat("/user-id/").concat(outletUserCreated.getUser().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@PatchMapping("/outlet-id/{outletId}/user-id/{userId}")
	public ResponseEntity<?> patch(@PathVariable Long outletId, @PathVariable Long userId, @Valid @RequestBody OutletUser outletUser) {
		service.update(new OutletUserPkey(outletId, userId), outletUser);
		HttpStatus httpStatus = HttpStatus.OK;
		return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
	}

	@PutMapping("/outlet-id/{outletId}/user-id/{userId}")
	public ResponseEntity<?> put(@PathVariable Long outletId, @PathVariable Long userId, @Valid @RequestBody OutletUser outletUser) {
		OutletUserResponse outletUserPut = service.put(new OutletUserPkey(outletId, userId), outletUser);
		if (outletUserPut.getOutlet().getId().equals(outletId) && outletUserPut.getUser().getId().equals(userId)) {
			HttpStatus httpStatus = HttpStatus.OK;
			return ResponseEntity.status(httpStatus).body(new BasicResponse(httpStatus, "updated"));
		}

		HttpStatus httpStatus = HttpStatus.CREATED;
		String uri = "/v1/outlet-user".concat("/outlet-id/").concat(outletUserPut.getOutlet().getId().toString()).concat("/user-id/").concat(outletUserPut.getUser().getId().toString());
		return ResponseEntity.created(URI.create(uri)).body(new BasicResponse(httpStatus, "created"));
	}

	@DeleteMapping("/outlet-id/{outletId}/user-id/{userId}")
	public ResponseEntity<?> delete(@PathVariable Long outletId, @PathVariable Long userId) {
		service.delete(new OutletUserPkey(outletId, userId));
		return ResponseEntity.noContent().build();
	}

}
