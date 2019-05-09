package nba_statistics.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public
class PozycjeZawWMeczuID implements Serializable {

    @Column(name = "id_zawodnika")
    int id_zawodnika;

    @Column(name = "id_meczu")
    int id_meczu;

    @Column(name = "id_pozycji")
    int id_pozycji;

    public PozycjeZawWMeczuID(){}

    public PozycjeZawWMeczuID(int id_zawodnika, int id_meczu, int id_pozycji) {
        this.id_zawodnika = id_zawodnika;
        this.id_meczu = id_meczu;
        this.id_pozycji = id_pozycji;
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

    public int getId_pozycji() {
        return id_pozycji;
    }

    public void setId_pozycji(int id_pozycji) {
        this.id_pozycji = id_pozycji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PozycjeZawWMeczuID)) return false;
        PozycjeZawWMeczuID that = (PozycjeZawWMeczuID) o;
        return Objects.equals(id_zawodnika, that.id_zawodnika) &&
                Objects.equals(id_pozycji, that.id_pozycji)&&
                Objects.equals(id_meczu, that.id_meczu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_zawodnika, id_pozycji, id_meczu);
    }
}
