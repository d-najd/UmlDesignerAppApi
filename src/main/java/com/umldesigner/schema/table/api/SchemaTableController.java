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

import com.umldesigner.schema.table.dto.SchemaTablePojo;
import com.umldesigner.schema.table.service.SchemaTableService;

@RestController
@RequestMapping("/schema/table")
public class SchemaTableController {

    @Autowired
    SchemaTableService schemaTableService;

	//this should be removed in the future for security reasons
	@GetMapping("/id/{id}")
	public SchemaTablePojo getById(@PathVariable (value = "id") Integer id){
		return schemaTableService.findById(id);
	}

	@GetMapping("/{uuid}")
	public SchemaTablePojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		return schemaTableService.getByUuid(uuid);
	}

	@GetMapping
	public List<SchemaTablePojo> getAll() {
		return schemaTableService.getAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public SchemaTablePojo createSchemaTable(@RequestBody SchemaTablePojo requestSchemaTablePojo) {
		return schemaTableService.createSchemaTable(requestSchemaTablePojo);
	}

	@PutMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public SchemaTablePojo updateSchemaTable(@PathVariable(value = "uuid") String uuid, @RequestBody SchemaTablePojo requestSchemaTablePojo) {
		return schemaTableService.updateSchemaTable(uuid, requestSchemaTablePojo);
	}
	
	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeSchemaTable(@PathVariable(value = "uuid") String uuid) {
		schemaTableService.removeSchemaTable(uuid);
	}
}