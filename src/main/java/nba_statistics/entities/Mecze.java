/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import java.util.List;
import java.util.Objects;
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
 * @author Przemek
 */
@Entity
@Table(name = "mecze")
public class Mecze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meczu")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_druzyny_gospodarza")
    private Druzyny druzGosp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_druzyny_goscia")
    private Druzyny druzGosc;

    @Column(name = "data")
    private String data;

    @Column(name = "punkty_gospodarza")
    private int pktGosp;

    @Column(name = "punkty_goscia")
    private int pktGosc;

    @Column(name = "ilosc_dogrywek")
    private int iloscDogrywek;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_sezonu")
    private Sezony sezon;

    @OneToMany(mappedBy = "mecz", cascade = CascadeType.ALL)
    private List<PozycjeZawWMeczu> pozycjeZawWMeczu;

    @OneToMany(mappedBy = "mecz", cascade = CascadeType.ALL)
    private List<OsiagnieciaZawWMeczu> osiagZawWMeczu;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_meczu")
    private List<HistZmianWMeczu> histZmianWMeczu;

    public Mecze() {

    }

    public Mecze(String data, int pktGosp, int pktGosc, int iloscDogrywek) {
        this.data = data;
        this.pktGosp = pktGosp;
        this.pktGosc = pktGosc;
        this.iloscDogrywek = iloscDogrywek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Druzyny getDruzGosp() {
        return druzGosp;
    }

    public void setDruzGosp(Druzyny druzGosp) {
        this.druzGosp = druzGosp;
    }

    public Druzyny getDruzGosc() {
        return druzGosc;
    }

    public void setDruzGosc(Druzyny druzGosc) {
        this.druzGosc = druzGosc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPktGosp() {
        return pktGosp;
    }

    public void setPktGosp(int pktGosp) {
        this.pktGosp = pktGosp;
    }

    public int getPktGosc() {
        return pktGosc;
    }

    public void setPktGosc(int pktGosc) {
        this.pktGosc = pktGosc;
    }

    public int getIloscDogrywek() {
        return iloscDogrywek;
    }

    public void setIloscDogrywek(int iloscDogrywek) {
        this.iloscDogrywek = iloscDogrywek;
    }

    public Sezony getSezon() {
        return sezon;
    }

    public void setSezon(Sezony sezon) {
        this.sezon = sezon;
    }

    public List<OsiagnieciaZawWMeczu> getOsiagZawWMeczu() {
        return osiagZawWMeczu;
    }

    public void setOsiagZawWMeczu(List<OsiagnieciaZawWMeczu> osiagZawWMeczu) {
        this.osiagZawWMeczu = osiagZawWMeczu;
    }

    public List<HistZmianWMeczu> getHistZmianWMeczu() {
        return histZmianWMeczu;
    }

    public void setHistZmianWMeczu(List<HistZmianWMeczu> histZmianWMeczu) {
        this.histZmianWMeczu = histZmianWMeczu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mecze)) return false;
        Mecze that = (Mecze) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Mecze{" + "id=" + id + ", data=" + data + ", pktGosp=" + pktGosp + ", pktGosc=" + pktGosc + ", iloscDogrywek=" + iloscDogrywek + '}';
    }
}
