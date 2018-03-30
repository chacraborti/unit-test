package org.student.unit.task.service.health.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.model.SystemStatus;
import org.student.unit.task.operation.HealthCheckOperation;

import java.util.ArrayList;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckServiceImplTest {

    private HealthCheckServiceImpl healthCheckService;
    private static final String VERSION = "version";
    private HealthCheckResponse response;
    private HealthCheckOperation firstOperation;
    private HealthCheckOperation secondOperation;
    private ArrayList<HealthCheckOperation> value;



    @Before
    public void setUp() throws Exception {
        healthCheckService = new HealthCheckServiceImpl();
        ReflectionTestUtils.setField(healthCheckService, "applicationVersion", "version");
        value = new ArrayList<>();
        ReflectionTestUtils.setField(healthCheckService, "operations", value);
        firstOperation = mock(HealthCheckOperation.class);
        secondOperation = mock(HealthCheckOperation.class);
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
        value.add(firstOperation);
        SystemStatus status = new SystemStatus("");
        status.setAvailable(true);
        when(firstOperation.doHealthCheck()).thenReturn(status);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(1));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(true));
        assertThat(response.getOverAllStatus(), is(HealthStatus.HEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndStatusNull() throws Exception {
        value.add(firstOperation);
        SystemStatus status = new SystemStatus("");
        status.setAvailable(null);
        when(firstOperation.doHealthCheck()).thenReturn(status);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(1));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(Null.NULL));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndStatusFalse() throws Exception {
        value.add(firstOperation);
        SystemStatus status = new SystemStatus("");
        status.setAvailable(false);
        when(firstOperation.doHealthCheck()).thenReturn(status);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(1));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(false));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesTrueAndFalse() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(true);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(false);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);

        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(true));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(false));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesTrueAndNull() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(true);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(null);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);

        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(true));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(Null.NULL));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesTrueAndTrue() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(true);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(true);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(true));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(true));
        assertThat(response.getOverAllStatus(), is(HealthStatus.HEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesFalseAndFalse() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(false);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(false);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(false));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(false));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesFalseAndNull() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(false);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(null);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(false));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(Null.NULL));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesFalseAndTrue() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(false);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(true);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(false));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(true));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesNullAndFalse() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(null);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(false);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(Null.NULL));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(false));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesNullAndNull() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(null);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(null);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(Null.NULL));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(Null.NULL));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }

    @Test
    public void checkSystemsAvailabilityWithOperationAndTwoStatusesNullAndTrue() throws Exception {
        SystemStatus fistStatus = new SystemStatus("");
        fistStatus.setAvailable(null);
        SystemStatus secondStatus = new SystemStatus("");
        secondStatus.setAvailable(true);
        value.add(firstOperation);
        value.add(secondOperation);
        when(firstOperation.doHealthCheck()).thenReturn(fistStatus);
        when(secondOperation.doHealthCheck()).thenReturn(secondStatus);
        response = healthCheckService.checkSystemsAvailability();
        assertThat(response.getVersion(), is(VERSION));
        assertThat(response.getSystemStatus(), hasSize(2));
        assertThat(response.getSystemStatus().get(0).isAvailable(), is(Null.NULL));
        assertThat(response.getSystemStatus().get(1).isAvailable(), is(true));
        assertThat(response.getOverAllStatus(), is(HealthStatus.UNHEALTHY.getStatus()));
    }
}