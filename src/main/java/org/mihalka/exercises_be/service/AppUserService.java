package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.AppUserCreationDto;
import org.mihalka.exercises_be.model.dto.AppUserListerDto;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final Validator validator;


    @Autowired
    public AppUserService(AppUserRepository appUserRepository, Validator validator){
        this.appUserRepository=appUserRepository;
        this.validator =validator;
    }


    // app user létrehozó metódus
    public AppUserEntity appUserCreation(AppUserCreationDto dto){
        List<String> errors =new ArrayList<>();

        List<String> passwordErrors = validator.PasswordValidate(dto.getPassword_hash());
        if(!passwordErrors.isEmpty()){
            errors.addAll(passwordErrors);
        }
        if(appUserRepository.findByEmail(dto.getEmail()).isPresent()){
                errors.add("Ezt az email címet már regisztrálták: "+dto.getEmail());
            }
            if(appUserRepository.findByName(dto.getName()).isPresent()){
                errors.add("Ezt a felhasználó nevet már regisztrálták: "+dto.getName());
            }

            if(!errors.isEmpty()){
                throw new IllegalArgumentException(String.join(" \n",errors));
            }

        //ez az egyeszerű appUser létrehozása, amit bekapunk a paraméterben Dto-t feltöltjük ide.
        AppUserEntity appUser = new AppUserEntity(dto);
        return appUserRepository.save(appUser);
    }

    public List<AppUserListerDto> listAppUsers(){
        //streaem gyűjtük ki az adatokat.
        //a repository.findAll metódusával kigyűjtjük az összes entitást a táblából.
        //.stream()-e elindítjuk a feldolgozást.
    return appUserRepository.findAll().stream()
            //sorba rendezi az entitásokból álló listát AppUser::getUser_id (user id alapján)
            .sorted(Comparator.comparing(AppUserEntity::getUser_id))
            //.map(el feltöltöm az AppUserLIsterDto-t )
            .map(AppUserListerDto::new)
            //Listába gyűjtöd a stream-ben létrehozott dto-kat.
            .collect(Collectors.toList());
//Régi alternatíva!
//        List<AppUserEntity> allUsers = appUserRepository.findAll();
//        allUsers.sort(Comparator.comparing(AppUserEntity::getUser_id));
//
//        List<AppUserListerDto> result = new ArrayList<>();
//        for (AppUserEntity entity : allUsers) {
//            AppUserListerDto dto = new AppUserListerDto(entity);
//            result.add(dto);
//        }
//        return result;
    }


}
