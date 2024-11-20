package champollion;

import java.util.Date;

public class Intervention {
    private Date date;
    private UE ue;
    private int duree;
    private TypeIntervention type;
    private boolean annule = false;
    private Salle salle;
    private Enseignant enseignant;

    public Intervention(Date date, int duree, Salle salle, TypeIntervention type, Enseignant enseignant, UE ue) {
        this.duree = duree;
        this.salle = salle;
        this.enseignant = enseignant;
        this.ue = ue;
    }

    public Date getDate() {
        return date;
    }

    public UE getUe() {
        return ue;
    }

    public int getDuree() {
        return duree;
    }

    public TypeIntervention getType() {
        return type;
    }

    public boolean getAnnule() {
        return annule;
    }

    public Salle getSalle() {
        return salle;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }
}
