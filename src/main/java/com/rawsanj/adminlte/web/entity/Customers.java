package com.rawsanj.adminlte.web.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Customers  {

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}


	
    private String firstname;

  
    private String lastname;

    
    private String email;

   
    //private Date addedDate;

}
