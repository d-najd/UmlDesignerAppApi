package com.umldesigner.schema.table.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.utils.table.STableTestUtil;
import com.umldesigner.schema.table_item.domain.SItem;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
@Slf4j
public class STableRepositoryTest {
    @Autowired
    private STableRepository sTableRepository;

    @Test
    public void injectComponentsAreNotNull() {
        assertNotNull(sTableRepository);
    }

    @Test
    @DisplayName("Get All Schema Tables")
    public void getAllSTables() {
        STable mock = STableTestUtil.createMockTableEntity();
        sTableRepository.save(mock);

        List<STable> sTableList = sTableRepository.findAll();
        assertFalse(sTableList.isEmpty());
    }

    @Test
    @DisplayName("Create Schema Table")
    public void createSTable() {
        // create and verify
        STable mock = STableTestUtil.createMockTableEntity();
        mock = sTableRepository.save(mock);

        Optional<STable> persistedOptionalMock = sTableRepository.findByUuid(mock.getUuid());
        assertTrue(persistedOptionalMock.isPresent());
        STable persistedMock = persistedOptionalMock.get();

        assertEquals(persistedMock.getItems().size(), mock.getItems().size());
        assertEquals(persistedMock.getItems().size(), 2, "STable Mock should have 2 items by default");
        assertEquals(persistedMock.getItems(), mock.getItems());
        assertEquals(persistedMock.getUuid(), mock.getUuid());
        assertEquals(persistedMock.getTitle(), mock.getTitle());
        assertEquals(persistedMock.getX(), mock.getX());
        assertEquals(persistedMock.getY(), mock.getY());
    }

    @Test
    @DisplayName("Update Schema Table")
    public void updateSTable() {
        // create and verify
        STable mock = STableTestUtil.createMockTableEntity();
        mock = sTableRepository.save(mock);

        Optional<STable> persistedOptionalMock = sTableRepository.findByUuid(mock.getUuid());
        assertTrue(persistedOptionalMock.isPresent());
        STable persistedMock = persistedOptionalMock.get();

        assertEquals(persistedMock.getItems().size(), mock.getItems().size());
        assertEquals(persistedMock.getItems().size(), 2, "STable Mock should have 2 items by default");
        assertEquals(persistedMock.getItems(), mock.getItems());
        assertEquals(persistedMock.getUuid(), mock.getUuid());
        assertEquals(persistedMock.getTitle(), mock.getTitle());
        assertEquals(persistedMock.getX(), mock.getX());
        assertEquals(persistedMock.getY(), mock.getY());

        // update and verify
        // Note items shouldn't update
        STable mockForUpdate = persistedMock;
        List<SItem> itemsForUpdate = persistedMock.getItems();
        itemsForUpdate.get(0).setType("Updated Mock Type");
        itemsForUpdate.get(0).setPosition(10000);
        itemsForUpdate.add(new SItem());

        mockForUpdate.setItems(itemsForUpdate);
        mockForUpdate.setX(-214.42f);
        mockForUpdate.setY(573.12f);
        mockForUpdate.setTitle("Updated Mock Title");

        sTableRepository.saveAndFlush(mockForUpdate);

        Optional<STable> persistedUpdateOptionalMock = sTableRepository.findByUuid(mockForUpdate.getUuid());
        assertTrue(persistedUpdateOptionalMock.isPresent());

        STable persistedUpdateMock = persistedUpdateOptionalMock.get();

        assertEquals(persistedUpdateMock.getItems().size(), mock.getItems().size());
        assertEquals(3, persistedMock.getItems().size(), "STable Mock should have 3 items by default");
        assertEquals(persistedUpdateMock.getItems(), mock.getItems());
        assertEquals(persistedUpdateMock.getUuid(), mock.getUuid());
        assertEquals(persistedUpdateMock.getTitle(), mock.getTitle());
        assertEquals(persistedUpdateMock.getX(), mock.getX());
        assertEquals(persistedUpdateMock.getY(), mock.getY());
    }

    @Test
    @DisplayName("Remove Schema Table")
    public void removeSTable() {
        // create book and verify
        STable mock = STableTestUtil.createMockTableEntity();
        mock = sTableRepository.save(mock);

        Optional<STable> persistedOptionalMock = sTableRepository.findByUuid(mock.getUuid());
        assertTrue(persistedOptionalMock.isPresent());
        STable persistedMock = persistedOptionalMock.get();

        assertEquals(persistedMock.getItems().size(), mock.getItems().size());
        assertEquals(persistedMock.getItems().size(), 2, "STable Mock should have 2 items by default");
        assertEquals(persistedMock.getItems(), mock.getItems());
        assertEquals(persistedMock.getUuid(), mock.getUuid());
        assertEquals(persistedMock.getTitle(), mock.getTitle());
        assertEquals(persistedMock.getX(), mock.getX());
        assertEquals(persistedMock.getY(), mock.getY());

        // remove book
        sTableRepository.delete(persistedMock);
        Optional<STable> deletedOptionalMock = sTableRepository.findByUuid(mock.getUuid());
        assertTrue(deletedOptionalMock.isEmpty());
    }
}