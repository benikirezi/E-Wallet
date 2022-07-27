/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Dao.TransactionsDao;
import Dao.ClientDao;
import Dao.UserDao;
import Domain.Client;
import Domain.Transactions;
import Domain.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rugwiro
 */
@ManagedBean
@SessionScoped
public class AdModel {

    private User u = new User();
    private Client client = new Client();


    @PostConstruct
    public void init() {
       
    }

    public void createClient() {
        new ClientDao().save(client);
        u.setPassword(u.getPassword());
        u.setAccess("Client");
        u.setClient(client);

        new UserDao().save(u);

        u = new User();
        client = new Client();

        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Created!", null));
    }


    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

   
}
