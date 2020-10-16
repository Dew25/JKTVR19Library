/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Melnikov
 */
public class Book implements Serializable{
    private String name;
    private String author;
    private Integer publishedYear;

    public Book() {
    }

    public Book(String name, String author, Integer publishedYear) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
    }
    public Book(String name, String author, String publishedYear) {
        this.name = name;
        this.author = author;
        setPublishedYear(publishedYear);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
    public void setPublishedYear(String publishedYear) {
        try {
            int publishedYearInt = Integer.parseInt(publishedYear);
            this.publishedYear = publishedYearInt;
            System.out.println("Строка "+publishedYear+" успешно преобразована в число.");
        } catch (Exception e) {
            System.out.println("Введены не цифры. Поле не изменено");
        }
        
    }

    @Override
    public String toString() {
        return "Book{" 
                + "name=" + name 
                + ", author=" + author 
                + ", publishedYear=" + publishedYear 
                + '}';
    }
    
    
    
}
