package org.student.unit.task.operation;

import org.student.unit.task.model.SystemStatus;

@FunctionalInterface
public interface HealthCheckOperation
{
    SystemStatus doHealthCheck();
}
