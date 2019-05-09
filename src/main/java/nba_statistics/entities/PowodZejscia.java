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
 * @author Adam
 */
@Entity
@Table(name="powody_zejscia")
public class PowodZejscia {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_powodu")
    private int id;
    
    @Column(name="nazwa")
    private String nazwa;
    
    @OneToMany(mappedBy="powodZejscia",
            cascade={CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.DETACH, CascadeType.REFRESH})
    private List<HistZmianWMeczu> historiaZmian;
    
    public void dodajHistorieZmian(HistZmianWMeczu historiaZmianWMeczu){
        if (historiaZmian == null)
        {
            historiaZmian = new ArrayList<>();
        }
        historiaZmian.add(historiaZmianWMeczu);
        historiaZmianWMeczu.setPowodZejscia(this);
        
    }
    
    public List<HistZmianWMeczu> getHistZmianWMeczu(){
        return historiaZmian;
    }
    
    public void setHistZmianWMeczu(List<HistZmianWMeczu> historiaZmian){
        this.historiaZmian = historiaZmian;
    }
            
    
}
