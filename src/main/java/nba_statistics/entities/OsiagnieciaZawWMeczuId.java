package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Przemek
 */

@Embeddable
public class OsiagnieciaZawWMeczuId implements Serializable {

    //field representing part of composite PK and at the same time FK
    @Column(name = "id_zawodnika")
    int id_zawodnika;

    //field representing part of composite PK and at the same time FK
    @Column(name = "id_meczu")
    int id_meczu;

    public OsiagnieciaZawWMeczuId(){};

    public OsiagnieciaZawWMeczuId(int id_zawodnika, int id_meczu) {
        this.id_zawodnika = id_zawodnika;
        this.id_meczu = id_meczu;
    }

    public int getId_zawodnika() {
        return id_zawodnika;
    }

    public void setId_zawodnika(int id_zawodnika) {
        this.id_zawodnika = id_zawodnika;
    }

    public int getId_meczu() {
        return id_meczu;
    }

    public void setId_meczu(int id_meczu) {
        this.id_meczu = id_meczu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OsiagnieciaZawWMeczuId)) return false;
        OsiagnieciaZawWMeczuId that = (OsiagnieciaZawWMeczuId) o;
        return Objects.equals(getId_zawodnika(), that.getId_zawodnika()) &&
                Objects.equals(getId_meczu(), that.getId_meczu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_zawodnika, id_meczu);
    }
}
