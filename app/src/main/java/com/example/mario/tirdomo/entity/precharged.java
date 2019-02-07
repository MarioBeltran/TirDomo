package com.example.mario.tirdomo.entity;

/**
 * Created by TMAX on 11/12/2017.
 */

public class precharged {

    private Integer pre_id;
    private String pre_model;
    private String pre_location_button;
    private String pre_button_name;
    private String pre_button_icon;
    private String pre_irCode;
    private String pre_colorback;

   // public precharged(Integer pre_id, String pre_model, String pre_location_button, String pre_button_name, String pre_button_icon, String pre_irCode, String pre_colorback) {
   public precharged(){
        this.pre_id = pre_id;
        this.pre_model = pre_model;
        this.pre_location_button = pre_location_button;
        this.pre_button_name = pre_button_name;
        this.pre_button_icon = pre_button_icon;
        this.pre_irCode = pre_irCode;
        this.pre_colorback = pre_colorback;
    }

    public Integer getPre_id() {
        return pre_id;
    }

    public void setPre_id(Integer pre_id) {
        this.pre_id = pre_id;
    }

    public String getPre_model() {
        return pre_model;
    }

    public void setPre_model(String pre_model) {
        this.pre_model = pre_model;
    }

    public String getPre_location_button() {
        return pre_location_button;
    }

    public void setPre_location_button(String pre_location_button) {
        this.pre_location_button = pre_location_button;
    }

    public String getPre_button_name() {
        return pre_button_name;
    }

    public void setPre_button_name(String pre_button_name) {
        this.pre_button_name = pre_button_name;
    }

    public String getPre_button_icon() {
        return pre_button_icon;
    }

    public void setPre_button_icon(String pre_button_icon) {
        this.pre_button_icon = pre_button_icon;
    }

    public String getPre_irCode() {
        return pre_irCode;
    }

    public void setPre_irCode(String pre_irCode) {
        this.pre_irCode = pre_irCode;
    }

    public String getPre_colorback() {
        return pre_colorback;
    }

    public void setPre_colorback(String pre_colorback) {
        this.pre_colorback = pre_colorback;
    }
}
