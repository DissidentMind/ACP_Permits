package gui.controller.init;

import java.awt.*;

public enum ColorsLoader {
    TC_BLUE(new Color(35, 124, 193)),
    TC_GREEN(new Color(121, 155, 62)),
    DARK_GRAY(new Color(76, 76, 76)),
    BLUE_TARO(new Color(10, 37, 69)),
    GREEN_EXCEL(new Color(85, 146, 29)),
    BLUE_FOLDER(new Color(30, 160, 207)),
    BLUE_TEMPER(new Color(4, 116, 149)),
    GRAY_LIGHT(new Color(163, 175, 177)),
    ORANGE_SHOCK(new Color(255, 96, 0));

    private final Color code;
    ColorsLoader(Color code) {
        this.code = code;
    }
    public Color getCode() {
        return this.code;
    }

    /*public static void main(String[] args) {
        ColorsLoader cD = ColorsLoader.BLUE_TEMPER;
        System.out.println(cD.getCode());
    }*/
}
