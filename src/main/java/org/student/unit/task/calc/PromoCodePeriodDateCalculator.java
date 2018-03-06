package org.student.unit.task.calc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.student.unit.task.model.BenefitPeriod;

@Component
public class PromoCodePeriodDateCalculator {
    private static final String INCORRECT_PERIOD_MSG = "Incorrect benefit period";


    /**
     * Calculates date and time for last day of specified period,
     * convert it to target timezone with retaining UTC zone
     *
     * @param benefitPeriod benefit period
     * @return date and time of period end
     */
    public LocalDateTime getLastDateTimeOfPeriod(BenefitPeriod benefitPeriod, DateTimeZone timeZone) {
        return getLastDateOfPeriod(benefitPeriod, timeZone).toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(timeZone).withZone(DateTimeZone.UTC).toLocalDateTime();
    }

    /**
     * Calculates date for last day of specified period
     *
     * @param benefitPeriod benefit period
     * @return date of last day in specified period
     */
    public LocalDate getLastDateOfPeriod(BenefitPeriod benefitPeriod, DateTimeZone timeZone) {
        return getRefreshDate(benefitPeriod, timeZone).minusDays(1);
    }

    public LocalDate getRefreshDate(BenefitPeriod benefitPeriod, DateTimeZone timeZone) {
        LocalDate refreshDate = DateTime.now().withZone(timeZone).toLocalDate();

        switch (benefitPeriod) {
            case MONTH:
                refreshDate = refreshDate.plusMonths(1).withDayOfMonth(1);
                break;
            case QUARTER:
                refreshDate = resolveRefreshDateOnQuarterlyRule(refreshDate);
                break;
            case YEAR:
                refreshDate = refreshDate.plusYears(1).withDayOfYear(1);
                break;
            case BIYEAR:
                refreshDate = resolveRefreshDateOnBiYearlyRule(refreshDate);
                break;
            default:
                throw new IllegalArgumentException(INCORRECT_PERIOD_MSG);
        }
        return refreshDate;
    }


    private LocalDate resolveRefreshDateOnBiYearlyRule(LocalDate refreshDate) {
        if (refreshDate.getMonthOfYear() < 7) {
            return refreshDate.withMonthOfYear(7).withDayOfMonth(1);
        }
        return refreshDate.plusYears(1).withDayOfYear(1);
    }

    private LocalDate resolveRefreshDateOnQuarterlyRule(LocalDate refreshDate) {
        int month = refreshDate.getMonthOfYear();

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Incorrect month of year");
        }

        if (month < 4) {
            return refreshDate.withMonthOfYear(4).withDayOfMonth(1);
        } else if (month < 7) {
            return refreshDate.withMonthOfYear(7).withDayOfMonth(1);
        } else if (month < 10) {
            return refreshDate.withMonthOfYear(10).withDayOfMonth(1);
        } else {
            return refreshDate.plusYears(1).withDayOfYear(1);
        }
    }
}
