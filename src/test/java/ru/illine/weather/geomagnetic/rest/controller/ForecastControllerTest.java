package ru.illine.weather.geomagnetic.rest.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import ru.illine.weather.geomagnetic.exception.NotFoundException;
import ru.illine.weather.geomagnetic.exception.ParseException;
import ru.illine.weather.geomagnetic.model.dto.MobileForecastResponse;
import ru.illine.weather.geomagnetic.rest.presenter.ForecastPresenter;
import ru.illine.weather.geomagnetic.test.helper.generator.DtoGeneratorHelper;
import ru.illine.weather.geomagnetic.test.tag.SpringIntegrationTest;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringIntegrationTest
@DisplayName("ForecastController Spring Integration Test")
class ForecastControllerTest extends AbstractControllerTest {

    private static final String URI_GET_DIURNAL = "/forecasts/diurnal";
    private static final String URI_GET_CURRENT = "/forecasts/current";
    private static final String URI_GET_THREE_DAY = "/forecasts/three-day";

    @Mock
    private ForecastPresenter forecastPresenterMock;

    @Autowired
    private ForecastController controller;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(controller, "forecastPresenter", forecastPresenterMock);
    }

    @AfterEach
    void tearDown() {
        reset(forecastPresenterMock);
    }

    //  -----------------------   successful tests   -------------------------

    @Test
    @DisplayName("getDiurnal(): returns 200 and a success body")
    void successfulGetDiurnal() {
        when(forecastPresenterMock.getDiurnal()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getDiurnal();
    }

    @Test
    @DisplayName("getDiurnal(): returns 200 when a security is disabled")
    void successfulGetDiurnalSecurityDisabled() {
        apiKeyProperties.setEnabled(false);
        when(forecastPresenterMock.getDiurnal()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, null, URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getDiurnal();
        apiKeyProperties.setEnabled(true);
    }

    @Test
    @DisplayName("getCurrent(): returns 200 and a success body")
    void successfulGetCurrent() {
        when(forecastPresenterMock.getCurrent()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getCurrent();
    }

    @Test
    @DisplayName("getCurrent(): returns 200 when a security is disabled")
    void successfulGetCurrentSecurityDisabled() {
        apiKeyProperties.setEnabled(false);
        when(forecastPresenterMock.getCurrent()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, null, URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getCurrent();
        apiKeyProperties.setEnabled(true);
    }

    @Test
    @DisplayName("getThreeDay(): returns 200 and a success body")
    void successfulThreeDays() {
        when(forecastPresenterMock.getThreeDays()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getThreeDays();
    }

    @Test
    @DisplayName("getThreeDay(): returns 200 when a security is disabled")
    void successfulThreeDaysSecurityDisabled() {
        apiKeyProperties.setEnabled(false);
        when(forecastPresenterMock.getThreeDays()).thenReturn(DtoGeneratorHelper.generateMobileForecastResponse());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, null, URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.OK);
        verify(forecastPresenterMock).getThreeDays();
        apiKeyProperties.setEnabled(true);
    }

    //  -----------------------   fail tests   -------------------------

    @Test
    @DisplayName("getDiurnal(): returns 404 when a forecast not found")
    void failGetDiurnalNotFound() {
        when(forecastPresenterMock.getDiurnal()).thenThrow(NotFoundException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.NOT_FOUND);
        verify(forecastPresenterMock).getDiurnal();
    }

    @Test
    @DisplayName("getDiurnal(): returns 500 when ParseException is thrown")
    void failGetDiurnalParseInternalServerError() {
        when(forecastPresenterMock.getDiurnal()).thenThrow(ParseException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        verify(forecastPresenterMock).getDiurnal();
    }

    @Test
    @DisplayName("getDiurnal(): returns 500 when an any unknown exception is thrown")
    void failGetDiurnalInternalServerError() {
        when(forecastPresenterMock.getDiurnal()).thenThrow(RuntimeException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        verify(forecastPresenterMock).getDiurnal();
    }

    @Test
    @DisplayName("getDiurnal(): returns 403 when a request isn't authorized")
    void failGetDiurnalForbidden() {
        var headers = new HttpHeaders();
        headers.set(apiKeyProperties.getHeaderName(), UUID.randomUUID().toString());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, headers, URI_GET_DIURNAL));
        assertCall().accept(actual, HttpStatus.FORBIDDEN);
        verify(forecastPresenterMock, never()).getDiurnal();
    }

    @Test
    @DisplayName("getCurrent(): returns 404 when a forecast not found")
    void failGetCurrentNotFound() {
        when(forecastPresenterMock.getCurrent()).thenThrow(NotFoundException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.NOT_FOUND);
        verify(forecastPresenterMock).getCurrent();
    }

    @Test
    @DisplayName("getCurrent(): returns 500 when ParseException is thrown")
    void failGetCurrentParseInternalServerError() {
        when(forecastPresenterMock.getCurrent()).thenThrow(ParseException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        verify(forecastPresenterMock).getCurrent();
    }

    @Test
    @DisplayName("getCurrent(): returns 500 when an any unknown exception is thrown")
    void failGetCurrentInternalServerError() {
        when(forecastPresenterMock.getCurrent()).thenThrow(RuntimeException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        verify(forecastPresenterMock).getCurrent();
    }

    @Test
    @DisplayName("getCurrent(): returns 403 when a request isn't authorized")
    void failGetCurrentForbidden() {
        var headers = new HttpHeaders();
        headers.set(apiKeyProperties.getHeaderName(), UUID.randomUUID().toString());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, headers, URI_GET_CURRENT));
        assertCall().accept(actual, HttpStatus.FORBIDDEN);
        verify(forecastPresenterMock, never()).getCurrent();
    }

    @Test
    @DisplayName("getThreeDay(): returns 404 when a forecast not found")
    void failGetThreeDaysNotFound() {
        when(forecastPresenterMock.getThreeDays()).thenThrow(NotFoundException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.NOT_FOUND);
        verify(forecastPresenterMock).getThreeDays();
    }

    @Test
    @DisplayName("getThreeDay(): returns 500 when ParseException is thrown")
    void failThreeDaysParseInternalServerError() {
        when(forecastPresenterMock.getThreeDays()).thenThrow(ParseException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        assertTrue(Objects.requireNonNull(actual.getBody()).getForecasts().isEmpty());
        verify(forecastPresenterMock).getThreeDays();
    }

    @Test
    @DisplayName("getThreeDay(): returns 500 when an any unknown exception is thrown")
    void failThreeDaysInternalServerError() {
        when(forecastPresenterMock.getThreeDays()).thenThrow(RuntimeException.class);
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, buildDefaultHeaders(), URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.INTERNAL_SERVER_ERROR);
        verify(forecastPresenterMock).getThreeDays();
    }

    @Test
    @DisplayName("getThreeDay(): returns 403 when a request isn't authorized")
    void failThreeDaysForbidden() {
        var headers = new HttpHeaders();
        headers.set(apiKeyProperties.getHeaderName(), UUID.randomUUID().toString());
        var actual = assertDoesNotThrow(() -> get(MobileForecastResponse.class, headers, URI_GET_THREE_DAY));
        assertCall().accept(actual, HttpStatus.FORBIDDEN);
        verify(forecastPresenterMock, never()).getThreeDays();
    }
}