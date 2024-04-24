package com.example.lab3.DAL.mappers;

import com.example.lab3.DAL.dao.CatDao;
import com.example.lab3.DAL.dao.OwnerDao;
import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.entities.cat.CatDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    private final OwnerDao ownersRepo;
    private final CatDao catsRepo;

    public CatDto convertToDto(Cat cat){
        return new CatDto(cat.getCatID(), cat.getName(), cat.getBirthday(),cat.getType(), cat.getColor(), cat.getOwner().getOwnerID(), cat.getFriends().stream().map(Cat::getCatID).toList());
    }
    public Cat convertToBase(CatDto cat){
        return new Cat(cat.getCatID(), cat.getName(), cat.getBirthday(), cat.getType(), cat.getColor(), ownersRepo.findById(cat.getOwner()).get(), cat.getFriends().stream().map(x -> catsRepo.findById(x).get()).toList());
    }
}