package com.aoizora.converter;

import com.aoizora.api.dto.PetDTO;
import com.aoizora.dao.domain.Pet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

public class PetConverter implements Converter<Pet, PetDTO> {

    @Nullable
    @Override
    public PetDTO convert(Pet source) {
        PetDTO target = new PetDTO();
        target.setPetId(source.getId());
        target.setPetName(source.getName());
        target.setPetType(source.getPetType());

        return target;
    }
}
