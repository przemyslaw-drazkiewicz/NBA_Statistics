/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Adam
 */

@Embeddable
class ZawodnikPozycjaID implements Serializable{

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_zawodnika")
    private Zawodnicy zawodnik;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_pozycji")
    private Pozycje pozycja;

    public ZawodnikPozycjaID() {
    }
        
    public ZawodnikPozycjaID(Zawodnicy zawodnik, Pozycje pozycja){
        this.zawodnik = zawodnik;
        this.pozycja = pozycja;
    }
    
    public Zawodnicy getZawodnik() {
        return zawodnik;
    } 
    
    public Pozycje getPozycja() {
        return pozycja;
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZawodnikPozycjaID)) return false;
        ZawodnikPozycjaID that = (ZawodnikPozycjaID) o;
        return Objects.equals(getZawodnik(), that.getZawodnik()) &&
                Objects.equals(getPozycja(), that.getPozycja());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getZawodnik(), getPozycja());
    }
}



@Entity(name="ZawodnikPozycja")
@Table(name = "zawodnik_pozycja")
public class ZawodnikPozycja {

    @EmbeddedId
    private ZawodnikPozycjaID id; 

    public ZawodnikPozycja(){};

    public ZawodnikPozycjaID getId() {
        return id;
    }

    public void setId(ZawodnikPozycjaID id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "ZawodnikPozycja{id= " + id + '}';
    }
    

    
}
