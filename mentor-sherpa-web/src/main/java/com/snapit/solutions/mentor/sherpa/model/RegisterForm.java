/*
 * Property of SnapIT Solutions.
 */
package com.snapit.solutions.mentor.sherpa.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Sudheer.Parasker@SnapIT.Solutions
 */
public class RegisterForm {
    @NotEmpty
    @Email
    private String emailId;
    @Size(min=8,max=15)
    private String password;
    @Size(min=8,max=15)
    private String confirmPassword;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
    
}
