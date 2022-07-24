package com.umldesigner.schema.table.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table.utils.table.STableTestUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
@Slf4j
// I am aware that this is testing both Controller and Service and I am aware
// that there are weaknesses with this way like not knowing if a method inside
// the service has been called for sure

public class STableControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    STableService sTableService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mockMvc).isNotNull();
        assertThat(objectMapper).isNotNull();
        assertThat(sTableService).isNotNull();
    }

    @Test
    @DisplayName("Get Schema Table by Uuid")
    public void getByUuidTest() {
        String mockUuid = "mockUUID";
        STablePojo mock = STableTestUtil.createMockTablePojo();

        when(this.sTableService.getByUuid(mockUuid)).thenReturn(mock);
        try {
            this.mockMvc.perform(get(Endpoints.TABLE + "/" + mockUuid))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title", is(mock.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$.items", is(not(empty()))))
                    .andExpect(jsonPath("$.items", hasSize(mock.getItems().size())))
                    .andExpect(jsonPath("$.x").value(mock.getX()))
                    .andExpect(jsonPath("$.y").value(mock.getY()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        verify(this.sTableService).getByUuid(mockUuid);
    }

    @Test
    @DisplayName("Get All Schema Table's")
    public void getAllTest() {
        List<STablePojo> mockSTableListPojo = new ArrayList<>();

        mockSTableListPojo.add(STableTestUtil.createMockTablePojo());
        mockSTableListPojo.add(STableTestUtil.createMockTablePojo());
        mockSTableListPojo.get(0).setTitle("Mock Title 0");

        when(this.sTableService.getAll()).thenReturn(mockSTableListPojo);

        try {
            this.mockMvc.perform(get(Endpoints.TABLE))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title", is(mockSTableListPojo.get(0).getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$[0].items", is(not(empty()))))
                    .andExpect(jsonPath("$[0].items", hasSize(mockSTableListPojo.get(0).getItems().size())))
                    .andExpect(jsonPath("$[0].x").value(mockSTableListPojo.get(0).getX()))
                    .andExpect(jsonPath("$[0].y").value(mockSTableListPojo.get(0).getY()))
                    .andExpect(jsonPath("$[1].title", is(mockSTableListPojo.get(1).getTitle())));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        verify(this.sTableService).getAll();
    }

    /*
     * @Test
     * public void updateBookTest_success() {
     * String mockUuid = "mockUUID";
     * STablePojo mock = STableTestUtil.createMockSTablePojo("mock title 1",
     * "mock description 1");
     * STablePojo updatedMock = STableTestUtil.createMockSTablePojo("mock title 1",
     * "mock description 1");
     * 
     * when(this.sTableService.updateBook(mockUuid, mock)).thenReturn(updatedMock);
     * 
     * try {
     * String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);
     * 
     * this.mockMvc.perform(put(Endpoints.BOOKS + mockUuid)
     * .contentType(MediaType.APPLICATION_JSON)
     * .content(jsonBodyPayload))
     * .andDo(print())
     * .andExpect(status().is2xxSuccessful())
     * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
     * .andExpect(jsonPath("$.title").value(updatedMock.getTitle()))
     * .andExpect(jsonPath("$.description").value(updatedMock.getDescription()));
     * } catch (Exception e) {
     * e.printStackTrace();
     * fail();
     * }
     * }
     */
}