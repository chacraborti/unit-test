package org.student.unit.task.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.service.health.HealthCheckService;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckControllerTest {

    private MockMvc mockMvc;

    @Mock
    private HealthCheckService service;
    private HealthCheckResponse response;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new HealthCheckController()).build();
       // verifyNoMoreInteractions(service);
    }

    @Test
    public void checkSystemStatusResponseReturnWithOkStatus() throws Exception {
        response = mock(HealthCheckResponse.class);
        when(service.checkSystemsAvailability()).thenReturn(response);
        when(response.getOverAllStatus()).thenReturn(HealthStatus.UNHEALTHY.getStatus());

        mockMvc.perform(get("/heath"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void checkSystemStatusResponseReturnWithError() throws Exception {
      response = mock(HealthCheckResponse.class);
        when(service.checkSystemsAvailability()).thenReturn(response);
        when(response.getOverAllStatus()).thenReturn(HealthStatus.UNHEALTHY.getStatus());

        mockMvc.perform(get("/heath"))
                .andExpect(status().isOk()).andReturn();
    }

}