/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Adam
 */

@Embeddable
class ZawodnikPozycjaID implements Serializable{

    @Column(name = "id_zawodnika")
    int id_zawodnika;
    
    @Column(name = "id_pozycji")
    int id_pozycji;

    public ZawodnikPozycjaID() {
    }

    public int getId_zawodnika() {
        return id_zawodnika;
    }

    public void setId_zawodnika(int id_zawodnika) {
        this.id_zawodnika = id_zawodnika;
    }

    public int getId_pozycji() {
        return id_pozycji;
    }

    public void setId_pozycji(int id_pozycji) {
        this.id_pozycji = id_pozycji;
    }

    public ZawodnikPozycjaID(int id_zawodnika, int id_pozycji) {
        this.id_zawodnika = id_zawodnika;
        this.id_pozycji = id_pozycji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZawodnikPozycjaID)) return false;
        ZawodnikPozycjaID that = (ZawodnikPozycjaID) o;
        return Objects.equals(id_zawodnika, that.id_zawodnika) &&
                Objects.equals(id_pozycji, that.id_pozycji);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(id_zawodnika, id_pozycji);
    }
}

@Entity(name="ZawodnikPozycja")
@Table(name = "zawodnik_pozycja")
public class ZawodnikPozycja {

    @EmbeddedId
    private ZawodnikPozycjaID id;

    @ManyToOne
    @MapsId("id_zawodnika")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_zawodnika")
    private Zawodnicy zawodnik;

    @ManyToOne
    @MapsId("id_pozycji")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_pozycji")
    private Pozycje pozycja;

    public ZawodnikPozycja(){}

    public ZawodnikPozycja(Zawodnicy zawodnik, Pozycje pozycja) {
        id = new ZawodnikPozycjaID();
        this.zawodnik = zawodnik;
        this.pozycja = pozycja;
    }

    public ZawodnikPozycjaID getId() {
        return id;
    }

    public void setId(ZawodnikPozycjaID id) {
        this.id = id;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

    public Pozycje getPozycje() {
        return pozycja;
    }

    public void setPozycje(Pozycje pozycje) {
        this.pozycja = pozycje;
    }

    @Override
    public String toString() {
        return "ZawodnikPozycja{" +
                "id=" + id +
                ", zawodnik=" + zawodnik +
                ", pozycje=" + pozycja +
                '}';
    }
}
