package com.umldesigner.schema.table.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;

@Service
public interface STableService {

    public STablePojo findById(Integer id);

    public STablePojo getByUuid(String uuid);

    public STable findByUuid(String uuid);

    public List<STablePojo> getAll();

    /**
     * creating a table which can also include items
     * 
     * @apiNote the implementation can use "more elegant" way in the future
     * @see
     *      com.umldesigner.schema.table.service.SchemaTableService#createSchemaTable(com
     *      .umldesigner.schema.table.dto.SchemaTablePojo)
     */
    public STablePojo createSchemaTable(STablePojo sTablePojo);

    public STablePojo updateSchemaTable(String uuid, STablePojo sTablePojo);

    public void removeSchemaTable(String uuid);

}