package champollion;

public class ServicePrevu {
  private int volTp;
  private int volTd;
  private int volCm;
  private UE ue;

public ServicePrevu(int volTp, int volTd, int volCm, UE ue) {
    this.volTp = volTp;
    this.volTd = volTd;
    this.volCm = volCm;
    this.ue = ue;}

public int getServiceType (TypeIntervention type ) {
    int vol= 0;
    if (type.equals(TypeIntervention.CM)){
        vol = vol+this.getVolCm();
    }
    if (type.equals(TypeIntervention.TD)){
        vol = vol+this.getVolTd();
    }
    if (type.equals(TypeIntervention.TP)){
        vol = vol+this.getVolTp();
    }
    return vol;
}
    public void addCM ( int Add){
    volCm = volCm+Add;
    }
    public void addTD ( int Add){
    volTd = volTd+Add;
    }
    public void addTP ( int Add){
    volTp = volTp+Add;
    }
    public int getVolTp() {
        return volTp;
    }

    public int getVolTd() {
        return volTd;
    }

    public int getVolCm() {
        return volCm;
    }

    public UE getUe() {
        return ue;
    }
}
