package net.java.railway;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "TKT")
@TableGenerator(name = "ENT_ID_GEN", table = "ENT_ID_GEN", pkColumnName = "ENT_NAM", pkColumnValue = "TKT", valueColumnName = "CURR_ID", initialValue = 1, allocationSize = 10)
@NamedQueries({
        @NamedQuery(name = "findNoOfTravellers", query = "SELECT SUM(t.noofTickets) FROM Ticket t "
                + "WHERE (t.fromStation=:from AND t.toStation=:to) "
                + "AND ( :startDate <= t.createdTime OR t.createdTime <= :endDate )"),
        @NamedQuery(name = "findTotalRevenue", query = "SELECT SUM(t.amount) FROM Ticket t "
                + "WHERE t.fromStation=:from AND (:startDate <= t.createdTime OR t.createdTime <= :endDate)")
})
public class Ticket {
    @Id
    @GeneratedValue(generator = "ENT_ID_GEN", strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "FK_FROM_STN_ID")
    private RailwayStation fromStation;

    @OneToOne
    @JoinColumn(name = "FK_TO_STN_ID")
    private RailwayStation toStation;

    @Column(name = "AMT", precision = 2)
    private Double amount;

    @Column(name = "CRTD_TIME")
    private Date createdTime;

    @Column(name = "NO_OF_TKT")
    private Integer noofTickets;

    @OneToOne
    @JoinColumn(name = "FK_IN_SMS_ID")
    private SMS inSMS;

    @OneToOne
    @JoinColumn(name = "FK_OUT_SMS_ID")
    private SMS outSMS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RailwayStation getFromStation() {
        return fromStation;
    }

    public void setFromStation(RailwayStation fromStation) {
        this.fromStation = fromStation;
    }

    public RailwayStation getToStation() {
        return toStation;
    }

    public void setToStation(RailwayStation toStation) {
        this.toStation = toStation;
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

    public Integer getNoofTickets() {
        return noofTickets;
    }

    public void setNoofTickets(Integer noofTickets) {
        this.noofTickets = noofTickets;
    }

    public SMS getInSMS() {
        return inSMS;
    }

    public void setInSMS(SMS inSMS) {
        this.inSMS = inSMS;
    }

    public SMS getOutSMS() {
        return outSMS;
    }

    public void setOutSMS(SMS outSMS) {
        this.outSMS = outSMS;
    }

}
