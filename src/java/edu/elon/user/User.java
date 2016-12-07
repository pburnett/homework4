/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elon.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 *
 * @author Dylan
 */
public class User implements Serializable {

    private String email;
    private String firstName;
    private String lastName;
    private String bookTitle;
    private String dueDate;

    public User() {
        email = "";
        firstName = "";
        lastName = "";
        bookTitle = "";
        dueDate = "";
    }

    public User(String firstName, String lastName, String email, String bookTitle, String dueDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bookTitle = bookTitle;
        this.dueDate = dueDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getBookTitle() {
      return bookTitle;
    }
    
    public void setBookTitle(String bookTitle) {
      this.bookTitle = bookTitle;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }
}
