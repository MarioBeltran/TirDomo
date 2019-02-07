package com.example.mario.tirdomo.entity;

/**
 * Created by TMAX on 29/11/2017.
 */

public class devices {

    private Integer id_Device;
    private String device_Name;
    private String ip_Lan;
    private String ip_port;

    public devices() {
        this.id_Device = id_Device;
        this.device_Name = device_Name;
        this.ip_Lan = ip_Lan;
        this.ip_port = ip_port;
    }

    public Integer getId_Device() {
        return id_Device;
    }

    public void setId_Device(Integer id_Device) {
        this.id_Device = id_Device;
    }

    public String getDevice_Name() {
        return device_Name;
    }

    public void setDevice_Name(String device_Name) {
        this.device_Name = device_Name;
    }

    public String getIp_Lan() {
        return ip_Lan;
    }

    public void setIp_Lan(String ip_Lan) {
        this.ip_Lan = ip_Lan;
    }

    public String getIp_port() {
        return ip_port;
    }

    public void setIp_port(String ip_port) {
        this.ip_port = ip_port;
    }
}


