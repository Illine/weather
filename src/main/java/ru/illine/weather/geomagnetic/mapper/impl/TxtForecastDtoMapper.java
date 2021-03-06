package ru.illine.weather.geomagnetic.mapper.impl;

import ru.illine.weather.geomagnetic.mapper.AbstractDtoMapper;
import ru.illine.weather.geomagnetic.model.dto.ForecastDto;
import ru.illine.weather.geomagnetic.model.dto.TxtForecastDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class TxtForecastDtoMapper extends AbstractDtoMapper<ForecastDto, TxtForecastDto> {

    protected TxtForecastDtoMapper(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    protected void setupMapper() {
        modelMapper
                .createTypeMap(destinationClass, sourceClass)
                .setPostConverter(toSourceConverter(new SourceConverter()));
    }

    public static class SourceConverter implements BiConsumer<TxtForecastDto, ForecastDto> {

        @Override
        public void accept(TxtForecastDto destination, ForecastDto source) {
            source.setForecastTime(destination.getInterval().getTimeInterval());
        }
    }
}