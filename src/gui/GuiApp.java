/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author jvm
 */
public class GuiApp extends JFrame{

    public GuiApp() {
        super.setTitle("MyLibrary");
        super. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(600, 400);
        JPanel contents = new JPanel(new VerticalLayout());
        MenuBarComponent menuBarComponent = new MenuBarComponent();
        ContentComponent contentComponent = new ContentComponent(new VerticalLayout());
        contents.add(menuBarComponent);
        contents.add(contentComponent);
        super.setContentPane(contents);
        super.setVisible(true);
    }
    
}
