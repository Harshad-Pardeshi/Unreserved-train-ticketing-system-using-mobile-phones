package net.java.railway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "RLY_STN")
@NamedQueries({ @NamedQuery(name = "findAllStations", query = "SELECT stn FROM RailwayStation stn") })
@Inheritance(strategy = InheritanceType.JOINED)
public class RailwayStation {

	@Id
	@Column(name = "STN_CODE", length = 4)
	private String stationCode;

	@Column(name = "STN_NAME")
	private String stationName;

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
}
