/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "historia_zmian_w_meczu")
public class HistZmianWMeczu {

    //tutaj zamiast Bi-directional jest Uni-directional a wiec brak adnotacji
    //@ManyToOne co oznacza ze np. usuwajac jakis mecz usunie sie rowniez rekord
    //z tej encji czyli HistZmianWMeczu(to samo dla Zadownikow itd.)
    //Trzeba by sprawdzic czy w pozostalych klasach tez nie powinnismy zamienic z Bi-directional 
    //na Uni-directional
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historii_zmian")
    private int id;

    @Column(name = "czas_zmiany")
    private Mecze mecz;

    @Column(name = "id_zawodnika_schodzacego")
    private int idZawodSchodz;

    @Column(name = "czas_zmiany")
    private String czasZmiany;

    @Column(name = "id_zawodnika_wchodzacego")
    private int idZawodWchodz;

    //KLASA DO IMPLEMENTACJI
    //private PowodyZejscia powodZejscia;
    
    public HistZmianWMeczu() {
    }

    ;

    public HistZmianWMeczu(Mecze mecz, int idZawodSchodz, String czasZmiany, int idZawodWchodz) {
        this.mecz = mecz;
        this.idZawodSchodz = idZawodSchodz;
        this.czasZmiany = czasZmiany;
        this.idZawodWchodz = idZawodWchodz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mecze getMecz() {
        return mecz;
    }

    public void setMecz(Mecze mecz) {
        this.mecz = mecz;
    }

    public int getIdZawodSchodz() {
        return idZawodSchodz;
    }

    public void setIdZawodSchodz(int idZawodSchodz) {
        this.idZawodSchodz = idZawodSchodz;
    }

    public String getCzasZmiany() {
        return czasZmiany;
    }

    public void setCzasZmiany(String czasZmiany) {
        this.czasZmiany = czasZmiany;
    }

    public int getIdZawodWchodz() {
        return idZawodWchodz;
    }

    public void setIdZawodWchodz(int idZawodWchodz) {
        this.idZawodWchodz = idZawodWchodz;
    }

    @Override
    public String toString() {
        return "HistZmianWMeczu{" + "id=" + id + ", mecz=" + mecz + ", idZawodSchodz=" + idZawodSchodz + ", czasZmiany=" + czasZmiany + ", idZawodWchodz=" + idZawodWchodz + '}';
    }

}
