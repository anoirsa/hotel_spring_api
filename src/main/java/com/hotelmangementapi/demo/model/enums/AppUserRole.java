package com.hotelmangementapi.demo.model.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum AppUserRole {
    MANAGER(PermissionStore.managerPermissions),
    BOOKER(PermissionStore.bookerPermissions),
    ADMIN(PermissionStore.adminPermissions);

    private final  Set<Permissions> permissions;
    public Set<Permissions> getPermissions() {
        return permissions;
    }

    AppUserRole(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return permissions;
    }


}
