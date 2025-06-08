package model;

public class GerakanRep extends Gerakan {
    protected int repetisi;

    public GerakanRep(String namaGerakan, String bagianTubuh, int repetisi) {
        super(namaGerakan, bagianTubuh);
        this.repetisi = repetisi;
    }

    public GerakanRep(String namaGerakan, int repetisi) {
        super(namaGerakan);
        this.repetisi = repetisi;
    }

    @Override
    public void detail() {
        System.out.println(namaGerakan + " (" + repetisi + " repetisi)");
    }

    public int getRepetisi() {
        return repetisi;
    }
}