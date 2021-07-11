package com.hotelmangementapi.demo.model.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.hotelmangementapi.demo.model.enums.Permissions.*;

public class PermissionStore {
    public static Set<Permissions> managerPermissions =
            Sets.newHashSet(ADD_RESERVATION, ADD_VISITOR, VIEW_RESERVATIONS,
                    ADD_RESERVATION_SPECIFIC);
    public static Set<Permissions> bookerPermissions = Sets.newHashSet(ADD_RESERVATION);

    public static Set<Permissions> adminPermissions = Sets.newHashSet(ADD_ROOM, ADD_RESERVATION,
            ADD_VISITOR, VIEW_RESERVATIONS, EDIT_VISITOR_DETAILS, ADD_RESERVATION_SPECIFIC, REMOVE_ROOM,EDIT_ROOM);

}
