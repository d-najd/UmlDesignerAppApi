package com.umldesigner.schema.table.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.mapper.STableMapper;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.utils.table.STableTestUtil;

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

    // WHY DO I NEED TO MOCK THIS?????????????????????????????
    @MockBean
    STableMapper sTableMapper;

    @MockBean
    STableRepository sTableRepository;

    @Test
    void createSchemaTableTest() {
        STable mock = STableTestUtil.createMockTableEntity();
        STablePojo mockPojoRequest = STableTestUtil.createMockTablePojo();

        // creating mocks
        STablePojo persistedPojo = createSchemaTable();

        assertEquals(2, persistedPojo.getItems().size());

        verify(this.sTableMapper).dtoToEntity(mockPojoRequest);
        verify(this.sTableMapper).entityToDto(mock);
    }

    /**
     * @apiNote while creating mocks we need all the different types because
     *          first the request is taken and and the items are put in temprary
     *          variable since we can't save items to table that doesn't exist, then
     *          we save the table, order them and set the table to them and them
     *          save the table with the items
     * @implNote find a better way to save the items at once so I can get rid of
     *           this spaghetti
     */
    STablePojo createSchemaTable() {
        STable mock = STableTestUtil.createMockTableEntity();
        STable mockOrderedItems = STableTestUtil.createMockTableEntity();
        STable noItemsMock = STableTestUtil.createMockTableEntity();
        STablePojo mockPojoRequest = STableTestUtil.createMockTablePojo();
        STablePojo mockOrderedItemsPojo = STableTestUtil.createMockTablePojo();

        // setting table and positions for the mocks
        noItemsMock.setItems(null);
        for (int i = 0; i < mockOrderedItems.getItems().size(); i++) {
            mockOrderedItems.getItems().get(i).setPosition(i);
            mockOrderedItems.getItems().get(i).setTable(noItemsMock);

            mockOrderedItemsPojo.getItems().get(i).setTable(mockOrderedItemsPojo);
        }

        when(this.sTableRepository.save(noItemsMock)).thenReturn(noItemsMock);
        when(this.sTableRepository.save((mock))).thenReturn(mock);
        when(this.sTableMapper.dtoToEntity(mockPojoRequest)).thenReturn(mock);
        when(this.sTableMapper.entityToDto(mock)).thenReturn(mockOrderedItemsPojo);

        STablePojo persistedPojo = sTableService.createSchemaTable(mockPojoRequest);

        return persistedPojo;
    }

    @Test
    void testRemoveSchemaTable() {
        STablePojo persistedTable = createSchemaTable();

        sTableRepository.findAll();

        sTableService.removeSchemaTable(persistedTable.getUuid());

        List<STable> test = sTableRepository.findAll();
        log.debug("size {}", test);
    }

    @Test
    void testUpdateSchemaTable() {

    }
}
