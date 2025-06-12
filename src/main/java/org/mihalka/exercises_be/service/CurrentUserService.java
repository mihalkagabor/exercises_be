package org.mihalka.exercises_be.service;

import jakarta.transaction.Transactional;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.entity.UserDataEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.mihalka.exercises_be.repository.UserDataRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CurrentUserService {
private final AppUserRepository appUserRepository;
private final UserDataRepository userDataRepository;


    public CurrentUserService(AppUserRepository appUserRepository, UserDataRepository userDataRepository) {
        this.appUserRepository = appUserRepository;
        this.userDataRepository = userDataRepository;
    }

    public String getCurrentAppUserIdentifier (){
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }


    public AppUserEntity getCurrentAppUser(){
        String currentUser=getCurrentAppUserIdentifier();
        return  appUserRepository.findByEmail(currentUser)
                .or(()-> appUserRepository.findByName(currentUser))
                .orElseThrow(()-> new UsernameNotFoundException("User not found with this identifier : " + currentUser));
    }

    public UserDataEntity getCurrentUserData(){
        String currentUser=getCurrentAppUserIdentifier();
        AppUserEntity appUser =getCurrentAppUser();
        return  userDataRepository.findByAppUser(appUser)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with this identifier:" +currentUser));
    }

}
