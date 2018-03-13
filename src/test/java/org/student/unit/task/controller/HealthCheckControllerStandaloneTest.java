package org.student.unit.task.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.service.health.HealthCheckService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HealthCheckControllerStandaloneTest {

    private MockMvc mockMvc;
    @Mock
    private HealthCheckService service;
    @Mock
    private HealthCheckResponse response;
    @InjectMocks
    private HealthCheckController controller;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void checkSystemStatusResponseReturnWithOkStatus() throws Exception {

        when(service.checkSystemsAvailability()).thenReturn(response);
        when(response.getOverAllStatus()).thenReturn(HealthStatus.HEALTHY.getStatus());
        mockMvc.perform(get("/health"));
        ResponseEntity responseEntity = controller.checkSystemStatus();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void checkSystemStatusResponseReturnWith404() throws Exception {
        mockMvc.perform(get("/222"))
                .andExpect(status().is(404)).andReturn();
    }

    @Test
    public void checkSystemStatusResponseReturnWith500() throws Exception {
        when(service.checkSystemsAvailability()).thenReturn(response);
        when(response.getOverAllStatus()).thenReturn(HealthStatus.UNHEALTHY.getStatus());
        mockMvc.perform(get("/health"));
        ResponseEntity responseEntity = controller.checkSystemStatus();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}