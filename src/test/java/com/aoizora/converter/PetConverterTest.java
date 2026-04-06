package com.aoizora.converter;

import com.aoizora.api.dto.PetDTO;
import com.aoizora.dao.domain.Pet;
import com.aoizora.dao.domain.PetType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PetConverterTest {

    private static final Integer PET1_ID = 1;
    private static final String PET1_NAME = "Vasya";
    private static final PetType PET1_TYPE = PetType.CAT;

    private static final Integer PET2_ID = 2;
    private static final String PET2_NAME = "Котик";
    private static final PetType PET2_TYPE = PetType.CAT;

    @ParameterizedTest
    @MethodSource("convertMethodSource")
    void convert(Integer petId, String petName, PetType petType) {
        PetConverter converter = new PetConverter();

        Pet source = new Pet();
        source.setId(petId);
        source.setName(petName);
        source.setPetType(petType);

        var expected = new PetDTO(
                petId,
                petName,
                petType);

        var actual = converter.convert(source);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> convertMethodSource() {
        return Stream.of(
                arguments(PET1_ID, PET1_NAME, PET1_TYPE),
                arguments(PET2_ID, PET2_NAME, PET2_TYPE));
    }
}