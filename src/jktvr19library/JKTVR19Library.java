/**
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
 * Подключение программы к базе данных без хранения массивов в программе.
 * 
 * 
 */
package jktvr19library;

/**
 *
 * @author Melnikov
 */
public class JKTVR19Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String flag = "base";
        if(args.length > 0){
            flag = args[0];
        }
        App app = new App(flag);
        app.run();
    }
    
}
