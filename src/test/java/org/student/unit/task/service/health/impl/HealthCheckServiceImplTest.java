package org.student.unit.task.service.health.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.model.SystemStatus;
import org.student.unit.task.operation.HealthCheckOperation;
import org.student.unit.task.operation.impl.HealthCheckOperationImpl;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckServiceImplTest {

    private HealthCheckServiceImpl healthCheckService;
    private static final String VERSION = "version";
    private HealthCheckResponse response;
    @Mock
    private HealthCheckOperation operation;



    @Before
    public void setUp() throws Exception {
        healthCheckService = new HealthCheckServiceImpl();
//        healthCheckService.healthCheckOperations = new ArrayList<>();

        ReflectionTestUtils.setField(healthCheckService, "applicationVersion", "version");
        ReflectionTestUtils.setField(healthCheckService, "healthCheckOperations", new ArrayList<HealthCheckOperation>());


    }

    @Test
    public void checkSystemsAvailabilityWhenNoOperations() throws Exception {

        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), empty());
        assertThat(response.getOverAllStatus(), is(HealthStatus.HEALTHY.getStatus()));

    }

    @Test
    public void checkSystemsAvailabilityWithOperation() throws Exception {

//        healthCheckService.healthCheckOperations.add(operation);

        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(1));
//        assertThat(response.getSystemStatus(), );
        assertThat(response.getOverAllStatus(), is(HealthStatus.HEALTHY.getStatus()));

    }

}