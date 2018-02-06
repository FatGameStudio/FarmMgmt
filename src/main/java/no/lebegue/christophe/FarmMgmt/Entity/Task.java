package no.lebegue.christophe.FarmMgmt.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity // This tells Hibernate to make a table out of this class
public class Task {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private Date date;
    private String status;
	
    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
    
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    
    public String toString(){
    	String result = id + ":" + name + "-" + date.toString() + "-" + status ;
    	if (zone!=null) {
    		result += "- Zone:" + zone.toString();
    	}
    	if (activity!=null) {
    		result += "- Activity:" + activity.toString() ;
    	}
    	return result;
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	
	

}