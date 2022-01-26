package AppPackage.factoryMethodSingleton;

import AppPackage.ProjectForm;

import javax.swing.*;
import java.awt.*;

public class LightThemeFrame implements ThemedFrame{
    private static LightThemeFrame lightThemeFrame = new LightThemeFrame();

    public static LightThemeFrame getLightThemeFrame() {
        return lightThemeFrame;
    }

    final Color colorOfBackground= new Color(255, 255, 255);
    final Color colorOfForeground= new Color(66, 88, 100);
    public void changeTheme( ProjectForm projectForm ){
        for(JComponent d : projectForm.returnAllThemeComponents()){
            d.setForeground(colorOfForeground);
            d.setBackground(colorOfBackground);
        }
    };
}
