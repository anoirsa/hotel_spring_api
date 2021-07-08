package com.hotelmangementapi.demo.model.enums;

import com.google.common.collect.Sets;

import java.util.Set;

public class PermissionStore {
    public static Set<Permissions> managerPermissions =
            Sets.newHashSet(Permissions.ADD_RESERVATION,Permissions.ADD_VISITOR,Permissions.VIEW_RESERVATIONS);
    public static Set<Permissions> bookerPermissions = Sets.newHashSet(Permissions.ADD_RESERVATION);
    public static Set<Permissions> adminPermissions = Sets.newHashSet(Permissions.ADD_ROOM,Permissions.ADD_RESERVATION,Permissions.ADD_VISITOR,Permissions.VIEW_RESERVATIONS,Permissions.EDIT_VISITOR_DETAILS);

}
