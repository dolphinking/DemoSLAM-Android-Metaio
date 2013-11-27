DemoSLAM-Android-Metaio
=======================

Applicazione Android di Realtà Aumentata basata su tecniche di SLAM.
Bisogna creare una mappa 3D dell'ambiente o oggetto che si intende aumentare. A quel punto la demo visualizza un modello 3D di un vado in un punto dell'ambiente o oggetto desiderato.
Nella demo è stata creata una mappa 3D di una scrivania, sopra la quale viene visualizzato il modello 3D di un vaso. La mappa 3D è stata creata tramite l'applicazione Toolbox messa a disposizione da Metaio http://dev.metaio.com/sdk/toolbox/
Per eseguire correttamente la demo bisogna creare la propria mappa 3D e sostituirla a quella presente
E' inoltre necessario scaricare l'SDK di Metaio e linkarlo al progetto.

## Getting Started
1. Importare il progetto in Eclipse
2. Scaricare MetaioSDK dal seguente link http://my.metaio.com/index.php?state=register
3. Linkare l'SDK per Android al progetto importato in Eclipse
4. Scaricare l'applicazione Toolbox e creare la mappa 3D http://dev.metaio.com/sdk/toolbox/
5. Spostare la mappa appena creata (è un file con estensione .3dmap) nella cartella **assets** del progetto
6. Aprire il file Camera.java e sostituire la mappa 3D appena creata alla riga 69 
    String trackingConfigFile = AssetsManager.getAssetPath("tuofile.3dmap");
7. Il vaso viene visualizzato nel punto (0,0) del sistema di riferimento della mappa.
8. Fare il Build del progetto ed eseguirlo su un dispositivo

**HINT**: quando la mappa 3D viene creata dall'applicazione Toolbox, questa non avrà una scala metrica e il suo sistema di riferimento non è noto. E' conveniente sempre effettuare l'operazione di Align della mappa, sempre tramite l'applicazione Toolbox. Quest'operazione allinea la mappa utilizzando un marker di dimensioni note; in questo modo la mappa allineata avrà delle dimensioni a scala reale e il punto di origine del sistema di riferimento sarà noto e coinciderà con il marker utilizzato. Maggiori dettagli: http://dev.metaio.com/sdk/toolbox/3d-maps/

### Documentazione Metaio SDK
http://dev.metaio.com/sdk/getting-started/android/setting-up-the-development-environment/

### Documentazione Metaio Toolbox
http://dev.metaio.com/sdk/toolbox/
http://dev.metaio.com/sdk/toolbox/3d-maps/
