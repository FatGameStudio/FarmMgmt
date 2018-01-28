package no.lebegue.christophe.FarmMgmt;

import java.util.LinkedList;
import java.util.List;

abstract class ZoneService {

	public static List<Zone> getAllZones(){
		List<Zone> list = new LinkedList<Zone>();
		
		Zone zone = new Zone();
		zone.setId(1);
		zone.setNom("potager");
		list.add(zone);
		
		zone = new Zone();
		zone.setId(2);
		zone.setNom("foret");
		list.add(zone);
		
		
		return list;
		
	}
}
