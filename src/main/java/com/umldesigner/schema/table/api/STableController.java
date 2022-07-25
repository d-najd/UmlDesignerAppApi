package com.umldesigner.schema.table.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.service.STableService;

@RestController
@RequestMapping(Endpoints.TABLE)
public class STableController {

	@Autowired
	STableService sTableService;

	// this should be removed in the future for security reasons
	@GetMapping("/id/{id}")
	public STablePojo getById(@PathVariable(value = "id") Integer id) {
		return sTableService.findById(id);
	}

	@GetMapping("/{uuid}")
	public STablePojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		return sTableService.getByUuid(uuid);
	}

	@GetMapping
	public List<STablePojo> getAll() {
		return sTableService.getAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public STablePojo createSchemaTable(@RequestBody STablePojo requestSTablePojo) {
		return sTableService.createSchemaTable(requestSTablePojo);
		
	}

	@PutMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public STablePojo updateSchemaTable(@PathVariable(value = "uuid") String uuid,
			@RequestBody STablePojo requestSTablePojo) {
		return sTableService.updateSchemaTable(uuid, requestSTablePojo);
	}

	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeSchemaTable(@PathVariable(value = "uuid") String uuid) {
		sTableService.removeSchemaTable(uuid);
	}
}