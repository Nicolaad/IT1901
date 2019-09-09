# Group gr1911 repository 
 
## Utgift

Dette prosjektet er en trelagsapplikasjon med et domenelag, brukergrensesnitt (UI) og persistens (lagring). Prosjektet inneholder tester for Utgift-klassen. Prosjektet er konfigurert med gradle som byggesystem.

## Organisering av koden

Prosjektet er organisert med 2 * 2 = 4 kildekodemapper, kode og ressurser for henholdsvis applikasjonen selv og testene:

- **src/main/java** for koden til applikasjonen
- **src/main/resources** for tilhørende ressurser, f.eks. data-filer og FXML-filer, som brukes av applikasjonen.
- **src/test/java** for testkoden
- **src/test/resources** for tilhørende ressurser, f.eks. data-filer og FXML-filer, som brukes av testene.

## Domenelaget

Appen handler om å samle inn alle utgifter, som har et navn, en pris og en kategori knyttet til dem. Domenelaget inneholder klasser for å representere og håndtere slike, samt for å håndtere en liste bestående av utgifts-objekter.

## Brukergrensesnittlaget

Brukergrensesnittlaget inneholder alle klasser og logikk knyttet til å legge til nye utgifter, og lagre de. Brukergrensesnittet til appen viser frem en liste over registrerte utgifter. Registrerte utgifter kan lagres og lastes inn på nytt.
Brukergrensesnittet er laget med JavaFX og FXML. JavaFX koden finnes i **utgift/src/main/java/ui** og FXML finnes i **utgift/src/main/resources/ui** 

## Persistentlaget

Persistenslaget inneholder alle klasser og logikk for lagring (skriving og lesing) av dataene i domenelaget. Vårt persistentlag leser og lagrer til en .txt fil. 

Persistentlaget finnes i **utgift/src/main/resources/json**
