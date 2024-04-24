package com.example.lab3.Services.Cat;

import com.example.lab3.Controller.CatParamFinder;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.OwnerDto;

import java.util.List;

public interface CatService {
    CatDto findCat(Integer id);
    void saveCat(CatDto cat);
    Boolean update(CatDto cat, Integer id);
    Boolean delete(Integer id);
    CatDto findFriendById(Integer id);
    OwnerDto findOwnerById(Integer id);
    List<CatDto> findAll();
    List<CatDto> findCatsByParam(CatParamFinder param);
}
