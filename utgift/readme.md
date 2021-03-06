# Group gr1911 utgift

En Utgiftsapp som lar deg legge  til utgifter, med navn, pris og kategori.

# Brukerhistorier

Vi har valgt å ha våre brukerhistorier som issues, som finnes [her](https://gitlab.stud.idi.ntnu.no/it1901/gr1911/gr1911/issues?scope=all&utf8=%E2%9C%93&state=closed&label_name[]=Brukerhistorie)


# Diagrammer
Diagrammene for deliverable 3 ligger [her](https://gitlab.stud.idi.ntnu.no/it1901/gr1911/gr1911/tree/master/diagrams)

# Hvordan starte appen
Først må appen bygges med **gradlew build** i **utgift** mappen (/gr1911/utgift)
Appen startes ved å kjøre **gradlew run** i **utgift** mappen (/gr1911/utgift)
Javafx UI, react UI og grizzly server vil da kjøres i parallell og man vil få opp to brukergrensesnitt, ett i react og ett med javafx. 
Grunnen til dette er fordi Vi er med fornøyde med javafx uiet enn react, men react appen har all nødvendig funksjonalitet. 

<details>

<summary>Readme fra Deliverable 2</summary>

# Brukerhistorier

Jeg ønsker å sortere utgifter etter kategorier.

Jeg ønsker å lagre utgifter på en web-server.

## Skjermbilder for deliverable 2

Sketches ligger [her](https://gitlab.stud.idi.ntnu.no/it1901/gr1911/gr1911/tree/master/sketches)

Skjermbilde av forsiden:

![Foside](sketches/Skjermbilde DL2 forside.JPG)

Skjermbilde av ny utgift:

![Ny utgift](sketches/Skjermbilde DL2 ny utgift.JPG)

Skjermbilde fra web-server:

![web server](sketches/Skjermbilde rest1 DL2.JPG)

Skjermbilde fra web-server med GET metode

![web server get](sketches/Skjermbilde rest2 DL2.JPG)

</details>


<details>

  <summary>Readme fra Deliverable 1</summary>
  
  En utgiftsapp som lar deg legge inn nye utgifter, og se oversikt over hva man bruker penger på, sortert i diverse kategorier.
  Initielt kan bruker velge mellom gitte kategorier, men senere er det ønske om mulighet til å legge til egne. 
  Fordelingen mellom de ulike kategoriene vil vises i et kakediagram som i første omgang bare vil være illustrerende, 
  men som senere kan utvikles til at bruker kan trykke på kategoriene og få opp tilhørende utgifter. 
  
  Under videre utvikling finnes det også tanker om å introdusere dato som et potensielt skille mellom utgifter
  i kombinasjon med kategoriene. Mulighet til å endre og slette eksisterende utgifter er også noe som er ment å implementeres etterhvert. 
  
  Skisse av forsiden av appen:
  
  ![Forside](sketches/Forside skisse.png)
  
  Skisse av når man skal legge inn en ny utgift:
  
  ![Ny utgift](sketches/ny utgift skisse.png)
  
  Skjermbilde av forsiden:
  
  ![Forside](sketches/Skjermbilde forside.JPG)
  
  Skjermbilde av ny utgift:
  
  ![Ny utgift](sketches/Skjermbilde ny utgift.JPG)
   
  Brukerhistorie:
  
  Som en forbruker, ønsker jeg å kunne registrere mine utgifter, slik at jeg kan få oversikt, og se hva jeg bruker penger på.

</details>
