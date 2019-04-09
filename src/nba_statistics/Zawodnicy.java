/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.OneToMany;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "zawodnicy")
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

    @OneToMany(mappedBy = "druzyna", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    List<OsiagnieciaZawWMeczu> osiagZawWMeczu;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_zawodnika_schodzacego")
    List<HistZmianWMeczu> histZmianWMeczuZawSchodz;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_zawodnika_wchodzacego")
    List<HistZmianWMeczu> histZmianWMeczuZawWchodz;

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

    public Druzyny getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyny druzyna) {
        this.druzyna = druzyna;
    }

    public List<HistoriaDruzynZawodnika> getHistDruzynZaw() {
        return histDruzynZaw;
    }

    public void setHistDruzynZaw(List<HistoriaDruzynZawodnika> histDruzynZaw) {
        this.histDruzynZaw = histDruzynZaw;
    }

    public List<OsiagnieciaZawWMeczu> getOsiagZawWMeczu() {
        return osiagZawWMeczu;
    }

    public void setOsiagZawWMeczu(List<OsiagnieciaZawWMeczu> osiagZawWMeczu) {
        this.osiagZawWMeczu = osiagZawWMeczu;
    }

    public List<HistZmianWMeczu> getHistZmianWMeczuZawSchodz() {
        return histZmianWMeczuZawSchodz;
    }

    public void setHistZmianWMeczuZawSchodz(List<HistZmianWMeczu> histZmianWMeczuZawSchodz) {
        this.histZmianWMeczuZawSchodz = histZmianWMeczuZawSchodz;
    }

    public List<HistZmianWMeczu> getHistZmianWMeczuZawWchodz() {
        return histZmianWMeczuZawWchodz;
    }

    public void setHistZmianWMeczuZawWchodz(List<HistZmianWMeczu> histZmianWMeczuZawWchodz) {
        this.histZmianWMeczuZawWchodz = histZmianWMeczuZawWchodz;
    }

    @Override
    public String toString() {
        return "Zawodnicy{" + "id=" + id + ", imie=" + imie + ", nazwisko=" + nazwisko + ", dataUr=" + dataUr + ", wzrost=" + wzrost + ", waga=" + waga + '}';
    }

}
