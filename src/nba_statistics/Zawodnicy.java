/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Przemek
 */
public class Zawodnicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zawodnika")
    private int id;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "data_urodzenia")
    private String dataUr;

    @Column(name = "wzrost")
    private float wzrost;

    @Column(name = "waga")
    private float waga;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_druzyny")
    private Druzyny druzyna;

    @OneToMany(mappedBy = "zawodnik", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    List<HistoriaDruzynZawodnika> histDruzynZaw;

    public Zawodnicy(String imie, String nazwisko, String dataUr, float wzrost, float waga) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUr = dataUr;
        this.wzrost = wzrost;
        this.waga = waga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getDataUr() {
        return dataUr;
    }

    public void setDataUr(String dataUr) {
        this.dataUr = dataUr;
    }

    public float getWzrost() {
        return wzrost;
    }

    public void setWzrost(float wzrost) {
        this.wzrost = wzrost;
    }

    public float getWaga() {
        return waga;
    }

    public void setWaga(float waga) {
        this.waga = waga;
    }

    @Override
    public String toString() {
        return "Zawodnicy{" + "id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", dataUr=" + dataUr + ", wzrost=" + wzrost + ", waga=" + waga + '}';
    }
    
    
}
