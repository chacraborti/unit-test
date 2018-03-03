package org.student.unit.task.operation.impl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.student.unit.task.model.SystemStatus;
import org.student.unit.task.operation.HealthCheckOperation;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


public class HealthCheckOperationImplTest {

    HealthCheckOperation operation;

    @Before
    public void setUp() throws Exception {
        operation = new HealthCheckOperationImpl();
    }

    @Test
    public void m1(){
        SystemStatus status = operation.doHealthCheck();
        assertThat(status, notNullValue());
        assertThat(status.getSourceSystem(), is("FAKE"));
        assertThat(status.isAvailable(), is(true));
    }


}