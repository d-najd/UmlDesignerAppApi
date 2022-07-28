package com.umldesigner.schema.table.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.utils.table.STableTestUtil;
import com.umldesigner.submodules.UmlDesignerShared.schema.table.dto.STablePojo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
@Slf4j
// @AutoConfigureTestDatabase(replace = Replace.NONE)

// NOTE I am aware that I am technically testing both the service and the
// controller at the same time

public class STableControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    // @MockBean
    // STableService sTableService;

    @MockBean
    STableRepository sTableRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(sTableRepository).isNotNull();
    }

    @Test
    @DisplayName("Get Schema Table by Uuid")
    public void getByUuidTest() {
        String mockUuid = "mockUUID";
        STablePojo mock = STableTestUtil.createMockTablePojo();
        STable mock1 = STableTestUtil.createMockTableEntity();

        when(this.sTableRepository.findByUuid(mockUuid)).thenReturn(Optional.of(mock1));
        try {
            this.mockMvc.perform(get(Endpoints.TABLE + "/" + mockUuid))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title", is(mock.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$.items", is(not(empty()))))
                    .andExpect(jsonPath("$.items", hasSize(mock.getItems().size())))
                    .andExpect(jsonPath("$.x", is(closeTo(mock.getX(), 0.000001))))
                    .andExpect(jsonPath("$.y", is(closeTo(mock.getY(), 0.000001))));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test
    @DisplayName("Get All Schema Table's")
    public void getAllTest() {
        List<STable> mockSTableList = new ArrayList<>();

        mockSTableList.add(STableTestUtil.createMockTableEntity());
        mockSTableList.add(STableTestUtil.createMockTableEntity());
        mockSTableList.get(0).setTitle("Mock Title 0");

        when(this.sTableRepository.findAll()).thenReturn(mockSTableList);

        try {
            this.mockMvc.perform(get(Endpoints.TABLE))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title", is(mockSTableList.get(0).getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$[0].items", is(not(empty()))))
                    .andExpect(jsonPath("$[0].items", hasSize(mockSTableList.get(0).getItems().size())))
                    .andExpect(jsonPath("$[0].x", is(closeTo(mockSTableList.get(0).getX(), 0.000001))))
                    .andExpect(jsonPath("$[0].y", is(closeTo(mockSTableList.get(0).getY(), 0.000001))))
                    .andExpect(jsonPath("$[1].title", is(mockSTableList.get(1).getTitle())));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    // @transactional has also been tried
    @Test
    @DisplayName("Create Schema Table")
    @Transactional
    @Disabled
    public void createSTable() {
        STablePojo mock = STableTestUtil.createMockTablePojo();

        STable mockWithItems = STableTestUtil.createMockTableEntity();
        STable mockWithNoItems = STableTestUtil.createMockTableEntity();

        mockWithNoItems.setItems(null);

        when(sTableRepository.save(mockWithItems)).thenReturn(mockWithItems);
        when(sTableRepository.save(mockWithNoItems)).thenReturn(mockWithNoItems);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(post(Endpoints.TABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title", is(mock.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$.items", is(not(empty()))))
                    .andExpect(jsonPath("$.items", hasSize(mock.getItems().size())))
                    .andExpect(jsonPath("$.x", is(closeTo(mock.getX(), 0.000001))))
                    .andExpect(jsonPath("$.y", is(closeTo(mock.getY(), 0.000001))));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Update Schema Table")
    @Disabled
    public void updateSTable() {
        String mockUuid = "mockUUID";
        STablePojo mock = STableTestUtil.createMockTablePojo();
        STablePojo updatedMock = STableTestUtil.createMockTablePojo();

        // when(this.sTableService.updateSchemaTable(mockUuid,
        // mock)).thenReturn(updatedMock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);

            this.mockMvc.perform(put(Endpoints.TABLE + "/" + mockUuid)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title", is(mock.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$.items", is(not(empty()))))
                    .andExpect(jsonPath("$.items", hasSize(mock.getItems().size())))
                    .andExpect(jsonPath("$.x", is(closeTo(mock.getX(), 0.000001))))
                    .andExpect(jsonPath("$.y", is(closeTo(mock.getY(), 0.000001))));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}