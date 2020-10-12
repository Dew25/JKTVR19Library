/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.login;

import entity.Reader;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class LoginManager {
    private Scanner scanner = new Scanner(System.in);
    public Reader login(Reader[] readers) {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        if(readers == null || readers.length < 1){
            System.out.println("Нет пользователей. Зарегистрируйтесь");
            return null;
        }
        for (int i = 0; i < readers.length; i++) {
            Reader reader = readers[i];
            if(reader != null 
                    && reader.getLogin().equals(login)
                    && reader.getPassword().equals(password)){
                return reader;
            }
        }
        System.out.println("Нет такого пользователя или пароля");
        return null;
    }

}
