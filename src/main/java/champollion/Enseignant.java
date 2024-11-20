package champollion;

import java.util.HashMap;
import java.util.HashSet;

public class Enseignant extends Personne {
    private HashSet<ServicePrevu> servicePrevus = new HashSet<ServicePrevu>();
    private HashSet<Intervention> intervensions = new HashSet<Intervention>();


    public Enseignant(String nom, String email) {
        super(nom, email);
    }
    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     */
    public int heuresPrevues() {
        int nb = 0;
        for (ServicePrevu p : servicePrevus) {
            nb = nb+heuresPrevuesPourUE(p.getUe());
        }
        return nb;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     */
    public int heuresPrevuesPourUE(UE ue) {
        float nb = 0;
        for (ServicePrevu p : servicePrevus) {
            if (p.getUe().equals(ue)) {
                nb += equivalentTd(TypeIntervention.TD,p.getVolTd())
                        + equivalentTd(TypeIntervention.CM, p.getVolCm())
                        + equivalentTd(TypeIntervention.TP, p.getVolTp());
            }
        }
        return Math.round(nb);
    }

    public float equivalentTd(TypeIntervention type, int valeur) {
        float nb = 0;
        if (type.equals(TypeIntervention.CM)) {
            nb+= valeur * 1.5;
        } else if (type.equals(TypeIntervention.TD)) {
            nb += valeur;
        } else if (type.equals(TypeIntervention.TP)) {
            nb+= valeur * 0.75;
        }
        return nb;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue       l'UE concernée
     * @param volCm le volume d'heures de cours magitral
     * @param volTd le volume d'heures de TD
     * @param volTp le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volCm, int volTd, int volTp) {
        if (volTp < 0 || volCm < 0 || volTd < 0) {
            throw new IllegalArgumentException("Le volume doit être positif.");
        }

        boolean ueEx = false;

        for (ServicePrevu p : servicePrevus) {
            if (p.getUe().equals(ue)) {
                ueEx = true;
                p.addCM(volCm);
                p.addTD(volTd);
                p.addTP(volTp);
            }
        }
        if (!ueEx) {
            servicePrevus.add(new ServicePrevu(volCm, volTd, volTp, ue));
        }
    }

    public ServicePrevu getServicePrevuFromUE(UE ue) {
        for (ServicePrevu s : servicePrevus) {
            if (s.getUe().equals(ue)) {
                return s;
            }
        }
        return null;
    }

    public HashSet<ServicePrevu> getServicePrevus() {
        return servicePrevus;
    }

    public void ajouteIntervention(Intervention inter) {
        if (getServicePrevuFromUE(inter.getUe()) == null) {
            throw new IllegalArgumentException("L'UE ne fait pas partie des enseignements");
        }
        intervensions.add(inter);
    }
    public boolean enSousService() {
        if (heuresPrevues() <= 192) {
            return true;
        }
        return false;
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) {
        int planif = 0;
        for (Intervention p : intervensions) {
            if (p.getUe().equals(ue) && p.getType().equals(type)) {
                planif = planif+ p.getDuree();
            }
        }
        int nb = getServicePrevuFromUE(ue).getServiceType(type);
        return nb - planif;
    }

    public HashSet<Intervention> getIntervensions() {
        return intervensions;
    }
}