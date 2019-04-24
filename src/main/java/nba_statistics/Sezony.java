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
import javax.persistence.Table;
import javax.persistence.OneToMany;

/**
 *
 * @author Przemek
 */


@Entity
@Table(name = "sezony")
public class Sezony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sezonu")
    private int id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "data_rozpoczecia")
    private String dataRozp;

    @Column(name = "data_zakonczenia")
    private String dataZakon;
    
    @OneToMany(mappedBy = "sezon", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<Mecze> mecze;

        @OneToMany(mappedBy = "sezon", cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                CascadeType.DETACH, CascadeType.REFRESH})
    List<HistoriaDruzynZawodnika> histDruzZaw;
    
    public Sezony() {
    }

    ;

    public Sezony(String nazwa, String dataRozp, String dataZakon) {
        this.nazwa = nazwa;
        this.dataRozp = dataRozp;
        this.dataZakon = dataZakon;
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

    public String getDataRozp() {
        return dataRozp;
    }

    public void setDataRozp(String dataRozp) {
        this.dataRozp = dataRozp;
    }

    public String getDataZakon() {
        return dataZakon;
    }

    public void setDataZakon(String dataZakon) {
        this.dataZakon = dataZakon;
    }

    @Override
    public String toString() {
        return "Sezony{" + "id=" + id + ", nazwa=" + nazwa + ", dataRozp=" + dataRozp + ", dataZakon=" + dataZakon + '}';
    }

}