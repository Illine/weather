package ru.illine.weather.geomagnetic.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@ConfigurationProperties(prefix = "application.rest")
public class RestProperties {

    @NotNull
    @Min(1)
    private Integer maxThread;

    @NotNull
    @Min(1)
    private Integer maxRoute;

    @NotNull
    @Min(1000)
    @Max(90000)
    private Integer readTimeout;

    @NotNull
    @Min(1000)
    @Max(90000)
    private Integer connectionTimeout;

    @NotNull
    private Retry retry;

    @Validated
    @Getter
    @Setter
    public static class Retry {

        @NotNull
        @Min(50)
        @Max(1000 * 60)
        private Long delayInMs;

        @NotNull
        @Min(2)
        @Max(10)
        private Integer maxAttempts;
    }
}