package com.example.lab3.Services.Owner;

import com.example.lab3.Controller.CatParamFinder;
import com.example.lab3.Controller.OwnerParamFinder;
import com.example.lab3.DAL.dao.CatDao;
import com.example.lab3.DAL.dao.OwnerDao;
import com.example.lab3.DAL.entities.cat.Cat;
import com.example.lab3.DAL.entities.cat.CatDto;
import com.example.lab3.DAL.entities.owner.Owner;
import com.example.lab3.DAL.entities.owner.OwnerDto;
import com.example.lab3.DAL.mappers.CatMapper;
import com.example.lab3.DAL.mappers.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerServiceImplementation implements OwnerService {
    private final OwnerDao ownerRepository;
    private final OwnerMapper ownerMapper;
    private final CatMapper catMapper;
    private final CatDao catRepository;

    @Override
    public List<OwnerDto> findOwnersByParam(OwnerParamFinder param) {
        List<Owner> owners = null;
        if (param.getName() != null) {
            owners = ownerRepository.findByName(param.getName());
        }
        else if (param.getBirthday() != null) {
            owners = ownerRepository.findByBirthday(param.getBirthday());
        }
        return owners.stream().map(ownerMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public OwnerDto findOwner(Integer id){
        var owner = ownerRepository.getReferenceById(id);
        return ownerMapper.convertToDto(owner);
    }

    @Override
    public void saveOwner(OwnerDto owner){
        var ownerBase = ownerMapper.convertToBase(owner);
        ownerRepository.save(ownerBase);
    }

    @Override
    public Boolean deleteOwner(Integer id){
        if (ownerRepository.existsById(id)){
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateOwner(OwnerDto owner, Integer id){
        var ownerBase = ownerMapper.convertToBase(owner);
        if (ownerRepository.existsById(id)){
            ownerBase.setOwnerID(id);
            ownerRepository.save(ownerBase);
            return true;
        }
        return false;
    }

    @Override
    public List<OwnerDto> findAllOwners(){
        return ownerRepository.findAll().stream().map(ownerMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CatDto findCatById(Integer id){
        var cat = catRepository.getReferenceById(id);
        return catMapper.convertToDto(cat);
    }
}
