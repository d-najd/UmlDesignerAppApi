package com.umldesigner.schema.table_item.api;

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

import com.umldesigner.schema.table_item.dto.SchemaItemPojo;
import com.umldesigner.schema.table_item.repository.SchemaItemRepository;
import com.umldesigner.schema.table_item.service.SchemaItemService;

@RestController
@RequestMapping("/schema/table") // Not sure how to make the item mapping selective so
public class SchemaItemController {

	@Autowired
	SchemaItemService schemaItemService;

	@Autowired
	SchemaItemRepository schemaItemRepository;

	// should be removed in future for security reasons
	@GetMapping("/item/id/{id}")
	public SchemaItemPojo getById(@PathVariable(value = "id") Integer id) {
		return schemaItemService.findById(id);
	}

	@GetMapping("/item/{uuid}")
	public SchemaItemPojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		return schemaItemService.getByUuid(uuid);
	}

	@GetMapping("/item")
	public List<SchemaItemPojo> getAll() {
		return schemaItemService.getAll();
	}

	@GetMapping("{tUuid}/item")
	public List<SchemaItemPojo> getAllByTUuid(@PathVariable(value = "tUuid") String tUuid) {
		return schemaItemService.getAllByTableUuid(tUuid);
	}

	@PostMapping("/{tUuid}/item")
	@ResponseStatus(value = HttpStatus.CREATED)
	public SchemaItemPojo createSchemaItem(@PathVariable(value = "tUuid") String tUuid,
			@RequestBody SchemaItemPojo requestSchemaItemPojo) {
		return schemaItemService.createSchemaItem(tUuid, requestSchemaItemPojo);
	}

	@PostMapping("/{tUuid}/item/list")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<SchemaItemPojo> createSchemaItemList(@PathVariable(value = "tUuid") String tUuid,
			@RequestBody List<SchemaItemPojo> requestSchemaItemPojoList) {
		return schemaItemService.createSchemaItemList(tUuid, requestSchemaItemPojoList);
	}

	@PutMapping("/item/{uuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public SchemaItemPojo updateSchemaItem(@PathVariable(value = "uuid") String uuid,
			@RequestBody SchemaItemPojo requestSchemaItemPojo) {
		return schemaItemService.updateSchemaItem(uuid, requestSchemaItemPojo);
	}

	@DeleteMapping("/item/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeSchemaItem(@PathVariable(value = "uuid") String uuid) {
		schemaItemService.removeSchemaItem(uuid);
	}
}