package com.example.lab3.Services.Cat;

import com.example.lab3.Controller.CatParamFinder;
import com.example.lab3.DAL.dao.CatDao;
import com.example.lab3.DAL.dao.OwnerDao;
import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.OwnerDto;
import com.example.lab3.DAL.mappers.CatMapper;
import com.example.lab3.DAL.mappers.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatServiceImplementation implements CatService {
    private final CatDao catRepository;
    private final OwnerMapper ownerMapper;
    private final CatMapper catMapper;
    private final OwnerDao ownerRepository;

    @Override
    public List<CatDto> findCatsByParam(CatParamFinder param) {
        List<Cat> cats = null;
        if (param.getName() != null) {
            cats = catRepository.findByName(param.getName());
        }
        else if (param.getType() != null) {
            cats = catRepository.findByType(param.getType());
        }
        else if (param.getBirthday() != null) {
            cats = catRepository.findByBirthday(param.getBirthday());
        }
        else if (param.getColor() != null) {
            cats = catRepository.findByColor(param.getColor());
        }
        return cats.stream().map(catMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CatDto findCat(Integer id) {
        var cat = catRepository.getReferenceById(id);
        return catMapper.convertToDto(cat);
    }

    @Override
    public void saveCat(CatDto cat){
        var catBase = catMapper.convertToBase(cat);
        catRepository.save(catBase);
    }

    @Override
    public Boolean update(CatDto cat, Integer id){
        var catBase = catMapper.convertToBase(cat);
        if (catRepository.existsById(id)){
            catBase.setCatID(id);
            catRepository.save(catBase);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id){
        if (catRepository.existsById(id)){
            catRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CatDto findFriendById(Integer id){
        var cat = catRepository.getReferenceById(id);
        return catMapper.convertToDto(cat);
    }

    @Override
    public OwnerDto findOwnerById(Integer id){
        var owner = ownerRepository.getReferenceById(id);
        return ownerMapper.convertToDto(owner);
    }

    @Override
    public List<CatDto> findAll(){
        return catRepository.findAll().stream().map(catMapper::convertToDto).collect(Collectors.toList());
    }
}

