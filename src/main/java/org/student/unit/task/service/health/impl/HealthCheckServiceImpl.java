package org.student.unit.task.service.health.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.student.unit.task.model.HealthCheckResponse;
import org.student.unit.task.model.HealthStatus;
import org.student.unit.task.model.SystemStatus;
import org.student.unit.task.operation.HealthCheckOperation;
import org.student.unit.task.service.health.HealthCheckService;

@Component
public class HealthCheckServiceImpl implements HealthCheckService
{
    @Autowired
    private List<HealthCheckOperation> operations;

    @Value("${info.app.version}")
    private String applicationVersion;

    @Override
    public HealthCheckResponse checkSystemsAvailability()
    {
        HealthCheckResponse response = new HealthCheckResponse();
        List<SystemStatus> systemStatusList = new ArrayList<>();
        for (HealthCheckOperation operation : operations) {
            SystemStatus doHealthCheck = operation.doHealthCheck();
            systemStatusList.add(doHealthCheck);
        }
        response.setSystemStatus(systemStatusList);
        response.setVersion(applicationVersion);
        response.setOverAllStatus(getOverAllAvailabilityStatus(systemStatusList));

        return response;
    }

    private String getOverAllAvailabilityStatus(List<SystemStatus> systemStatusList) {
        return isOverAllSystemsAvailable(systemStatusList) ?
                HealthStatus.HEALTHY.getStatus() :
                HealthStatus.UNHEALTHY.getStatus();
    }

    private boolean isOverAllSystemsAvailable(List<SystemStatus> systemStatusList) {
        for (SystemStatus systemStatus : systemStatusList) {
            if (systemStatus.isAvailable() == null || Boolean.FALSE.equals(systemStatus.isAvailable())) {
                return false;
            }
        }
        return true;
    }
}
