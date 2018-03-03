package org.student.unit.task.service.health;

import org.student.unit.task.model.HealthCheckResponse;

@FunctionalInterface
public interface HealthCheckService
{
    HealthCheckResponse checkSystemsAvailability();
}
