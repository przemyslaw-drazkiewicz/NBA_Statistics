/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nba_statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @Column(name="id_druzyny_gospodarza")
    private int idDrGosp;
    
    @Column(name="id_druzyny_goscia")
    private int idDrGosc;
    
    @Column(name="data")
    String data;
    
    @Column(name="punkty_gospodarza")
    private int pktGosp;
    
    @Column(name="punkty_goscia")
    private int pktGosc;
    
    @Column(name="ilosc_dogrywek")
    private int iloscDogrywek;
    
    @Column(name="id_sezonu")
    private int idSezonu;
    
    public Mecze(){
    
    }

    public Mecze(int idDrGosp, int idDrGosc, String data, int pktGosp, int pktGosc, int iloscDogrywek, int idSezonu) {
        this.idDrGosp = idDrGosp;
        this.idDrGosc = idDrGosc;
        this.data = data;
        this.pktGosp = pktGosp;
        this.pktGosc = pktGosc;
        this.iloscDogrywek = iloscDogrywek;
        this.idSezonu = idSezonu;
    }
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDrGosp() {
        return idDrGosp;
    }

    public void setIdDrGosp(int idDrGosp) {
        this.idDrGosp = idDrGosp;
    }

    public int getIdDrGosc() {
        return idDrGosc;
    }

    public void setIdDrGosc(int idDrGosc) {
        this.idDrGosc = idDrGosc;
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
        return "Mecze{" + "id=" + id + ", idDrGosp=" + idDrGosp + ", idDrGosc=" + idDrGosc + ", data=" + data + ", pktGosp=" + pktGosp + ", pktGosc=" + pktGosc + ", iloscDogrywek=" + iloscDogrywek + ", idSezonu=" + idSezonu + '}';
    }
    
}
