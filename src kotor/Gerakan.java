package model;

public abstract class Gerakan {
    private static KatalogGerakan katalogGerakan;
    protected String namaGerakan;
    protected String bagianTubuh;

    public Gerakan(String namaGerakan, String bagianTubuh) {
        this.namaGerakan = namaGerakan;
        this.bagianTubuh = bagianTubuh;
    }

    public Gerakan(String namaGerakan) {
        this.namaGerakan = namaGerakan;
    }

    public abstract void detail();

    public String getNamaGerakan() {
        return namaGerakan;
    }

    public String getBagianTubuh() {
        return bagianTubuh;
    }

    // public static List<Gerakan> getGerakanSesuaiTujuan(String tujuan) {
    //     switch (tujuan.toLowerCase()) {
    //         case "menurunkan berat badan":
    //             return katalogGerakan.getGerakanMenurunkanBB();
    //         case "membentuk otot":
    //             return katalogGerakan.getGerakanMenambahOtot();
    //         case "menjaga stamina":
    //             return katalogGerakan.getGerakanStamina();
    //         default:
    //             System.out.println("Input tidak valid.");
    //             return new ArrayList<>();
    //     }
    // }
}