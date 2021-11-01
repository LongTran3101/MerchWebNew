/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rawsanj.adminlte.web.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DreamStore
 */
@Entity
@Table(name = "account_merch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountMerch.findAll", query = "SELECT a FROM AccountMerch a"),
    @NamedQuery(name = "AccountMerch.findById", query = "SELECT a FROM AccountMerch a WHERE a.id = :id"),
    @NamedQuery(name = "AccountMerch.findByName", query = "SELECT a FROM AccountMerch a WHERE a.name = :name"),
    @NamedQuery(name = "AccountMerch.findByIp", query = "SELECT a FROM AccountMerch a WHERE a.ip = :ip"),
    @NamedQuery(name = "AccountMerch.findByDay", query = "SELECT a FROM AccountMerch a WHERE a.day = :day"),
    @NamedQuery(name = "AccountMerch.findBySale", query = "SELECT a FROM AccountMerch a WHERE a.sale = :sale"),
    @NamedQuery(name = "AccountMerch.findByMoney", query = "SELECT a FROM AccountMerch a WHERE a.money = :money"),
    @NamedQuery(name = "AccountMerch.findByEmail", query = "SELECT a FROM AccountMerch a WHERE a.email = :email")})
public class SaleMerch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @Column(name = "day")
    @Temporal(TemporalType.DATE)
    private Date day;
    @Basic(optional = false)
    @Column(name = "sale")
    private int sale;
    @Basic(optional = false)
    @Column(name = "money")
    private double money;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "path")
    private String path;

    @Transient
    private String dayString;
    public String getDayString() {
		return dayString;
	}

	public void setDayString(String dayString) {
		this.dayString = dayString;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public SaleMerch() {
    }

    public SaleMerch(Integer id) {
        this.id = id;
    }

    public SaleMerch(Integer id, String name, String ip, Date day, int sale, double money, String email) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.day = day;
        this.sale = sale;
        this.money = money;
        this.email = email;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaleMerch)) {
            return false;
        }
        SaleMerch other = (SaleMerch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication5.AccountMerch[ id=" + id + " ]";
    }
    
}
