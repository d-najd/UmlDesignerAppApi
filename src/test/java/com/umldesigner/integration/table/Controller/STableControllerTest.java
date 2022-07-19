package com.umldesigner.integration.table.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table_item.dto.SItemPojo;
import com.umldesigner.utils.item.SItemTestUtil;
import com.umldesigner.utils.table.STableTestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
public class STableControllerTest {

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
    }

    @Test
    @DisplayName("Get Schema Table by Uuid")
    public void getByUuidTest_success() {
        String mockUuid = "mockUUID";
        STablePojo mock1 = STableTestUtil.createMockTablePojo();

        when(this.sTableService.getByUuid(mockUuid)).thenReturn(mock1);

        try {
            this.mockMvc.perform(get(Endpoints.TABLE + "/" + mockUuid))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title").value(mock1.getTitle()))
                    .andExpect(jsonPath("$.items").value(mock1.getItems()))
                    .andExpect(jsonPath("$.x").value(mock1.getX()))
                    .andExpect(jsonPath("$.y").value(mock1.getY()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("Get All Schema Table's")
    public void getAllTest_success() {
        List<STablePojo> mockSTablePojoList = new ArrayList<>();
        List<SItemPojo> mockItemPojoList = SItemTestUtil.createMockSItemPojoList(null, null, "Mock Type", "Mock Value",
                2);

        STablePojo mock1 = STableTestUtil.createMockTablePojo(mockItemPojoList, "Mock Title 1", 0.15f, -5.2f);
        STablePojo mock2 = STableTestUtil.createMockTablePojo(mockItemPojoList, "Mock Title 2", -5.15f, 1.42f);

        mockSTablePojoList.add(mock1);
        mockSTablePojoList.add(mock2);

        when(this.sTableService.getAll()).thenReturn(mockSTablePojoList);

        try {
            this.mockMvc.perform(get(Endpoints.TABLE))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title").value(mock1.getTitle()))
                    .andExpect(jsonPath("$[0].items").value(mock1.getItems()))
                    .andExpect(jsonPath("$[0].x").value(mock1.getX()))
                    .andExpect(jsonPath("$[0].y").value(mock1.getY()))
                    .andExpect(jsonPath("$[1].title").value(mock2.getTitle()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    /*
     * @Test
     * public void createBookTest_success() {
     * STablePojo mock = STableTestUtil.createMockSTablePojo("mock title 1",
     * "mock description 1");
     * STablePojo createdMock = STableTestUtil.createMockSTablePojo("mock title 1",
     * "mock description 1");
     * 
     * when(this.sTableService.createBook(mock)).thenReturn(createdMock);
     * 
     * try {
     * String jsonBodyPayload = objectMapper.writer().writeValueAsString(mock);
     * 
     * this.mockMvc.perform(post(Endpoints.BOOKS)
     * .contentType(MediaType.APPLICATION_JSON)
     * .content(jsonBodyPayload))
     * .andDo(print())
     * .andExpect(status().is2xxSuccessful())
     * .andExpect(content().contentType(MediaType.APPLICATION_JSON))
     * .andExpect(jsonPath("$.title").value(createdMock.getTitle()))
     * .andExpect(jsonPath("$.description").value(createdMock.getDescription()));
     * } catch (Exception e) {
     * e.printStackTrace();
     * fail();
     * }
     * }
     * 
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