package org.student.unit.task.model;

public enum HealthStatus
{
    HEALTHY("healthy"),

    UNHEALTHY("unhealthy");

    private String status;

    HealthStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
}
