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

import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table_item.dto.SItemPojo;
import com.umldesigner.schema.table_item.service.SItemService;

@RestController
@RequestMapping(Endpoints.ITEM) // Not sure how to make the item mapping selective so
public class SItemController {

	@Autowired
	SItemService sItemService;

	// @Autowired
	// SItemRepository sItemRepository;

	// should be removed in future for security reasons
	@GetMapping("/id/{id}")
	public SItemPojo getById(@PathVariable(value = "id") Integer id) {
		return sItemService.getById(id);
	}

	@GetMapping("/{uuid}")
	public SItemPojo getByUuid(@PathVariable(value = "uuid") String uuid) {
		return sItemService.getByUuid(uuid);
	}

	@GetMapping
	public List<SItemPojo> getAll() {
		return sItemService.getAll();
	}

	@GetMapping(Endpoints.TABLE_RAW + "/{tUuid}")
	public List<SItemPojo> getAllByTUuid(@PathVariable(value = "tUuid") String tUuid) {
		return sItemService.getAllByTableUuid(tUuid);
	}

	@PostMapping(Endpoints.TABLE_RAW + "/{tUuid}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public SItemPojo createSItem(@PathVariable(value = "tUuid") String tUuid,
			@RequestBody SItemPojo requestSItemPojo) {
		return sItemService.createSchemaItem(tUuid, requestSItemPojo);
	}

	@PostMapping(Endpoints.TABLE_RAW + "/{tUuid}/list")
	@ResponseStatus(value = HttpStatus.CREATED)
	public List<SItemPojo> createSItemList(@PathVariable(value = "tUuid") String tUuid,
			@RequestBody List<SItemPojo> requestSItemPojoList) {
		return sItemService.createSchemaItemList(tUuid, requestSItemPojoList);
	}

	@PutMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public SItemPojo updateSItem(@PathVariable(value = "uuid") String uuid,
			@RequestBody SItemPojo requestSItemPojo) {
		return sItemService.updateSchemaItem(uuid, requestSItemPojo);
	}

	@PutMapping(Endpoints.TABLE_RAW + "/{tUuid}" + Endpoints.ITEM_RAW + "1/{fUuid}" + Endpoints.ITEM_RAW + "2/{sUuid}")
	@ResponseStatus(value = HttpStatus.OK)
	public void swapSItems(
			@PathVariable(value = "tUuid") String tUuid,
			@PathVariable(value = "fUuid") String fUuid,
			@PathVariable(value = "sUuid") String sUuid) {
		sItemService.swapSchemaItems(tUuid, fUuid, sUuid);
	}

	@DeleteMapping("/{uuid}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeSItem(@PathVariable(value = "uuid") String uuid) {
		sItemService.removeSchemaItem(uuid);
	}
}