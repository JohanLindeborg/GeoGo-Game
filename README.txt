GeoGo V.2

Licens:

MIT License

Copyright (c) 2019 Johan Lindeborg

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


OBSERVERA:


Detta projektet använder sig av Java 8, detta beror på begränsningar i API:et som används för projektet. 
För att koden ska kunna köras krävs det att Java 8 är installerat i eclipse. OBS! Detta betyder inte att hela eclipse påverkas, guiden nedan visar
specifikt hur detta projektet ställs in att köras med java 8. Resterande eclipse påverkas alltså inte. Kartan som används i en spelomgång använder Google maps API.
En del av knapparna som används i programmet använder en förgjord bakgrund som bas. Här är en erkännande länk till den som gjorde basen till knapparna som användes.
<a href="https://www.freepik.com/free-photos-vectors/frame">Frame vector created by starline - www.freepik.com</a>

Om det är några konstigheter, kontakta mig gärna, mail eller sms används med fördel.    johanlindeborg248@gmail.com / 076 82 74 180


Uppstartnings-guide:

Lägg in filerna som ett eclipse projekt som vanligt.

Om java 8 inte redan finns tillgängligt behöver detta installeras, det är alltså ”JDK 8” som ska installeras och sedan läggas till i eclipse (https://techiedan.com/set-up-jdk-in-eclipse/ en guide om detta är oklart)
Om java 8 inte ska användas som standard för eclipse, låt det vara avmarkerat, dvs. Ej standard JDK för eclipse.

Gå in på https://www.teamdev.com/jxmaps Klicka på download uppe till höger. Öppna den nedladdade filen och gå in på libraries, kopiera alla .Jar filer i datorn och spara dem i mappen ”API_Filer” i projektmappen. 
Resterande filer i nedladdningen kan raderas.


För att kunna köra koden behöver följande inställningar i eclipse projektet göras:

1. Högerklicka på projektet, gå in på ”build path”, klicka på ”configure build path”.

2. Om filen ”JRE system library” INTE är java 8, ta bort denna.
	(Om java 8 redan används, hoppa över steg 3.)

3.klicka ”add library”, välj ”JRE systems library” -> next, välj ”alternate JRE”, välj ”java 8” I listan. ”Finish”

4. När java 8 finns I ”build path”, klicka på ”add external jar”, navigera till projektmappen, öppna mappen API_FILER, markera alla filer i denna mappen, klicka på ”open”. 

5. I panelen till vänster, klicka på ”java compiler” kontrollera att ”compiler compliance level” är på 1.8, om inte, sätt den till 1.8.

Nu ska allt fungera, det som ska finnas i build path är alltså 6 st. .JAR filer samt JRE systems library java 8. 



Att köra koden:
För att köra programmet, kör klassen ClientGUI i paketet ”gui”.
Vid multiplayer spel krävs det att servern är igång, servern startas genom klassen ”Main” i paketet ”Server”. När servern är startad skrivs serverns IP-address ut i konsolfönstret.
Denna Ip adressen används sedan i programmet för att koppla upp till servern.


GeoGo Development Team - 19/06/01
