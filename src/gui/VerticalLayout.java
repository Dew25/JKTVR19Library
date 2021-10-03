/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 *
 * @author jvm
 */
public class VerticalLayout implements LayoutManager{
    private Dimension size = new Dimension();
    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {}

    @Override
    public Dimension preferredLayoutSize(Container container) {
        return CalculateBestSize(container);
    }

    @Override
    public Dimension minimumLayoutSize(Container container) {
        return CalculateBestSize(container);    
    }
    

    @Override
    public void layoutContainer(Container container) {
    // Список компонентов
        Component list[] = container.getComponents();
        int currentY = 5;
        for (int i = 0; i < list.length; i++) {
            // Определение предпочтительного размера компонента
            Dimension pref = list[i].getPreferredSize();
            // Размещение компонента на экране
            list[i].setBounds(5, currentY, pref.width, pref.height);
            // Учитываем промежуток в 5 пикселов
            currentY += 5;
            // Смещаем вертикальную позицию компонента
            currentY += pref.height;
        }
    }
    private Dimension CalculateBestSize(Container container){
        // Вычисление длины контейнера
        Component[] listComponents = container.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < listComponents.length; i++) {
            int width = listComponents[i].getWidth();
            // Поиск компонента с максимальной длиной
            if ( width > maxWidth ) 
                maxWidth = width;
        }
        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth + 5;
        // Вычисление высоты контейнера
        int height = 0;
        for (int i = 0; i < listComponents.length; i++) {
            height += 5;
            height += listComponents[i].getHeight();
        }
        size.height = height;
        return size;
    }
}
