package AppPackage;

import javax.swing.*;
import java.awt.*;

public class LightThemeFrame implements ThemedFrame{
    final Color colorOfBackground= new Color(255, 255, 255);
    final Color colorOfForeground= new Color(66, 88, 100);
    public void changeTheme( ProjectForm projectForm ){
        for(JComponent d : projectForm.returnAllThemeComponents()){
            d.setForeground(colorOfForeground);
            d.setBackground(colorOfBackground);
        }
    };
}
