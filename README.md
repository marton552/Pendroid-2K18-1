# Pendroid-2K18-1
Űrhajós lövölde

Ehhez a játékhoz hasonló: https://www.youtube.com/watch?v=_P33Ln3u1TE

A lényeg: felülnézetes űrhajó. Kicsit a felhasználó ujja felett repülne a hajó, autómatikusan lő. Ha a felhasználó felengedi az ujját akkor megáll a játék és bejön a menü. Az ellenségek folyamatosan jönnek és jönnek. Csak egy bizonyos SOK idő után mennének el, amúgy csak gyűlnek ha a felhasználó béna kilőni őket. Minden ölésért pont jár, ez a pont a képernyő tetején egy csíkban gyűlne. Ha betellik akkor már nem jön több ellenfél, csak azokat kell megölni a mi a képernyőn van. utána bejön három darab powerup lehetőség. Csak egyet vehet fel a játékos. Utána a felső csík nullázódik és ismét pontokat kell gyűjtenie. Majd ha megint betellik megint powerup és így tovább. Legyen egy különálló ÖSSZpontszámláló.
Az űrhajónak 3 élete legyen és ha mindet elveszti akkor vége a játéknak, összes powerup elveszik, és előről kezdi.
A játékban legyenek bossok amik random jöhetnek. Ezek a bossok külön elemekből állnak, amiket külön kell szétlőni, de persze legyen egy összélete.
És kb ennyi. Összeszedtem a szabad feladatotak (pl bossok, poweruppok) és a személyre szólókat.

FONTOS!
A poweruppok 2D textúráiból két fajta van (2 mappába szétszórva). Az egyik a "pick" vagyis az ami pályarészenkénk jön és össze lehet szedni. A másik a "use" vagyis az ami a bal vagy jobb sarokban ott van (a GUI-ban) és jelzi ha már használatban van az a képesség. Mappákba csoportosítottam de a nevükben is benne van. A képességekből is két fajta van. Van passzív és az ult. Az ult használható képesség cooldown van rajta. A png-k nevét // után odaírtam a feladatok mögé.

Szabad feladatok:

Poweruppok: 
- [ ] Tűzerősség növelése 20%-al //firepower
- [ ] Lővés gyorsaság növelése 30%-al //firespeed
- [ ] Élet 50%-ának pajzsá tétele. (pajzs = kétszer akkora sebzést tud befogadni és tud regenerálódni lassan) //shield1
- [ ] Követős rakéták //rocket
- [ ] Mini űrhajó - Pew (Pew = autómatikusan lő időközönként az ellenfélre, és melletted marad. Sebezhetetlen) //pew
- [ ] Mini űrhajó - Doc (Doc = olyan mint a Pew, csak nem lő hanem az életedet gyógyítja lassan) //doc
- [ ] A bevitt sebzések arányában tölten vissza az életed //fireheal
- [ ] Egy pajzs ami egy bármekkora sebzést felfog, viszont utána eltűnik és 5 másodperc a pajzs regenerációja //shield2
- [ ] A lövedék nem áll meg az eltalált ellenfélnél hanem "átmegy rajta" //fireover
- [ ] passzívan van egy gyenge lézere ami közvetlen előre lő //laser

Használható képességek: (ezek mondjuk a bal alsó sarokba lennének, rá kell nyomni hogy aktiválódjon. Ugyanúgy a Poweruppoknál lehet majd választani őket)
- [ ] Laser //laserzlt
- [ ] Tűzerő megsokszorozása //firepowerult
- [ ] Egyszerre 5-10 követős rakéta //targetult
- [ ] Teljes sebzésvédelem pár másodpercig //shieldult
- [ ] Ha benyomja akkor "elkábitja az ellenfeleket, vagyis azok nem tudnak lőni //sleep

Bossok:


Személyreszóló feladatok:

István:
- [ ] Alap mozgás
- [ ] Lövés
- [ ] Ellenfelek időközönkénti érkezése
- [ ] "mesterséges inteligencia" vagyis hogy az ellenfelek PRÓBÁLNAK a játékosra lőni
- [ ] Martin bugjainak fixelése
- [ ] Github használata

Martin:
- [ ] Menü
- [ ] Bossok
- [ ] Poweruppok beprogramozása

Bendzsi:
- [ ] 3D grafika (4 ellenfél 2 boss Doc-Pew)
- [ ] Ha van idő akkor pénisz űrhajó

Márton:
- [ ] 2D grafika (poweruppok)
- [ ] 2D animációk
- [ ] 2D háttér elemek
- [ ] Zene és hangok
