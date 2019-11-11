# Group gr1911 repository 
 
## Utgift

Dette prosjektet er en trelagsapplikasjon med et domenelag, brukergrensesnitt (UI) og persistens (lagring), samt et REST-api bygget rundt kjernelogikken som tilbys av en webserver. 
Prosjektet er konfigurert med gradle som byggesystem. Prosjektet er nå bygget ut til å ha 2 brukergrensesnitt, ett i javafx, som tidligere, samt et nytt grensesnitt laget med React.

**utgift/readme.md** inneholder brukerhistorie og beskrivelse av hva appen handler om og er ment å gjøre samt skjermbilder og skisser.

## Organisering av koden

Prosjektet er organisert som et multi-modul-prosjekt med gradle, med følgende moduler.

- **utgift/core** 
- **utgift/fxui** 
- **utgift/restapi** 
- **utgift/restserver** 
- **utgift/reactweb**

Hver mappe/modul har sin egen README.md fil som inneholder informasjon om hva modulen består av, bygging med gradle, og annen relevant informasjon. 

# Hvordan starte appen
Først må appen bygges med **gradlew build** i **utgift** mappen (/gr1911/utgift)
Appen startes ved å kjøre **gradlew run** i **utgift** mappen (/gr1911/utgift)
Javafx UI, react UI og grizzly server vil da kjøres i parallell og man vil få opp to brukergrensesnitt, ett i react og ett med javafx. 
Grunnen til dette er fordi Vi er med fornøyde med javafx uiet enn react, men react appen har all nødvendig funksjonalitet. 