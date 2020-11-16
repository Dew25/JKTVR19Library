/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Melnikov
 */
@Entity
public class Book implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.author);
        hash = 97 * hash + Objects.hashCode(this.publishedYear);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.publishedYear, other.publishedYear)) {
            return false;
        }
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    
    
}
