package nba_statistics.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity(name = "PozycjeZawWMeczu")
@Table(name = "pozycje_zawodnika_w_meczu")
public class PozycjeZawWMeczu {

    @EmbeddedId
    private PozycjeZawWMeczuID id;

    @ManyToOne
    @MapsId("id_zawodnika")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_zawodnika")
    private Zawodnicy zawodnik;

    @ManyToOne
    @MapsId("id_meczu")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_meczu")
    private Mecze mecz;

    @ManyToOne
    @MapsId("id_pozycji")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "id_pozycji")
    private Pozycje pozycja;

    public PozycjeZawWMeczu(){};

    public PozycjeZawWMeczu(Zawodnicy zawodnik, Mecze mecz, Pozycje pozycja) {
        id = new PozycjeZawWMeczuID();
        this.zawodnik = zawodnik;
        this.mecz = mecz;
        this.pozycja = pozycja;
    }

    public Zawodnicy getZawodnik() {
        return zawodnik;
    }

    public void setZawodnik(Zawodnicy zawodnik) {
        this.zawodnik = zawodnik;
    }

    public Mecze getMecz() {
        return mecz;
    }

    public void setMecz(Mecze mecz) {
        this.mecz = mecz;
    }

    public Pozycje getPozycja() {
        return pozycja;
    }

    public void setPozycja(Pozycje pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public String toString() {
        return "PozycjeZawWMeczu{" +
                "id=" + id +
                ", zawodnik=" + zawodnik +
                ", mecz=" + mecz +
                ", pozycja=" + pozycja +
                '}';
    }
}
