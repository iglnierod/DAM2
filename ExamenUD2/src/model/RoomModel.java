/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author iglesias_nieto_rodrigo
 */
public class RoomModel {
    private String roomType;
    private boolean isSmoker;
    private boolean needWifi;

    public RoomModel(String roomType, boolean isSmoker, boolean needWifi) {
        this.roomType = roomType;
        this.isSmoker = isSmoker;
        this.needWifi = needWifi;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isIsSmoker() {
        return isSmoker;
    }

    public void setIsSmoker(boolean isSmoker) {
        this.isSmoker = isSmoker;
    }

    public boolean isNeedWifi() {
        return needWifi;
    }

    public void setNeedWifi(boolean needWifi) {
        this.needWifi = needWifi;
    }
    
    
}
