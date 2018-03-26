package net.java.railway.core;

import java.util.List;

import javax.ejb.Local;

import net.java.railway.RailwayStation;

@Local
public interface StationAndPriceManager {

	public List<RailwayStation> findAllStations();

	public Double findJourneyPrice(String fromStation, String toStation);

	public RailwayStation findRailwayStationByCode(String code);

}
