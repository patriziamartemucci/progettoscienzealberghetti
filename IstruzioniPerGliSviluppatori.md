<h4>Istruzioni per l'installazione e l'utilizzo del plugin<br>
<br>
Subversion per Eclipse</h4>

Per poter accedere alla pagina Source in scrittura è necessario essere membri inseriti in "Project Commiters".

<ol>
<li> dalla pagina<br>
<a href='http://code.google.com/p/progettoscienzealberghetti/'>http://code.google.com/p/progettoscienzealberghetti/</a>
<br>
andate su Project Home e cliccate sul collegamento al vostro nome (link a destra sotto Project committers).  Cliccate su Settings e prendere nota della googlecode.com password. Se non vedete settings è perchè non siete loggati su google. Per loggarsi (con le credenziali con cui si accede a gmail), cliccare su Sign in (link in alto a destra)<br>
<br>
<li> nel sito<br>
<a href='http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA'>http://subclipse.tigris.org/servlets/ProjectProcess?pageID=p4wYuA</a><br>
trovate le istruzioni su come installare il plugin SVN per Eclipse<br>
<br>
<li> installato SVN, per inserire l'indirizzo di googlecode da Eclipse aprite<br>
Windows -> Open Perspective -> Other <br>
e selezionate SVN Repository Exploring.Cliccate su Add a new SVN Repository (finetra a sinistra, icona in alto a destra)e inserite la Url:<br>
<a href='https://progettoscienzealberghetti.googlecode.com/svn/trunk'>https://progettoscienzealberghetti.googlecode.com/svn/trunk</a><br>
Dovrebbe chiedervi username e password. Come user mettete il vostro nome gmail nome@gmail.com (solo il nome senza @gmail.com). La password è quella recuperata al punto 1)<br>
<br>
<li> dovreste ora vedere l'albero delle directory di googlecode in SVN Repository<br>
<br>
<li> per tornare alla prospettiva java, di nuovo Window->Open Pespective->Other e poi selezionate Java o Java Browsing<br>
<br>
<li> Per creare il progetto collegato a quello su GoogleCode<br>
<br>
File->New->Other e selezionate<br>
Checkout Projects from SVN<br>
Selezionare il repository SVN<br>
Selezionare la cartella sul repository. Si apre il  Wizard per creare il progetto in locale. Selezionate Checkout a project configured using the New Project Wizard.<br>
A questo punto si apre la finestra del wizard per la creazione di<br>
un nuovo progetto Java. Proseguire normalmente.<br>
<br>
<li> per scaricare l'ultima versione di un progetto (o di una singola classe), fate l'update: in Package Explorer col tasto destro sul nome del progetto <br>
team->update.<br>
<br>
<li> per fare il commit (pubblicare una nuova versione dell'applicazione):in Package Explorer col tasto destro sul nome del progetto (o della singola classe)<br>
team->commit<br>
<br>
<br><br>
<p>
<br>
<br>
<H4><br>
<br>
Creare una nuova directory remota<br>
<br>
Unknown end tag for </h4><br>
<br>
<br>
Per creare una nuova directory sulla pagina source di googlecode, da Eclipse:<br>
Window->OpenPerspective->Other<br>
Selezionate SVN Repository Exploring<br>
Nella finestra a sinistra, la SVN Repository, espandere l'albero delle directory e col tasto destro selezionate <br>
New -> Remote Folder <br>
per creare una nuova sottocartella nella cartella selezionata.