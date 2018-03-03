package org.student.unit.task.model;

import java.util.List;

public class HealthCheckResponse
{
    private String overAllStatus;
    private List<SystemStatus> systemStatus;
    private String version;

    public List<SystemStatus> getSystemStatus()
    {
        return systemStatus;
    }

    public void setSystemStatus(List<SystemStatus> systemStatus)
    {
        this.systemStatus = systemStatus;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getOverAllStatus()
    {
        return overAllStatus;
    }

    public void setOverAllStatus(String overAllStatus)
    {
        this.overAllStatus = overAllStatus;
    }
}
