/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author Rugwiro
 */
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer Id;
    private String FName, LName, Email, Phone;
    @OneToOne(mappedBy = "client")
    private User user;
    private Integer Amount=1000;
    @Transient
    private String NewUserName,OldPass, NewPass, ConfNewPass;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNewUserName() {
        return NewUserName;
    }

    public void setNewUserName(String NewUserName) {
        this.NewUserName = NewUserName;
    }

    public String getOldPass() {
        return OldPass;
    }

    public void setOldPass(String OldPass) {
        this.OldPass = OldPass;
    }

    public String getNewPass() {
        return NewPass;
    }

    public void setNewPass(String NewPass) {
        this.NewPass = NewPass;
    }

    public String getConfNewPass() {
        return ConfNewPass;
    }

    public void setConfNewPass(String ConfNewPass) {
        this.ConfNewPass = ConfNewPass;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer Amount) {
        this.Amount = Amount;
    }


    
    
}
