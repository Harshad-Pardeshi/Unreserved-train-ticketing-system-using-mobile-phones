package net.java.railway;

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

import net.java.railway.Constants.ProcessingStatus;
import net.java.railway.Constants.SmsType;

@Entity
@Table(name = "SMS")
@TableGenerator(name = "ENT_ID_GEN", table = "ENT_ID_GEN", pkColumnName = "ENT_NAM", pkColumnValue = "SMS", valueColumnName = "CURR_ID", initialValue = 1, allocationSize = 10)
@NamedQueries({
        @NamedQuery(name = "findQueuedSMS", query = "SELECT s FROM SMS s WHERE processingStatus=:processingStatus"),
        @NamedQuery(name = "updateSMSStatus", query = "UPDATE SMS SET processingStatus=:processingStatus WHERE ID IN (:id)")
})
public class SMS {
    @Id
    @GeneratedValue(generator = "ENT_ID_GEN", strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_ACC_ID")
    private Account account;

    @Column(name = "MOBILE")
    private Long mobile;

    @Column(name = "BODY")
    private String body;

    @Column(name = "TYP")
    private SmsType type;

    @Column(name = "STATUS")
    private ProcessingStatus processingStatus;

    public SMS() {
    }

    public SMS(Long mobile, String body) {
        this.mobile = mobile;
        this.body = body;
    }

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

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SmsType getType() {
        return type;
    }

    public void setType(SmsType type) {
        this.type = type;
    }

    public ProcessingStatus getProcessingStatus() {
        return processingStatus;
    }

    public void setProcessingStatus(ProcessingStatus processingStatus) {
        this.processingStatus = processingStatus;
    }
}
