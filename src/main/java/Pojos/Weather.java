/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pojos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author litos
 */
@Entity
@Table(name = "WEATHER")
@NamedQueries({
    @NamedQuery(name = "Weather.findAll", query = "SELECT w FROM Weather w"),
    @NamedQuery(name = "Weather.findById", query = "SELECT w FROM Weather w WHERE w.id = :id"),
    @NamedQuery(name = "Weather.findByTemp", query = "SELECT w FROM Weather w WHERE w.temp = :temp"),
    @NamedQuery(name = "Weather.findByHumidity", query = "SELECT w FROM Weather w WHERE w.humidity = :humidity"),
    @NamedQuery(name = "Weather.findByWindspeed", query = "SELECT w FROM Weather w WHERE w.windspeed = :windspeed"),
    @NamedQuery(name = "Weather.findByUv", query = "SELECT w FROM Weather w WHERE w.uv = :uv"),
    @NamedQuery(name = "Weather.findByWeatherdesk", query = "SELECT w FROM Weather w WHERE w.weatherdesk = :weatherdesk"),
    @NamedQuery(name = "Weather.findByWeatherdate", query = "SELECT w FROM Weather w WHERE w.weatherdate = :weatherdate")})
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TEMP")
    private String temp;
    @Column(name = "HUMIDITY")
    private String humidity;
    @Column(name = "WINDSPEED")
    private String windspeed;
    @Column(name = "UV")
    private String uv;
    @Column(name = "WEATHERDESK")
    private String weatherdesk;
    @Column(name = "WEATHERDATE")
    @Temporal(TemporalType.DATE)
    private Date weatherdate;
    @JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    @ManyToOne
    private City cityId;

    public Weather() {
    }

    public Weather(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getWeatherdesk() {
        return weatherdesk;
    }

    public void setWeatherdesk(String weatherdesk) {
        this.weatherdesk = weatherdesk;
    }

    public Date getWeatherdate() {
        return weatherdate;
    }

    public void setWeatherdate(Date weatherdate) {
        this.weatherdate = weatherdate;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
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
        if (!(object instanceof Weather)) {
            return false;
        }
        Weather other = (Weather) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pojos.Weather[ id=" + id + " ]";
    }
    
}
