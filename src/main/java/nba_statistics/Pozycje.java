/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Adam
 */
@Entity
@Table(name = "pozycje")
public class Pozycje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pozycji")
    private int id;

    @Column(name = "nazwa")
    private String nazwa;

    @OneToMany(mappedBy="pozycja", cascade = CascadeType.ALL)
    private List<ZawodnikPozycja> zawodnikPozycja;

    public Pozycje(){}

    public Pozycje(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<ZawodnikPozycja> getZawodnikPozycja(){
        return zawodnikPozycja;
    }
    
    public void setZawodnikPozycja (List<ZawodnikPozycja> zawPoz){
        this.zawodnikPozycja = zawPoz;
    }
    
    public void dodajZawodnikPozycja(ZawodnikPozycja zawPoz){
        if (zawodnikPozycja == null){
            zawodnikPozycja = new ArrayList<>();
        }
        
        zawodnikPozycja.add(zawPoz);
        zawPoz.setPozycje(this);
    }

    @Override
    public String toString() {
        return "Pozycje{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", zawodnikPozycja=" + zawodnikPozycja +
                '}';
    }
}
