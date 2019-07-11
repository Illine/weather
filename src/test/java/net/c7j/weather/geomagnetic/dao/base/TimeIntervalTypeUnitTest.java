package net.c7j.weather.geomagnetic.dao.base;

import net.c7j.weather.geomagnetic.test.helper.AssertionHelper;
import net.c7j.weather.geomagnetic.test.tag.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalTime;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@UnitTest
@DisplayName("TimeIntervalType Unit Test")
class TimeIntervalTypeUnitTest {


    private static final String INTERVAL_00_03 = "00-03UT";
    private static final String INTERVAL_03_06 = "03-06UT";
    private static final String INTERVAL_06_09 = "06-09UT";
    private static final String INTERVAL_09_12 = "09-12UT";
    private static final String INTERVAL_12_15 = "12-15UT";
    private static final String INTERVAL_15_18 = "15-18UT";
    private static final String INTERVAL_18_21 = "18-21UT";
    private static final String INTERVAL_21_00 = "21-00UT";

    //  -----------------------   successful tests   -------------------------

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_00_03'")
    void successful0003IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_00_03)).accept(TimeIntervalType.INTERVAL_00_03);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_03_06'")
    void successful0306IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_03_06)).accept(TimeIntervalType.INTERVAL_03_06);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_06_09'")
    void successful0609IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_06_09)).accept(TimeIntervalType.INTERVAL_06_09);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_09_12'")
    void successful0912IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_09_12)).accept(TimeIntervalType.INTERVAL_09_12);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_12_15'")
    void successful1215IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_12_15)).accept(TimeIntervalType.INTERVAL_12_15);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_15_18'")
    void successful1518IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_15_18)).accept(TimeIntervalType.INTERVAL_15_18);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_18_21'")
    void successful1821IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_18_21)).accept(TimeIntervalType.INTERVAL_18_21);
    }

    @Test
    @DisplayName("intervalOf(): a successful call returns an 'INTERVAL_21_00'")
    void successful2100IntervalOf() {
        AssertionHelper.assertCall(() -> TimeIntervalType.intervalOf(INTERVAL_21_00)).accept(TimeIntervalType.INTERVAL_21_00);
    }

    @Test
    @DisplayName("getInterval(): a successful call")
    void successfulGetInterval() {
        var expectedIntervals =
                Set.of(
                        INTERVAL_00_03, INTERVAL_03_06, INTERVAL_06_09, INTERVAL_09_12,
                        INTERVAL_12_15, INTERVAL_15_18, INTERVAL_18_21, INTERVAL_21_00
                );

        var actualIntervals =
                EnumSet.allOf(TimeIntervalType.class)
                        .stream()
                        .map(TimeIntervalType::getInterval)
                        .collect(Collectors.toSet());

        assertEquals(expectedIntervals, actualIntervals);
    }

    @Test
    @DisplayName("getTimeInterval(): a successful call")
    void successfulGetTimeInterval() {
        var expectedTimeIntervals =
                Set.of(
                        LocalTime.of(0, 0), LocalTime.of(3, 0), LocalTime.of(6, 0), LocalTime.of(9, 0),
                        LocalTime.of(12, 0), LocalTime.of(15, 0), LocalTime.of(18, 0), LocalTime.of(21, 0)
                );

        var actualTimeIntervals =
                EnumSet.allOf(TimeIntervalType.class)
                        .stream()
                        .map(TimeIntervalType::getTimeInterval)
                        .collect(Collectors.toSet());

        assertEquals(expectedTimeIntervals, actualTimeIntervals);
    }

    @DisplayName("toString(): a successful call returns a valid value")
    @ParameterizedTest
    @ValueSource(strings = {"00-03UT", "03-06UT", "06-09UT", "09-12UT", "12-15UT", "15-18UT", "18-21UT", "21-00UT"})
    void successfulToString(String arg) {
        var interval = TimeIntervalType.intervalOf(arg);

        var actual = interval.toString();
        assertEquals(arg, actual);
    }

    //  -----------------------   unsuccessful tests   -------------------------

    @Test
    @DisplayName("intervalOf(): unsuccessful an call returns an any string")
    void unsuccessfulAnyStringIntervalOf() {
        var interval = "any string";
        assertThrows(NoSuchElementException.class, () -> TimeIntervalType.intervalOf(interval));
    }

    @Test
    @DisplayName("intervalOf(): unsuccessful an call returns a incorrect interval")
    void unsuccessfulIncorrectIntervalOf() {
        var interval = "01-02UT";
        assertThrows(NoSuchElementException.class, () -> TimeIntervalType.intervalOf(interval));
    }
}