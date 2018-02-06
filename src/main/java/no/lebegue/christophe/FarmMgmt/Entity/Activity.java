package no.lebegue.christophe.FarmMgmt.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity // This tells Hibernate to make a table out of this class
public class Activity {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
   
//    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
//    private Set<Task> tasks;

    public String toString(){
    	return id + ":" + name;
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
	
	
//    public Set<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(Set<Task> tasks) {
//        this.tasks = tasks;
//    }

}