package org.student.unit.task.calc;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.student.unit.task.model.BenefitPeriod;
import org.student.unit.task.operation.impl.HealthCheckOperationImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PromoCodePeriodDateCalculatorTest {

    PromoCodePeriodDateCalculator periodDateCalculator;

    @Before
    public void setUp() throws Exception {
        periodDateCalculator = new PromoCodePeriodDateCalculator();
    }
    @Test
    public void getLastDateTimeOfPeriodMonthJan() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-12-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodMonthDec() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-01-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-01-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodYear() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.YEAR, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-12-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodQuaterMonthLessThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-02-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-03-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodMonthLessThan7AndMoreThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-05-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-06-30").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodQuaterMonthLessThan10AndMoreThan7() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-08-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-09-30").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodQuaterEquals10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-12-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }

    @Test
    public void getLastDateTimeOfPeriodQuaterMoreThan10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDateTime dateTime = periodDateCalculator.getLastDateTimeOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(dateTime, is(new LocalDate("2018-12-31").toDateTimeAtStartOfDay().millisOfDay()
                .withMaximumValue().withZoneRetainFields(DateTimeZone.UTC).withZone(DateTimeZone.UTC).toLocalDateTime()));
    }
    //================
    @Test
    public void getLastDateOfPeriodJan() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-12-31")));
    }

    @Test
    public void getLastDateOfPeriodDec() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-01-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-01-31")));
    }

    @Test
    public void getLastDateOfPeriodYear() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.YEAR, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-12-31")));
    }

    @Test
    public void getLastDateOfPeriodQuaterMonthLessThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-02-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-03-31")));
    }

    @Test
    public void getLastDateOfPerioduaterMonthLessThan7AndMoreThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-05-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-06-30")));
    }

    @Test
    public void getLastDateOfPeriodQuaterMonthLessThan10AndMoreThan7() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-08-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-09-30")));
    }

    @Test
    public void getLastDateOfPeriodQuaterEquals10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-12-31")));
    }

    @Test
    public void getLastDateOfPeriodQuaterMoreThan10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDate date = periodDateCalculator.getLastDateOfPeriod(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-12-31")));
    }

    //==================
    @Test
    public void getRefreshDateJan() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2019-01-01")));
    }

    @Test
    public void getRefreshDateDec() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-01-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.MONTH, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-02-01")));
    }

    @Test
    public void getRefreshDateYear() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.YEAR, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2019-01-01")));
    }

    @Test
    public void getRefreshDateQuaterMonthMoreThan12() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-12-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2019-01-01")));
    }

    @Test
    public void getRefreshDateQuaterMonthLessThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-02-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-04-01")));
    }

    @Test
    public void getRefreshDateQuaterMonthLessThan7AndMoreThan4() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-05-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-07-01")));
    }

    @Test
    public void getRefreshDateQuaterMonthLessThan10AndMoreThan7() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-08-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2018-10-01")));
    }

    @Test
    public void getRefreshDateQuaterEquals10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2019-01-01")));
    }

    @Test
    public void getRefreshDateQuaterMoreThan10() throws Exception {
        DateTimeUtils.setCurrentMillisFixed(new DateTime("2018-10-15").getMillis());
        LocalDate date = periodDateCalculator.getRefreshDate(BenefitPeriod.QUARTER, DateTimeZone.UTC);
        assertThat(date, is(new LocalDate("2019-01-01")));
    }


}