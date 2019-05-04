/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
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

@Embeddable
class OsiagnieciaZawWMeczuId implements Serializable{
    
    //field representing part of composite PK and at the same time FK
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_zawodnika")
    Zawodnicy zawodnik;
    
    //field representing part of composite PK and at the same time FK
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_meczu")
    Mecze mecz;
    
    public OsiagnieciaZawWMeczuId(){};

    public OsiagnieciaZawWMeczuId(Zawodnicy zawodnik, Mecze mecz) {
        this.zawodnik = zawodnik;
        this.mecz = mecz;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public Mecze getMecz() {
        return mecz;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OsiagnieciaZawWMeczuId)) return false;
        OsiagnieciaZawWMeczuId that = (OsiagnieciaZawWMeczuId) o;
        return Objects.equals(getZawodnik(), that.getZawodnik()) &&
                Objects.equals(getMecz(), that.getMecz());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getZawodnik(), getMecz());
    }
}

@Entity
@Table(name = "osiagniecia_zawodnika_w_meczu")
public class OsiagnieciaZawWMeczu {
    
    @EmbeddedId
    private OsiagnieciaZawWMeczuId id;
    
    @Column(name = "zdobyte_punkty")
    private int zdobytePunkty;
    
    @Column(name = "przechwyty")
    private int przechwyty;
    
    @Column(name = "zbiorki")
    private int zbiorki;
    
    @Column(name = "bloki")
    private int bloki;
    
    @Column(name = "faule")
    private int faule;
    
    @Column(name = "faule_techniczne")
    private int fauleTech;
    
    public OsiagnieciaZawWMeczu(){};

    public OsiagnieciaZawWMeczu(int zdobytePunkty, int przechwyty, int zbiorki, int bloki, int faule, int fauleTech) {
        this.zdobytePunkty = zdobytePunkty;
        this.przechwyty = przechwyty;
        this.zbiorki = zbiorki;
        this.bloki = bloki;
        this.faule = faule;
        this.fauleTech = fauleTech;
    }

    public OsiagnieciaZawWMeczuId getId() {
        return id;
    }

    public void setId(OsiagnieciaZawWMeczuId id) {
        this.id = id;
    }

    public int getZdobytePunkty() {
        return zdobytePunkty;
    }

    public void setZdobytePunkty(int zdobytePunkty) {
        this.zdobytePunkty = zdobytePunkty;
    }

    public int getPrzechwyty() {
        return przechwyty;
    }

    public void setPrzechwyty(int przechwyty) {
        this.przechwyty = przechwyty;
    }

    public int getZbiorki() {
        return zbiorki;
    }

    public void setZbiorki(int zbiorki) {
        this.zbiorki = zbiorki;
    }

    public int getBloki() {
        return bloki;
    }

    public void setBloki(int bloki) {
        this.bloki = bloki;
    }

    public int getFaule() {
        return faule;
    }

    public void setFaule(int faule) {
        this.faule = faule;
    }

    public int getFauleTech() {
        return fauleTech;
    }

    public void setFauleTech(int fauleTech) {
        this.fauleTech = fauleTech;
    }

    @Override
    public String toString() {
        return "OsiagnieciaZawWMeczu{" + "id=" + id + ", zdobytePunkty=" + zdobytePunkty + ", przechwyty=" + przechwyty + ", zbiorki=" + zbiorki + ", bloki=" + bloki + ", faule=" + faule + ", fauleTech=" + fauleTech + '}';
    }
    
    
}