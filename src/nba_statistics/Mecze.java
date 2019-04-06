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
@Table(name="mecze")
public class Mecze {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_meczu")
    private int id;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_druzyny_gospodarza")
    private Druzyny druzGosp;
    
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="id_druzyny_goscia")
    private Druzyny druzGosc;
    
    @Column(name="data")
    private String data;
    
    @Column(name="punkty_gospodarza")
    private int pktGosp;
    
    @Column(name="punkty_goscia")
    private int pktGosc;
    
    @Column(name="ilosc_dogrywek")
    private int iloscDogrywek;
    
    //DO ZMIANY PO UTWORZENIU KLASY
    @Column(name="id_sezonu")
    private int idSezonu;
    
    public Mecze(){
    
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

    public int getIdSezonu() {
        return idSezonu;
    }

    public void setIdSezonu(int idSezonu) {
        this.idSezonu = idSezonu;
    }

    @Override
    public String toString() {
        return "Mecze{" + "id=" + id + ", druzGosc=" + druzGosc + ", data=" + data + ", pktGosp=" + pktGosp + ", pktGosc=" + pktGosc + ", iloscDogrywek=" + iloscDogrywek + '}';
    }


    
}
