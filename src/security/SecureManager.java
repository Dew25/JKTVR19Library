/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import entity.Reader;
import entity.User;
import entity.controllers.UserController;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import tools.creaters.ReaderManager;
import tools.creaters.UserManager;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author Melnikov
 */
public class SecureManager {
private Scanner scanner = new Scanner(System.in);
private UserManager userManager = new UserManager();
private ReaderManager readerManager = new ReaderManager();
//private FileManager storageManager = new FileManager();

public static enum role {READER, MANAGER};

    public User checkInLogin() {
        do{
            System.out.println("Ваш выбор: ");
            System.out.println("0. Закрыть программу");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход в систему");
            System.out.print("Введите номер задачи: ");
            String task = scanner.nextLine();
            switch (task) {
                case "0":
                    System.out.println("Пока! :)");
                    System.exit(0);
                    break;
                case "1":
                    User user = userManager.createUser();
                    UserController uc = new UserController();
                    uc.create(user);
                    break;
                case "2":
                    User checkInUser = userManager.getCheckInUser();
                    if(checkInUser == null) break;
                    return checkInUser;
                default:
                    System.out.println("Нет такой задачи.");;
            }
        }while(true);
    }
    
}
