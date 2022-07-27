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

/**
 *
 * @author user
 */
@Entity
public class Transactions {
    @Id
    @GeneratedValue
    private Integer Id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date TodayDate=new Date();
    private String Status, Receiver;
    @OneToOne
    private Client client;
    private Integer AmountSent, Transaction_Fees;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Date getTodayDate() {
        return TodayDate;
    }

    public void setTodayDate(Date TodayDate) {
        this.TodayDate = TodayDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

  

    public Integer getAmountSent() {
        return AmountSent;
    }

    public void setAmountSent(Integer AmountSent) {
        this.AmountSent = AmountSent;
    }

    public Integer getTransaction_Fees() {
        return Transaction_Fees;
    }

    public void setTransaction_Fees(Integer Transaction_Fees) {
        this.Transaction_Fees = Transaction_Fees;
    }

  
    

}
