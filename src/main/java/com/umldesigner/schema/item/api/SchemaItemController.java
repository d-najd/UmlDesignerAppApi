package com.umldesigner.schema.item.api;

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

import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;
import com.umldesigner.schema.item.repository.SchemaItemRepository;
import com.umldesigner.schema.item.service.SchemaItemService;
import com.umldesigner.schema.table.domain.SchemaTable;

@RestController
@RequestMapping("/schema/item")
public class SchemaItemController {

    @Autowired
    SchemaItemService schemaItemService;

	@Autowired
	SchemaItemRepository schemaItemRepository;

	@GetMapping("/id/{id}")
	public SchemaItemPojo getById(@PathVariable (value = "id") Integer id){
		return schemaItemService.findById(id);
	}

	@GetMapping("/{uuid}")
	public SchemaItemPojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		SchemaItem item = new SchemaItem();
		item.setId(100);
		item.setPosition(0);
		SchemaTable schemaTable = new SchemaTable();
		schemaTable.setId(20);
		item.setType("int");
		item.setUuid("uuid");
		item.setValue("value");
		schemaItemRepository.save(item);
			return null;
		//return schemaItemService.getByUuid(uuid);
	}

	@GetMapping
	public List<SchemaItemPojo> getAll() {
		return schemaItemService.getAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public SchemaItemPojo createSchemaItem(@RequestBody SchemaItemPojo requestSchemaItemPojo) {
		return schemaItemService.createSchemaItem(requestSchemaItemPojo);
	}

	@PutMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public SchemaItemPojo updateSchemaItem(@PathVariable(value = "uuid") String uuid, @RequestBody SchemaItemPojo requestSchemaItemPojo) {
		return schemaItemService.updateSchemaItem(uuid, requestSchemaItemPojo);
	}
	
	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeSchemaItem(@PathVariable(value = "uuid") String uuid) {
		schemaItemService.removeSchemaItem(uuid);
	}
}