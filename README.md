# MP3 Player

## Wzorce projektowe, użycie i zastosowanie

### 1. Wzorzec Stan
    Zastosowanie:
    
    
    
# UML Diagram
![diagram UML](https://user-images.githubusercontent.com/58574619/150343399-5a528942-71f2-47d8-b0a5-15a0688775ab.png)

Obserwator: informowanie systemu o usunięciu piosenki. SongManger obserwuje
AllSongs i zmienia piosenki w playlistach.

Strategia: Sposób sortowania piosenek - po nazwie, po długości i po roku wydania

Iterator: Używany podczas odtwarzania playlisty. Przechodzi przez wszystkie piosenki
w kolejce.

Singleton: Tworzenie klasy przetrzymującej piosenki.

Factory method: interfejs - ciemny, jasny motyw

Polecenie: alternatywne posługiwanie się odtwarzaczem (myszką i klawiaturą) next,
previous, start, stop 5

Proxy: Wykorzystywany przy kopiowaniu playlisty

Dekorator: Losowanie piosenek

Fasada: odtwarzanie muzyki - upraszczanie implementacji wczytywania strumienia
danych z pliku muzycznego i odtwarzania go.

# Stan: kontrolowanie muzyki - pauza i wznowienie.