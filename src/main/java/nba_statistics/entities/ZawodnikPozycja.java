/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

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
