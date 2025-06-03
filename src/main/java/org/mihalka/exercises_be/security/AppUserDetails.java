package org.mihalka.exercises_be.security;

import org.mihalka.exercises_be.model.entity.AppUserEntity;
import org.mihalka.exercises_be.model.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AppUserDetails  implements UserDetails {

    private final AppUserEntity user;

    public AppUserDetails(AppUserEntity entity){
        this.user=entity;
    }

    public AppUserEntity getUserEntity(){
        return user;
    }

    @Override
public String getPassword() {
        return user.getPassword_hash();
    }

    @Override
public String getUsername() {
        return user.getName() !=null? user.getName() : user.getEmail() ;    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getUserData() != null && user.getUserData().getUser_role() != null) {
            UserRole role = user.getUserData().getUser_role();
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
        }
        return Collections.emptyList(); // Ha nincs role, nem ad jogosultságot
    }

}
