package org.student.unit.task.model;

import java.util.Arrays;

public enum BenefitPeriod
{
    MONTH("MONTHLY"), QUARTER("QUARTERLY"), YEAR("YEARLY"), BIYEAR("BI-YEARLY");

    private String value;

    BenefitPeriod(String value)
    {
        this.value = value;
    }

    public static BenefitPeriod of(String value)
    {
        return Arrays.stream(BenefitPeriod.values())
                .filter(period -> period.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No BenefitPeriod specified for this string"));
    }

    public String getValue()
    {
        return value;
    }
}

