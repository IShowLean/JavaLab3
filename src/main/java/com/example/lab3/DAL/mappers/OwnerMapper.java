package com.example.lab3.DAL.mappers;

import com.example.lab3.DAL.dao.CatDao;
import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.Owner;
import com.example.lab3.DAL.entities.owner.OwnerDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private final CatDao catsRepo ;

    public OwnerDto convertToDto(Owner owner){
        return new OwnerDto(owner.getOwnerID(), owner.getName(), owner.getBirthday(), owner.getCats().stream().map(Cat::getCatID).toList());
    }

    public Owner convertToBase(OwnerDto owner){
        Class<CatDto> catDtoClass = CatDto.class;
        return new Owner(owner.getOwnerID(), owner.getName(), owner.getBirthday(), owner.getCats().stream().map(x -> catsRepo.findById(x).get()).toList());
    }
}