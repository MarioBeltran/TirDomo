package com.example.mario.tirdomo;

/**
 * Created by TMAX on 26/12/2017.
 */




public class ListSelectIcons {

    public String nameIconInput;
    public String iconOutput;

    public String nameColorInput;
    public String colorOutput;


public String listIcons(String nameIconInput){

        switch (nameIconInput){

            case "Power1":
                iconOutput = Integer.toString(R.drawable.ic_power_settings_new_black_24dp);
                break;

            case "Power2":
                iconOutput = Integer.toString(R.drawable.ic_power_settings_new_white_24dp);
                break;

            case "Mas1":
                iconOutput = Integer.toString(R.drawable.ic_add_black_24dp);
                break;

            case "Menos1":
                iconOutput = Integer.toString(R.drawable.ic_remove_black_24dp);
                break;

            case "Ch+1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_up_black_24dp);
                break;

            case "Ch-1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_down_black_24dp);
                break;

            case "CurUp1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_up_black_24dp);
                break;

            case "CurDo1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_down_black_24dp);
                break;

            case "CurLe1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_left_black_24dp);
                break;

            case "CurRi1":
                iconOutput = Integer.toString(R.drawable.ic_keyboard_arrow_right_black_24dp);
                break;

            case "Back1":
                iconOutput = Integer.toString(R.drawable.ic_reply_black_24dp);
                break;

            case "Input1":
                iconOutput = Integer.toString(R.drawable.ic_input_black_24dp);
                break;

            case "Home1":
                iconOutput = Integer.toString(R.drawable.ic_home_black_24dp);
                break;

            case "Mute1":
                iconOutput = Integer.toString(R.drawable.ic_volume_off_black_24dp);
                break;

            case "Star1":
                iconOutput = Integer.toString(R.drawable.ic_stars_black_24dp);
                break;

            case "Extra1":
                iconOutput = Integer.toString(R.drawable.ic_more_horiz_black_24dp);
                break;

            case "BackSpa1":
                iconOutput = Integer.toString(R.drawable.ic_backspace_black_24dp);
                break;

            case "Play1":
                iconOutput = Integer.toString(R.drawable.ic_play_arrow_black_24dp);
                break;

            case "Pause1":
                iconOutput = Integer.toString(R.drawable.ic_pause_black_24dp);
                break;

            case "Stop1":
                iconOutput = Integer.toString(R.drawable.ic_stop_black_24dp);
                break;

            case "Eject1":
                iconOutput = Integer.toString(R.drawable.ic_eject_black_24dp);
                break;

            case "SPre1":
                iconOutput = Integer.toString(R.drawable.ic_skip_previous_black_24dp);
                break;

            case "SNex1":
                iconOutput = Integer.toString(R.drawable.ic_skip_next_black_24dp);
                break;

            case "FFor1":
                iconOutput = Integer.toString(R.drawable.ic_fast_forward_black_24dp);
                break;

            case "FRew1":
                iconOutput = Integer.toString(R.drawable.ic_fast_rewind_black_24dp);
                break;

                default:
                    iconOutput = "0";
                    break;
        }
    return iconOutput;
}


    public String listColors(String nameColorInput){

    switch (nameColorInput){
        case "Red1":
            colorOutput =Integer.toString(R.color.Red1);
            break;

        case "Red2":
            colorOutput =Integer.toString(R.color.Red2);
            break;

        case "Blue1":
            colorOutput =Integer.toString(R.color.Blue1);
            break;

        case "Blue2":
            colorOutput =Integer.toString(R.color.colorPrimaryDark);
            break;

        case "Gray1":
            colorOutput =Integer.toString(R.color.Gray1);
            break;

        case "Gray2":
            colorOutput =Integer.toString(R.color.Gray2);
            break;

        case "Yellow1":
            colorOutput =Integer.toString(R.color.Yellow);
            break;

        case "Green1":
            colorOutput =Integer.toString(R.color.Green1);
            break;

        default:
                colorOutput =Integer.toString(R.color.White1);
                break;
        }

    return  colorOutput;
    }




}
