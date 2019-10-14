# Modul for restserver
Dette prosjektet inneholder webserveren med REST-API-et for utgift applikasjonen.

## Web-Serveren
REST-API-et er programmert etter JAX-RS-Standarden, som følge av dette benytter vi tjenester som støtter dette. 
Vi benytter derfor Jersey og grizzly2 for å denne webserveren. 

Koden består av følgende klasser:

- **UtgiftListConfig** Kobler serveren sammen "automagisk" sammen med UtgiftList-objektet som injectes i UtgiftListService.
- **UtgiftListGrizzlyApp** Oppstartskode med statisk metode som kjøres av FxApp og dermed starter serveren.

## Bygging med gradle
Vi har benyttet de dependencies vi har brukt på grunnlag av dokumentasjonen til Jersey og testing av det som fungerte i **simpleexample2**.
Vi har også inkludert diverse tillegg for testdekningsgrad og kodekvalitet. 