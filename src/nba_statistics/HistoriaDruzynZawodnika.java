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
@Table(name = "Historia_druzyn_zawodnika")
public class HistoriaDruzynZawodnika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historii_druzyn_zawodnika")
    private int id;

    //DO ZMIANY
    @Column(name = "id_zawodnika")
    private int idZawodnika;

    //DO ZMIANY, tak jak nizej
    @Column(name = "id_sezonu")
    private int idSezonu;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "id_druzyny")
    private Druzyny druzyna;

    public HistoriaDruzynZawodnika() {
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdZawodnika() {
        return idZawodnika;
    }

    public void setIdZawodnika(int idZawodnika) {
        this.idZawodnika = idZawodnika;
    }

    public int getIdSezonu() {
        return idSezonu;
    }

    public void setIdSezonu(int idSezonu) {
        this.idSezonu = idSezonu;
    }

    public Druzyny getDruzyna() {
        return druzyna;
    }

    public void setDruzyna(Druzyny druzyna) {
        this.druzyna = druzyna;
    }


}
