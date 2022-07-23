package com.umldesigner.schema.table.service;

import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.utils.item.SItemTestUtil;
import com.umldesigner.schema.table.utils.table.STableTestUtil;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
@Transactional
@Slf4j
public class STableServiceTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    STableService sTableService;

    @MockBean
    STableRepository sTableRepository;

    /**
     * 
     */
    @Test
    void createSchemaTableTest() {
        // creating mocks
        STable mock = STableTestUtil.createMockTableEntity();
        STable noItemsMock = STableTestUtil.createMockTableEntity();
        STablePojo mockPojoRequest = STableTestUtil.createMockTablePojo();

        // items prep
        SItem sItem1 = SItemTestUtil.createMockSItemEntity();
        SItem sItem2 = SItemTestUtil.createMockSItemEntity();
        SItemPojo sItemPojo1 = SItemTestUtil.createMockSItemPojo();
        SItemPojo sItemPojo2 = SItemTestUtil.createMockSItemPojo();
        sItem2.setPosition(1);
        log.debug("{}", sItemPojo1);

        noItemsMock.setItems(null);
        Collections.addAll(mock.getItems(), sItem1, sItem2);
        Collections.addAll(mockPojoRequest.getItems(), sItemPojo1, sItemPojo2);

        when(this.sTableRepository.save(noItemsMock)).thenReturn(noItemsMock);
        when(this.sTableRepository.save((mock))).thenReturn(mock);
        log.debug("{}", mockPojoRequest);

        STablePojo persistedPojo = sTableService.createSchemaTable(mockPojoRequest);

        log.debug("test {}", persistedPojo);
    }

    @Test
    void testFindByUuid() {

    }

    @Test
    void testGetAll() {

    }

    @Test
    void testGetByUuid() {

    }

    @Test
    void testRemoveSchemaTable() {

    }

    @Test
    void testUpdateSchemaTable() {

    }
}
