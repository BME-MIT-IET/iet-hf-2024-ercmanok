# UI teszt készítése

### Setup
A tesztelt grafikus program java swing-et használ. 
Szükség volt a grafikus program megjelenítésére, és a felhasználó által elvégezhető tevékenységének szimulálására (kattintások, szöveges inputok), ezt az assertj swing segítségével oldottam meg.

### UI Teszt
A teszt black-box-ként tekint a programra, csak a grafikus inputokkal és outputokkal foglalkozik.
A felhasználót utánozva adja meg az inputokat (pl. kattintás egy gombon, szöveg begépelése), majd ellenőrzi az outputot (pl. az "új játék" gomb megnyomására valóban elkezdődött-e egy új játék).
A teszt leellenőrzi a játék menü ablakain található gombok / labelek megfelelő működését, a játékablakon található gombok egy részét, illetve a játék fájlba mentését, és onnan való visszatöltését is.
Maga a játék grafikus felépítése randomizált, így nem tudtam minden részére megírni a tesztet. (pl. a játékosok mozgásához az egér kattintásával kell kiválasztani a mozgás célját, ami azonban mindig változik)

### Összegzés
Sokáig tartott beüzemelni a tesztkörnyezetet, utána már csak néhány UI elem beazaonosításához kellett egyértelmű azonosítókkal ellátni a megfelelő java swing komponenseket, amit a setName() függvénnyel tettem meg.