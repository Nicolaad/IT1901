# Modul for REST-api
Denne modulen inneholder REST-api for utgift prosjektet.

## REST-api
Modulen består av logikken som benyttes på REST-tjeneste-objektet, og finnes i **UtgiftListService** klassen. Selve oppsettet av serveren ligger i en egen modul, som heter restserver.

API-et følger JAX-RS-standaren. Tjenesten aktiveres ved å angi `utgiftlist` i URL stien, slik at URL'en blir seende slik ut:
http://localhost:8080/utgiftlist/.

API-et tilbyr følgende metoder:

- lese innholdet til alle utgiftobjektene som er i utgiftlistobjektet. Oppnås ved GET til tjeneste-URL, dvs URL'en spesifisert over.
- Lese innholdet tilalle utgiftsobjektene i èn spesifikk kategori, angitt med kategorien **kategori**. **@GET** til tjeneste-URL/kategori. f.eks http://localhost:8080/utgiftlist/Mat/.
- Lese innholdet til et spesifikt utgiftobjekt i èn spesifikk kategori, angitt med posisjonenn **num**. **@GET** til tjeneste-URL/kategori/num. f.eks http://localhost:8080/utgiftlist/Mat/0/.
- Legge til utgiftobjekter, **@Post** av Json-kodet UtgiftList-objekter.


## Bygging med Gradle 
API-et er prosjekt av typen **java-library**. Som nevnt er det benyttet JAX-RS-standaren, men vi har ingen implementasjon av denne standaren i denne modulen.
Den finnes i **restserver** modulen sammen med implementasjon av bibloteket Jackson.