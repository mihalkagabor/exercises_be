package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.dto.*;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.BodyWeightEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final BodyWeightService bodyWeightService;
    private final  CurrentUserService currentUserService;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, BodyWeightService bodyWeightService, CurrentUserService currentUserService){
        this.userDataRepository=userDataRepository;
        this.bodyWeightService=bodyWeightService;
        this.currentUserService = currentUserService;
    }

    //TODO ellenőrizze, hogy van-e ilyen id-jű appuser a táblába, abban az esetben csak a többi adatot kell felülírni.
//public void createATotalUserData(UserDataCreationDto dto, BodyWeightCreationDto bodyWeightCreationDto){
//        AppUserEntity appUser = currentUserService.getCurrentAppUser();
//
//    UserDataEntity userData=new UserDataEntity(dto,appUser);
//    userData=userDataRepository.save(userData);
//    BodyWeightEntity bodyWeightEntity=new BodyWeightEntity(bodyWeightCreationDto);
//    bodyWeightEntity.setUserData(userData);
//    bodyWeightService.saveBodyWeightEntity(bodyWeightEntity);
//}

    public void createATotalUserData(UserDataCreationDto dto, BodyWeightCreationDto bodyWeightCreationDto) {
        //A tokenből kikérem az appuser azonosítót.
        AppUserEntity appUser =currentUserService.getCurrentAppUser();

        //Ellenőrzöm, hogy van-e már UserDataEntity, amihez ez az AppUserEntity hozzá an kötve.
        Optional<UserDataEntity> optionalUserData = userDataRepository.findByAppUser(appUser);

        UserDataEntity userData ;
        if(optionalUserData.isPresent()){
            //innen tudjuk, hogy melyik entitást írjuk felül (kikérjük, és ahhoz fogunk nyúlni.
        userData=optionalUserData.get();
        userData.setFirstName(dto.getFirst_name());
        userData.setLastName(dto.getLast_name());
        userData.setHeight(dto.getHeight());
        userData.setBirthYear(dto.getBirth_year());
        userData.setSex(dto.getSex());
        userData.setUserRole(dto.getUser_role());
        }else{
            userData = new UserDataEntity(dto,appUser);
        }
        userDataRepository.save(userData);

        // BodyWeightEntity mentése
        BodyWeightEntity bodyWeightEntity = new BodyWeightEntity(bodyWeightCreationDto);
        bodyWeightEntity.setUserData(userData);
        bodyWeightService.saveBodyWeightEntity(bodyWeightEntity);

    }




        public List<UserDataListerDto> listUserData(){
        return userDataRepository.findAll().stream()
                .sorted(Comparator.comparing(UserDataEntity::getUserDataId))
                .map(UserDataListerDto::new)
                .collect(Collectors.toList());
}

public UserDataListerDto myUserData() {
    UserDataEntity entity = currentUserService.getCurrentUserData();
    return new UserDataListerDto(entity);
}
}
