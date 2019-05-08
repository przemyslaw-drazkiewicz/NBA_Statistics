package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Adam
 */

@Embeddable
public
class ZawodnikPozycjaID implements Serializable {

    @Column(name = "id_zawodnika")
    int id_zawodnika;

    @Column(name = "id_pozycji")
    int id_pozycji;

    public ZawodnikPozycjaID() {
    }

    public int getId_zawodnika() {
        return id_zawodnika;
    }

    public void setId_zawodnika(int id_zawodnika) {
        this.id_zawodnika = id_zawodnika;
    }

    public int getId_pozycji() {
        return id_pozycji;
    }

    public void setId_pozycji(int id_pozycji) {
        this.id_pozycji = id_pozycji;
    }

    public ZawodnikPozycjaID(int id_zawodnika, int id_pozycji) {
        this.id_zawodnika = id_zawodnika;
        this.id_pozycji = id_pozycji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZawodnikPozycjaID)) return false;
        ZawodnikPozycjaID that = (ZawodnikPozycjaID) o;
        return Objects.equals(id_zawodnika, that.id_zawodnika) &&
                Objects.equals(id_pozycji, that.id_pozycji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_zawodnika, id_pozycji);
    }
}
