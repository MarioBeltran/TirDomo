package com.example.mario.tirdomo.utility;

/**
 * Created by TMAX on 29/11/2017.
 */

public class utility {

    //Constant table devices
    public static final String TABLE_DEVICES="devices";
    public static final String COLUMN_ID_DEVICE="id_Device";
    public static final String COLUMN_DEVICE_NAME="device_Name";
    public static final String COLUMN_IP_LAN="ip_Lan";
    public static final String COLUMN_IP_PORT="ip_Port";

    public static String CREATE_TABLE_DEVICES = "CREATE TABLE "+TABLE_DEVICES+"("+COLUMN_ID_DEVICE+" INTEGER, "+COLUMN_DEVICE_NAME+" TEXT, "+COLUMN_IP_LAN+" TEXT,"+COLUMN_IP_PORT+" TEXT)";

    //Constant table controlir
    public static final String TABLE_CONTROLSIR="controlsir";
    public static final String COLUMN_ID_CONTROLSIR="id_Controlsir";
    public static final String COLUMN_NAME_CONTROLSIR="name_Controlir";
    public static final String COLUMN_TEMPLATE_CONTROLSIR="template_Controlsir";

    public static String CREATE_TABLE_CONTROLSIR = "CREATE TABLE "+TABLE_CONTROLSIR+"("+COLUMN_ID_CONTROLSIR+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME_CONTROLSIR+" TEXT, "+COLUMN_TEMPLATE_CONTROLSIR+" TEXT)";

    //Constant table precharged (is like a default table)
    public static final String TABLE_PRECHARGED="pre";
    public static final String COLUMN_PRE_ID="pre_id";
    public static final String COLUMN_PRE_MODEL="pre_model";
    public static final String COLUMN_PRE_LOCATION_BUTTON="pre_location_button";
    public static final String COLUMN_PRE_BUTTON_NAME="pre_button_name";
    public static final String COLUMN_PRE_BUTTON_ICON="pre_button_icon";
    public static final String COLUMN_PRE_IRCODE="pre_ircode";
    public static final String COLUMN_PRE_COLORBACK="pre_colorback";

    public static String CREATE_TABLE_PRECHARGED = "CREATE TABLE "+TABLE_PRECHARGED+"("+COLUMN_PRE_ID+" INTEGER, "+COLUMN_PRE_MODEL+" TEXT, "+COLUMN_PRE_LOCATION_BUTTON+" TEXT,"+COLUMN_PRE_BUTTON_NAME+" TEXT, "+COLUMN_PRE_BUTTON_ICON+" TEXT, "+COLUMN_PRE_IRCODE+" TEXT, "+COLUMN_PRE_COLORBACK+" TEXT)";

    //Constant table code ir learned (this have connection with control ir)
    public static final String TABLE_LEARN="learn";
    public static final String COLUMN_LEARN_ID="learn_id";
    public static final String COLUMN_LEARN_LOCATION_BUTTON="learn_location_button";
    public static final String COLUMN_LEARN_BUTTON_NAME="learn_button_name";
    public static final String COLUMN_LEARN_BUTTON_ICON="learn_button_icon";
    public static final String COLUMN_LEARN_IRCODE="learn_ircode";
    public static final String COLUMN_LEARN_DEVICE_NUMBER="learn_device_number";
    public static final String COLUMN_LEARN_COLORBACK="learn_colorback";

    public static String CREATE_TABLE_LEARN = "CREATE TABLE "+TABLE_LEARN+"("+COLUMN_LEARN_ID+" INTEGER, "+COLUMN_LEARN_LOCATION_BUTTON+" TEXT, "+COLUMN_LEARN_BUTTON_NAME+" TEXT,"+COLUMN_LEARN_BUTTON_ICON+" TEXT, "+COLUMN_LEARN_IRCODE+" TEXT ,"+COLUMN_LEARN_DEVICE_NUMBER+" INTEGER, "+COLUMN_LEARN_COLORBACK+" TEXT)";

}

