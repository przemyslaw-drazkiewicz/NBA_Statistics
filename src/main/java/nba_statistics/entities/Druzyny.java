/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Przemek
 */
@Entity
@Table(name = "druzyny")
public class Druzyny {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_druzyny")
    int id;

    @Column(name = "dywizja")
    String dywizja;

    @Column(name = "konferencja")
    String konferencja;

    @Column(name = "nazwa")
    String nazwa;

    @Column(name = "siedziba")
    String siedziba;

    @OneToMany(mappedBy = "druzGosp", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<Mecze> meczeGosp;

    @OneToMany(mappedBy = "druzGosc", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<Mecze> meczeGosc;
    
    @OneToMany(mappedBy = "druzyna", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<HistoriaDruzynZawodnika> histDruzynZaw;
    
    //druzyny - zawodnicy
    @OneToMany(mappedBy = "druzyna", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<Zawodnicy> zawodnicy;

    public Druzyny() {
    }

    public Druzyny(String dywizja, String konferencja, String nazwa, String siedziba) {
        this.dywizja = dywizja;
        this.konferencja = konferencja;
        this.nazwa = nazwa;
        this.siedziba = siedziba;
    }

    public void dodajMeczeGosp(Mecze mecz) {
        if (meczeGosp == null) {
            meczeGosp = new ArrayList<>();
        } else {
            meczeGosp.add(mecz);

            mecz.setDruzGosp(this);
        }
    }

    public void dodajMeczeGosc(Mecze mecz) {
        if (meczeGosc == null) {
            meczeGosc = new ArrayList<>();
        } else {
            meczeGosc.add(mecz);

            mecz.setDruzGosp(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDywizja() {
        return dywizja;
    }

    public void setDywizja(String dywizja) {
        this.dywizja = dywizja;
    }

    public String getKonferencja() {
        return konferencja;
    }

    public void setKonferencja(String konferencja) {
        this.konferencja = konferencja;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getSiedziba() {
        return siedziba;
    }

    public void setSiedziba(String siedziba) {
        this.siedziba = siedziba;
    }

    @Override
    public String toString() {
        return "Druzyny{" + "id=" + id + ", dywizja=" + dywizja + ", konferencja=" + konferencja + ", nazwa=" + nazwa + ", siedziba=" + siedziba + '}';
    }


}
