package com.hotelmangementapi.demo.model.enums;

public enum Permissions {
    ADD_VISITOR("user:add_visitor"),
    ADD_RESERVATION("user:add_reservation"),
    VIEW_RESERVATIONS("user:view_reservations"),
    ADD_ROOM("user:add_room"),
    REMOVE_ROOM("user:remove_room"),
    EDIT_ROOM("user:edit_room"),
    EDIT_VISITOR_DETAILS("user:edit_visitor_details"),
    ADD_RESERVATION_SPECIFIC("user:add_reservation_specific");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
