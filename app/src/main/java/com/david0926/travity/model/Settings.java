package com.david0926.travity.model;

public enum Settings {
    ALARM_EVENT("alarm_event"), ALARM_CHECKIN("alarm_checkin");

    private String name;

    Settings(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
