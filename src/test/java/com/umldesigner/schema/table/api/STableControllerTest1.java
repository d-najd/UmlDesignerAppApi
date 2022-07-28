package com.umldesigner.schema.table.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table.utils.table.STableTestUtil;
import com.umldesigner.submodules.UmlDesignerShared.schema.table.dto.STablePojo;

import lombok.extern.slf4j.Slf4j;


@WebAppConfiguration
@WebMvcTest(STableController.class)
//@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=none")
//@AutoConfigureMockMvc
public class STableControllerTest1 {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    STableRepository sTableRepository;


    @InjectMocks
    private STableController myController;


    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.myController).build();


        STable tableMock = STableTestUtil.createMockTableEntity();
        STable tableMock1 = STableTestUtil.createMockTableEntity();
        tableMock1.setItems(null);

        when(sTableRepository.save(tableMock1)).thenReturn(tableMock1);
        when(sTableRepository.save(tableMock)).thenReturn(tableMock);
    }

    @Test
    public void beforeTest() {
        assertThat(objectMapper).isNotNull();
    }

    @Test
    @DisplayName("Create Schema Table")
    public void createSTable() {

        STablePojo mock = STableTestUtil.createMockTablePojo();

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

}
