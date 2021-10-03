/*
 * 
 * Ветка SaveToBase2
 * Подключение базы данных и сохранение сущностей в базу
 * Шаги:
 * 1. добавить бибилотеки поставщика персистентности
 * (EclopseLink (JPA-2.1)) и драйвер базы данных (C:\Program Files\NetBeanse 8.2\ide\modules\ext\mysql-connector-java-5.1.23-bin.jar
 * 2. Добавить аннотации @Entity, @Id, @GeneratedValue и др. к полям сущностных классов.
 * 3. Создание базы с помощью phpmyadmin
 * 4. Создание persistence.xml (файла подключения к базе)
 *      В свойствах подключения прописываем после знака вопроса следующее:
 *      ?useUnicode=true&characterEncoding=utf8
 * 5. В persictence.xml добавляем классы сущностей.
 * 6. Создаем файл - менеджер сохранения в базе.
 * 
 * Ветка saveDataBaseOnly
 * 1. Подключение программы к базе данных без хранения массивов в программе.
 * 2. Создание фасадов (AbstractFacade и наследников) для уменьшения повторяемости кода.
 * 3. Создание фабрики FacadeFactory, чтобы поставлять объекты по требованию из стороннего объекта.
 * 4. Создание одиночки (Singleton) для оптимизации создания соединения с базой данных.
 * 5. Рефакторинг с целью оптимизировать код: удаление пакета tools.sever; удаления ненужных импортов.
 *  
 */
package jktvr19library;


import gui.HorizontalMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class JKTVR19Library {
    
    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        menuBar.add(createMenu("Читатель"));
        menuBar.add(createMenu("Менеджер"));
        menuBar.add(createMenu("Администратор"));

        menuBar.setBorder(BorderFactory.createMatteBorder(0,0,0,1,
                                                          Color.BLACK));
        return menuBar;
    }
// used by createMenuBar
    public JMenu createMenu(String title) {
        JMenu m = new HorizontalMenu(title);
        m.add("Menu item #1 in " + title);
        m.add("Menu item #2 in " + title);
        m.add("Menu item #3 in " + title);

        JMenu submenu = new HorizontalMenu("Submenu");
        submenu.add("Submenu item #1");
        submenu.add("Submenu item #2");
        m.add(submenu);

        return m;
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JKTVR19Library demo = new JKTVR19Library();
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.WHITE); //contrasting bg
        contentPane.add(demo.createMenuBar(),
                        BorderLayout.LINE_START);

        //Display the window.
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
       // App app = new App();
       // GuiApp guiApp = new GuiApp();
//        try{
       //   app.run();
            
//        } finally {
//            ConnectSingleton connect = ConnectSingleton.getInstance();
//            EntityManager em = connect.getEntityManager();
//            EntityManagerFactory emf = connect.getEntityManagerFactory();
//            if(em != null) {
//                em.close();
//            }
//            if(emf != null){
//                emf.close();
//            }
//            
//        }
//    }
    
}
