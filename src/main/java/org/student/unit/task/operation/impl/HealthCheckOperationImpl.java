package org.student.unit.task.operation.impl;

import org.springframework.stereotype.Component;
import org.student.unit.task.model.SystemStatus;
import org.student.unit.task.operation.HealthCheckOperation;

@Component
public class HealthCheckOperationImpl implements HealthCheckOperation
{
    @Override
    public SystemStatus doHealthCheck()
    {
        SystemStatus status = new SystemStatus("FAKE");
        status.setAvailable(true);

        return status;
    }
}
