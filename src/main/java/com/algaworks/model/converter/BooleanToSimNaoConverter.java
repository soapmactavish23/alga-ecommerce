package com.algaworks.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToSimNaoConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return Boolean.TRUE.equals(attribute) ? "Sim" : "NAO";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "SIM".equals(dbData) ? Boolean.TRUE : Boolean.FALSE;
    }
}
