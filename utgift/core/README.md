# Kjernemodulen (core)
I **core** prosjektet finnes domenelaget og  persistenslaget.
## Domenelaget
Domenelaget inneholder Javaklassene **Utgift** og **UtgiftList**. Der **Utgift** instansierer et utgiftobjekt, 
og **UtgiftList** er en liste som representerer utgiftobjekter.
Appen handler om lagring av finansielle utgifter, identifisert med navn, pris og kategori. 

Domenelaget finnes i **utgift.core** pakken.

## Persistenslaget
I persistenslaget ligger klasser for å lagre og laste data, i vårt prosjekt benyttes fillagring med JSON-syntaks.

Persistenslaget finnnes i **utgift.json** pakken.

## Bygging med gradle
Kodekvalitetsanalyseverktøy vi benytter er Jacoco, spotbugs og checkstyle. Spesifikasjonene for disse ligger i `build.gradle` , med spesifiseringer som at spotbugsrapporten genereres i en HTML fil,
og at selve byggingen av prosjektet ikke kanselleres skulle det være slik at ikke alle testene passerer. 