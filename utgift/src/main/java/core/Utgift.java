package core;


public class Utgift {
    private String navn;
    private String pris;
    private String kategori;

    public Utgift(String navn, String pris, String kategori){
        this.navn = navn;
        this.pris = pris;
        this.kategori = kategori;
    }

    public String getNavn(){
        return navn;
    }

    public String getKategori(){
        return kategori;
    }

    public String getPrisString(){
        return pris;
    }

    public double getPris(){
        return Double.parseDouble(pris);
    }

    public String toString(){
        return ""+navn+"        "+pris+"        "+kategori;
    }

    public static Utgift createUtgiftObject(String toString){
        String[] s = toString.split(";");
        return new Utgift(s[0],s[1],s[2]);
    }
}
