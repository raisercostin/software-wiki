/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.dcsi.internship;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author Yoga14.26.05.17
 */
public class User {

    @CsvBindByName
     private String username;

     @CsvBindByName
     private String email;

     @CsvBindByName
     private String firstname;
     
     @CsvBindByName
     private String lastname;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
     
   
}
