package gui.controller.init;

import java.awt.*;

public enum ColorsLoader {
    TC_BLUE(0x207CC1),
    TC_GREEN(0x799B3E),
    DARK_GRAY(0x4C4C4C),
    BLUE_TARO(0x0A2545),
    GREEN_EXCEL(0x55921D),
    BLUE_FOLDER(0x1EA0CF),
    BLUE_TEMPER(0x047495),
    GRAY_LIGHT(0xA3AFB1),
    ORANGE_SHOCK(0xFF6000);

    /*Color TC_BLUE = new Color(35, 124, 193);
    Color TC_GREEN = new Color(121, 155, 62);
    Color DARK_GRAY = new Color(76, 76, 76);
    Color BLUE_TARO = new Color(10, 37, 69);
    Color GREEN_EXCEL = new Color(85, 146, 29);
    Color BLUE_FOLDER = new Color(30, 160, 207);
    Color BLUE_TEMPER = new Color(4, 116, 149);
    Color GRAY_LIGHT = new Color(163, 175, 177);
    Color ORANGE_SHOCK = new Color(255, 96, 0);*/

    private int code;

    ColorsLoader(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}