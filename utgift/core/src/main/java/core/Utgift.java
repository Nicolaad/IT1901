package core;


public class Utgift {
    private String navn;
    private String pris;
    private String kategori;
    private static int globalId = 1;
    private long id = 0;

    /**
     * Lager et Utgift objekt med navn, pris og kategori.
     *
     * @param navn     navn til utgiften
     * @param pris     prisen til utgiften
     * @param kategori navn paa kategori til utgiften
     */
    public Utgift(String navn, String pris, String kategori) {
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;
        this.id = globalId;
        globalId++;
    }

    /**
     * Returnerer en utgift, men den aksepterer et id input felt for å lettere håndtere json.
     * FOr øyeblikket vil den bli gitt en ny id, men det skal være lett å implementere ett nytt system oppå dette
     * @param navn navnet til utgiften
     * @param pris prisen til utgiften
     * @param kategori kategorien til utgiften
     * @param id id til utgiften
     */
    public Utgift(String navn, String pris, String kategori, long id) {
        this(navn, pris, kategori);
        //this.setId(id);
    }

    /**
     * returnerer id til objektet
     * @return iden til objektet
     */
    public long getId() {
        return id;
    }

    /**
     * setter en id til utgiften
     * @param id iden som skal settes
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Henter navn i strengformat
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
    public String prisString() {
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
     * @param toString en string paa formen navn;pris;kategori
     * @return returnerer et utgifts objekt av strengen.
     */
    public static Utgift createUtgiftObject(String toString) {
        String[] s = toString.split(";");
        return new Utgift(s[0], s[1], s[2]);
    }
    /*
    private static void validateUtgift(String name, String pris, String kategori){
        if (name.length() < 1 || pris.length() < 1 || kategori.length() < 1){
            throw new IllegalArgumentException("Argumentene må være lengere en 0 karakterer");
        }if (Double.parseDouble(pris)<=0){
            throw new IllegalArgumentException("Prisen må være større enn 0");
        }
    }
    */
}
