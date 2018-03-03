package org.student.unit.task.model;

public class SystemStatus
{
    private String sourceSystem;

    private Boolean available;

    public SystemStatus(String sourceSystem)
    {
        setSourceSystem(sourceSystem);
    }

    public String getSourceSystem()
    {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem)
    {
        this.sourceSystem = sourceSystem;
    }

    public Boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(Boolean available)
    {
        this.available = available;
    }
}
