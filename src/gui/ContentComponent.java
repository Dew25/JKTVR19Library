/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jvm
 */
public class ContentComponent extends JPanel{

    public ContentComponent(LayoutManager layout) {
        // Добавим кнопки и текстовое поле в панель
        super.add(new JButton("Продукты"  ));
        super.add(new JButton("Галантерея"));
        super.add(new JTextField(20));
        String[] list = {"Один", "Два"};
        JComboBox cbList = new JComboBox(list);
        super.add(cbList);
    }

    
    
    
}
