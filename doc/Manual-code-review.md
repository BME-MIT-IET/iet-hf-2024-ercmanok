# Manuális kód átvizsgálás elvégzése az alkalmazás egy részére

## Átvizsgálási szempontok
Az alább részletezett manuális kód átvizsgálás során a következő szempontokat tartottam szem előtt:
    
        - megfelelő kommentezés, érthetőség
            - mennyiségben
            - minőségben
        - osztályon belüli konzisztencia
        - projekten belüli konzisztencia
        - clean code elvek betartása
        - kivételek megfelelő kezelése
            - elkapás, dobás
            - Nullpointer exception
        - redundancia csökkentés
        - felhasználó megfelelő értesítése
        - magic numbers elkerülés
        - későbbi kódmódosítás megkönnyítés
            - karbantarthatóság
            - hardcoded érték konstansba kiszervezése

## Cistern osztály

### Észrevétel

A step metódusban a Math.random() * 100 <= 33 kifejezés hardcoded értéket tartalmaz. Lehetséges megoldás konstansba helyezni, hogy könnyebb legyen módosítani. Például: private static final double pipe_creation = 33.0; //... boolean createNewPipe = Math.random() * 100 <= pipe_creation;

A step metódus minden körben új csövet hoz létre, mivel a createNewPipe változó mindig igaz, ezáltal redundáns a kód.

## Pipe osztály

### Észrevétel

Az osztály átvizsgálása során nem találtam különösebb hibát, vagy jelentős javítási lehetőséget, leszámítva esetleg azt, hogy a Math.random() * 360 típusú számítások esetében érdemes lehet konstansokat használni, ezáltal elkerülhető a magic number használat.

## Saboteur osztály

### Észrevétel

A makeItSlippery metódusban nincs ellenőrizve, hogy a a component változó nem null-e. Ha ellenőrizve lenne, akkor elkerülhető lenne a Nullpointer kivétel előfordulás.

## Game osztály

### Észrevétel

Az osztály átvizsgálása során nem találtam különösebb kivetni való hibát, vagy számottevő javítási, optimalizálási lehetősget. A kód jól strukturált, egyértelmű, megértését a kommentek segítik. Esetleg az egyes metódusokhoz tartozhatna több komment, valamint a felhasználói értesítéseket pontosítani lehetne.

## MainMenuWindow osztály

### Észrevétel

Az osztály logikai sorrendben tartalmazza az összes gomb létrehozását és hozzáadását a panelekhez. Az összes gomb megfelelő eseménykezelőkkel van ellátva. A layout menedzser segítségével a komponensek elrendezése átlátható.

## Player osztály

### Észrevétel

Az osztály kódjában nem találtam különösebb kivetni való hibát. Az attribútumok megfelelően védettek, protected láthatóságúak. A metódusok helyesen, absztraktként vannak deklarálva, mivel megvalósításuk a leszármazott osztályokban történik. A Serializable interfésznek köszönhetően a kód lehetővé teszi a sorosítást.

## Spring osztály

### Észrevétel

A kód nem tartalmaz kivételkezelést.

A drawOnMap metódusban meg lehetne vizsgálni, hogy a g objektum nem null-e, mielőtt a rajzolási műveletek végrehajtódnak, ezáltal a NullPointer exception elkerülhető.

A step metódusban meg lehetne vizsgálni, hogy a pipes lista nem null-e, mielőtt az iteráció végrehajtódik.

## Drawable osztály

### Észrevétel
A Graphics g paraméter a drawOnMap metódusban nem felel meg a clean code elveknek, mivel az egybetűs változóneveket alapvetően nem részesíti előnyben.

## PipelineSystem osztály

### Észrevétel
A flowRate mező értékét érdemes lehetne static-nak jelölni, mivel ez egy konstans érték.

Az addComponent metódus esetében érdemes lehetne ellenőrizni, hogy a hozzáadandó komponens nem null értékű.

## PlumberPanel osztály

### Észrevétel
A paraméterek és visszatérési értékek kommentezése lehetne részletesebb a jobb érthetőség érdekében.

A SelectorPanel példányosítás és a synchronized blokk több helyen is ismétlődik.

## Összegzés
Az összes átnézett osztály jól kommentezett, a kommentek jól, és könnyen érthetőek.\
Az átnézett osztályokban mind a kód, mind a kommentek  jól strukturáltak és érthetőek, stílusukat tekintve egyaránt konzisztensek. Azonban elnevezések nem mindig beszédesek, van ahol nem követik a clean code elveket.   
