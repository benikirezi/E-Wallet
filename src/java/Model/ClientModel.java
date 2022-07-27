/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Dao.ClientDao;
import Dao.TransactionsDao;
import Dao.UserDao;
import Domain.Client;
import Domain.Transactions;
import Domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rugwiro
 */
@ManagedBean
@SessionScoped
public class ClientModel {

    private Transactions transaction = new Transactions();
    private TransactionsDao transactionsDao = new TransactionsDao();
    private ClientDao clientDao = new ClientDao();
    List<Transactions> itt = new ArrayList<>();

    private User u = new User();
    private String unipassword = new String();
    private String id = new String();
    private Client loadClient = new Client();
    private List<Client> allClientsList = new ClientDao().findAll(Client.class);

    private List<Transactions> transactionsList = new TransactionsDao().view("from Transactions a WHERE a.client.Id='" + loadName2() + "'");
    private List<Client> clientList = new ClientDao().view("from Client a WHERE a.Id!='" + loadName2() + "'");

    String dropClient;
    Integer newBalance = 0;
    Integer k = 0;

    public void init() {
//        loadName();
        loadName2();
        transactionsList = new TransactionsDao().view("from Transactions a WHERE a.client.Id='" + loadName2() + "'");

    }

    public Integer loadName2() {
        User x = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session");
        loadClient = x.getClient();
        Integer IdNum = loadClient.getId();
        return IdNum;
    }

    public void sendMoney() throws Exception {

        allClientsList.forEach((us) -> {

            if (loadClient.getId().equals(us.getId())) {

                if (transaction.getAmountSent() > us.getAmount()) {
                    k = 1;

                } else {
                    User x = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session");
                    transaction.setClient(x.getClient());
                    transaction.setReceiver(dropClient);
                    transaction.setStatus("Sent");

                    if (transaction.getAmountSent() >= 1 && transaction.getAmountSent() < 10000) {

                        us.setAmount(us.getAmount() - transaction.getAmountSent());
                        transaction.setTransaction_Fees(0);

                        new ClientDao().update(us);
                    } else if (transaction.getAmountSent() >= 10000 && transaction.getAmountSent() < 100000) {
                        us.setAmount(us.getAmount() - (transaction.getAmountSent() + 200));
                        transaction.setTransaction_Fees(200);
                        new ClientDao().update(us);
                    } else {
                        us.setAmount(us.getAmount() - (transaction.getAmountSent() + 1000));
                        transaction.setTransaction_Fees(1000);
                        new ClientDao().update(us);
                    }

//        if(dropClient.matches(us.getFName()+" "+us.getLName())){
////        us.setAmount(us.getAmount()+transaction.getAmountSent());
////        new ClientDao().update(us);
//System.err.println("Hi"+ dropClient);
//        }
                }
            }

        });

        allClientsList.forEach((uss) -> {
            if ((uss.getFName() + " " + uss.getLName()).matches(dropClient)) {
                uss.setAmount(uss.getAmount() + transaction.getAmountSent());
                new ClientDao().update(uss);
            }
        });
        new TransactionsDao().save(transaction);
        transaction = new Transactions();
        transactionsList = new TransactionsDao().view("from Transactions a WHERE a.client.Id='" + loadName2() + "'");

        if (k == 0) {

            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction Created!", null));
        } else {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You have Insufficient Funds on Youy Account!", null));
        }
        k = 0;
    }

    public void addMoney() throws Exception {

        allClientsList.forEach((usr) -> {
            if ((usr.getFName() + " " + usr.getLName()).matches(loadClient.getFName() + " " + loadClient.getLName())) {
                usr.setAmount(usr.getAmount() + newBalance);
                new ClientDao().update(usr);
                System.out.println(newBalance);
            }
        });
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Blance Updated!", null));
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public String getUnipassword() {
        return unipassword;
    }

    public void setUnipassword(String unipassword) {
        this.unipassword = unipassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public TransactionsDao getTransactionsDao() {
        return transactionsDao;
    }

    public void setTransactionsDao(TransactionsDao transactionsDao) {
        this.transactionsDao = transactionsDao;
    }

    public List<Transactions> getItt() {
        return itt;
    }

    public void setItt(List<Transactions> itt) {
        this.itt = itt;
    }

    public Client getLoadClient() {
        return loadClient;
    }

    public void setLoadClient(Client loadClient) {
        this.loadClient = loadClient;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

    public List<Client> getAllClientsList() {
        return allClientsList;
    }

    public void setAllClientsList(List<Client> allClientsList) {
        this.allClientsList = allClientsList;
    }

    public String getDropClient() {
        return dropClient;
    }

    public void setDropClient(String dropClient) {
        this.dropClient = dropClient;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Integer getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Integer newBalance) {
        this.newBalance = newBalance;
    }

}
