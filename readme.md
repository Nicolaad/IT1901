# Group gr1911 repository 
 
## Utgift

Dette prosjektet er en trelagsapplikasjon med et domenelag, brukergrensesnitt (UI) og persistens (lagring), samt et REST-api bygget rundt kjernelogikken som tilbys av en webserver. 
Prosjektet er konfigurert med gradle som byggesystem.

**utgift/readme.md** inneholder brukerhistorie og beskrivelse av hva appen handler om og er ment å gjøre samt skjermbilder og skisser.

## Organisering av koden

Prosjektet er organisert som et multi-modul-prosjekt med gradle, med følgende moduler.

- **utgift/core** 
- **utgift/fxui** 
- **utgift/restapi** 
- **utgift/restserver** 

Hver mappe/modul har sin egen README.md fil som inneholder informasjon om hva modulen består av, bygging med gradle, og annen relevant informasjon. 

## Kjøring av prosjektet 
Kjøring av Appen gjøres enklest ved å skrive `gradlew run` i terminalen når man er inni **utgift** mappen. Dvs. `\gr1911\utgift> gradlew run`. 