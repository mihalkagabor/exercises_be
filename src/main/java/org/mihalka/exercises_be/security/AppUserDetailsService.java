package org.mihalka.exercises_be.security;

import lombok.RequiredArgsConstructor;
import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService  implements UserDetailsService {
private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        System.out.println("Trying to authenticate identifier: " + identifier);

        AppUserEntity user = userRepository.findByEmail(identifier)
                .or(() -> userRepository.findByName(identifier))
                .orElseThrow(() -> {
                    System.out.println("User not found with identifier: " + identifier);
                    return new UsernameNotFoundException("User not found with identifier: " + identifier);
                });

        System.out.println("Found user: " + user.getName() + ", password: " + user.getPasswordHash());

        return new AppUserDetails(user);
    }

}
