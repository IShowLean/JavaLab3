package com.example.lab3.Services.Owner;

import com.example.lab3.Controller.OwnerParamFinder;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto findOwner(Integer id);
    void saveOwner(OwnerDto owner);
    Boolean deleteOwner(Integer id);
    Boolean updateOwner(OwnerDto owner, Integer id);
    List<OwnerDto> findAllOwners();
    CatDto findCatById(Integer id);
    List<OwnerDto> findOwnersByParam(OwnerParamFinder param);
}
