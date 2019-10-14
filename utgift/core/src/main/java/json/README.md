# Persistenslaget
Vi har valgt å bruke Jackson-bibloteket for generell serialisering (og deserialisering) til JSON.
Det finnes 2 domeneklasser og begge disse har en klasse for å serialisere og deserialisere.
I persistenslaget ligger også en klasse **Save** som lagrer objektene som er generert til fil. Tilsvarende finnes en klasse **Load** som laster fra fil.
**Save** og **Load** bruker Gson-bibloteket.