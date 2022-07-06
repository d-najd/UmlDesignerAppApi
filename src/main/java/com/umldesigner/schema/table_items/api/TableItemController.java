package com.umldesigner.schema.table_items.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.table_items.dto.TableItemPojo;
import com.umldesigner.schema.table_items.service.TableItemService;

@RestController
@RequestMapping("/tableItem") //using /table/item might cause problems
public class TableItemController {

    @Autowired
    TableItemService tableItemService;

    @GetMapping("/get")
    public TableItemPojo getByItemPojo(@RequestBody BaseMTMIdentity identity) {
        return tableItemService.findById(identity);
    }
}