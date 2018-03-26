package net.java.railway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRICE_MSTR")
@NamedQueries({
    @NamedQuery(name = "findJourneyPrice", query = "SELECT p FROM PriceMaster p"
            + " WHERE UPPER(p.fromStationCode.stationCode)=UPPER(:from)"
            + " AND UPPER(p.toStationCode.stationCode)=UPPER(:to)")
})
public class PriceMaster {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "FK_FROM_STN_ID")
    private RailwayStation fromStationCode;

    @OneToOne
    @JoinColumn(name = "FK_TO_STN_ID")
    private RailwayStation toStationCode;

    @Column(name = "PRICE")
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFromStationCode(RailwayStation fromStationCode) {
        this.fromStationCode = fromStationCode;
    }

    public RailwayStation getToStationCode() {
        return toStationCode;
    }

    public void setToStationCode(RailwayStation toStationCode) {
        this.toStationCode = toStationCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public RailwayStation getFromStationCode() {
        return fromStationCode;
    }
}
