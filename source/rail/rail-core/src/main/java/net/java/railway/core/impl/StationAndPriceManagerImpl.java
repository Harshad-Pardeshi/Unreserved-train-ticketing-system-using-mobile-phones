package net.java.railway.core.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.java.railway.RailwayStation;
import net.java.railway.core.StationAndPriceManager;

@Stateless
public class StationAndPriceManagerImpl implements StationAndPriceManager {

	@PersistenceContext(unitName = "rail-core")
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<RailwayStation> findAllStations() {

		List<RailwayStation> stationList = null;

		Query query = em.createNamedQuery("findAllStations");

		stationList = query.getResultList();

		return stationList;
	}

	@Override
	public Double findJourneyPrice(String fromStation, String toStation) {

		Double price = 0d;

		Query query = em.createNamedQuery("findJourneyPrice");

		query.setParameter("fromStationCode", fromStation);
		query.setParameter("toStationCode", toStation);
		try {
			price = (Double) query.getSingleResult();
		} catch (javax.persistence.NoResultException nre) {
			System.out.println("ERROR: " + nre.getMessage());
			nre.printStackTrace();
		}
		return price;

	}

	@Override
	public RailwayStation findRailwayStationByCode(String code) {
		RailwayStation station = null;

		station = em.find(RailwayStation.class, code);

		return station;

	}
}
