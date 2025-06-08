package model;

public class GerakanDur extends Gerakan {
    protected int durasi;

    public GerakanDur(String namaGerakan, String bagianTubuh, int durasi) {
        super(namaGerakan, bagianTubuh);
        this.durasi = durasi;
    }

    public GerakanDur(String namaGerakan, int durasi) {
        super(namaGerakan);
        this.durasi = durasi;
    }

    @Override
    public void detail() {
        System.out.println(namaGerakan + " (" + durasi + " detik)");
    }

    public int getDurasi() {
        return durasi;
    }
}