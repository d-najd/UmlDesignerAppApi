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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table.utils.table.STableTestUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureMockMvc
@Slf4j
// I am aware that this is testing both Controller and Service and I am aware
// that there are weaknesses with this way like not knowing if a method inside
// the service has been called for sure

// NOTE I am creating multiple of the same object because if I pass them they
// might get modified on the way making most of the code useless
public class STableControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    STableService sTableService;

    @MockBean
    STableRepository sTableRepository;

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
        STable mock = STableTestUtil.createMockTableEntity();
        STable mockClone = STableTestUtil.createMockTableEntity();

        when(this.sTableRepository.findByUuid(mockUuid)).thenReturn(Optional.of(mock));
        try {
            this.mockMvc.perform(get(Endpoints.TABLE + "/" + mockUuid))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.title", is(mockClone.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$.items", is(not(empty()))))
                    .andExpect(jsonPath("$.items", hasSize(mockClone.getItems().size())))
                    .andExpect(jsonPath("$.x").value(mockClone.getX()))
                    .andExpect(jsonPath("$.y").value(mockClone.getY()));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        verify(this.sTableRepository).findByUuid(mockUuid);
    }

    @Test
    @DisplayName("Get All Schema Table's")
    public void getAllTest() {

        List<STable> mockSTableList = new ArrayList<>();
        List<STable> mockSTableListClone = new ArrayList<>();

        STable mock1 = STableTestUtil.createMockTableEntity();
        STable mock2 = STableTestUtil.createMockTableEntity();
        STable mock1Clone = STableTestUtil.createMockTableEntity();
        STable mock2Clone = STableTestUtil.createMockTableEntity();

        mockSTableList.add(mock1);
        mockSTableList.add(mock2);
        mockSTableListClone.add(mock1Clone);
        mockSTableListClone.add(mock2Clone);

        when(this.sTableRepository.findAll()).thenReturn(mockSTableList);

        try {
            this.mockMvc.perform(get(Endpoints.TABLE))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].title", is(mock1Clone.getTitle())))
                    // checking for size n stuff will be useless if there are no items in the table
                    .andExpect(jsonPath("$[0].items", is(not(empty()))))
                    .andExpect(jsonPath("$[0].items", hasSize(mock1Clone.getItems().size())))
                    .andExpect(jsonPath("$[0].x").value(mock1Clone.getX()))
                    .andExpect(jsonPath("$[0].y").value(mock1Clone.getY()))
                    .andExpect(jsonPath("$[1].title", is(mock1Clone.getTitle())));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        verify(this.sTableRepository).findAll();
    }

    @Test
    @DisplayName("Create Schema Table")
    @Disabled
    /**
     * @apiNote we have a mock with no items becauase first we save the table
     *          without the items so we get the uuid of the table so
     *          that we can set a reference for each item to the table (uuid), after
     *          that we make a table with the items and save it again and
     *          now we have a table with items also need to find a better way of
     *          doing this in the future
     */
    public void createSTableTest() {
        // creating mocks
        STable mock = STableTestUtil.createMockTableEntity();
        STable noItemsMock = STableTestUtil.createMockTableEntity();
        STable mockClone = STableTestUtil.createMockTableEntity();
        STablePojo mockPojoRequest = STableTestUtil.createMockTablePojo();

        // setting correct values
        noItemsMock.setItems(null);
        mock.getItems().get(0).setPosition(0);
        mock.getItems().get(1).setPosition(1);
        mockClone.getItems().get(0).setPosition(0);
        mockClone.getItems().get(1).setPosition(1);

        when(this.sTableRepository.save(noItemsMock)).thenReturn(noItemsMock);
        when(this.sTableRepository.save((mock))).thenReturn(mock);

        try {
            String jsonBodyPayload = objectMapper.writer().writeValueAsString(mockPojoRequest);

            this.mockMvc.perform(post(Endpoints.TABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyPayload))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
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