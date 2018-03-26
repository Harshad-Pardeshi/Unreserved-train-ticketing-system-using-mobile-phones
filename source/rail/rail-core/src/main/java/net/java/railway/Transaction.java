package net.java.railway;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import net.java.railway.Constants.TransactionType;

@Entity
@Table(name = "TRN")
@NamedQueries({
    @NamedQuery(name = "findTransactionsByAccountNumber", query = "SELECT trn FROM Transaction trn WHERE trn.account.id = :id")
})
@TableGenerator(name = "ENT_ID_GEN", table = "ENT_ID_GEN", pkColumnName = "ENT_NAM", pkColumnValue = "TRN", valueColumnName = "CURR_ID", initialValue = 1, allocationSize = 10)
public class Transaction {

    @Id
    @GeneratedValue(generator = "ENT_ID_GEN", strategy = GenerationType.TABLE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "FK_ACC_ID")
    private Account account;

    @Column(name = "TRN_TYP")
    private TransactionType transactionType;

    @Column(name = "AMT", precision = 2)
    private Double amount;

    @Column(name = "CRT_TIME")
    private Date createdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
