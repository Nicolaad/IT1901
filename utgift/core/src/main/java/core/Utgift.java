package core;


public class Utgift {
    private String navn;
    private String pris;
    private String kategori;


    /**
     * @param navn
     * @param pris
     * @param kategori
     * //Lager et utgifts objekt som inneholder tre strenger: navn, pris og kategori.
     */
    public Utgift(String navn, String pris, String kategori) {
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;
    }

    /**
     * @return En string med navnet til utgiften.
     */
    public String getNavn() {
        return navn;
    }

    /**
     * @return En string med navnet paa kategorien til utgiften.
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * @return En string med pris til utgiften.
     */
    public String getPrisString() {
        return pris;
    }

    /**
     * @return En double med pris til utgiften.
     */
    public double getPris() {
        return Double.parseDouble(pris);
    }

    /**
     * @return En string bestaaende av navn;pris;kategori
     */
    public String toString() {
        return "" + navn + ";" + pris + ";" + kategori;
    }

    /**
     * @param toString  en string paa formen navn;pris;kategori
     * @return returnerer et utgifts objekt av strengen.
     */
    public static Utgift createUtgiftObject(String toString) {
        String[] s = toString.split(";");
        return new Utgift(s[0],s[1],s[2]);
    }
}
