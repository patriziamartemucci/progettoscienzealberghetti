/* From http://java.sun.com/docs/books/tutorial/index.html */

/*
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 * -Redistribution in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * DragPictureDemo.java is a 1.4 example that requires the following files:
 * Picture.java DTPicture.java PictureTransferHandler.java images/Maya.jpg
 * images/Anya.jpg images/Laine.jpg images/Cosmo.jpg images/Adele.jpg
 * images/Alexi.jpg
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class DragPictureDemo extends JPanel implements ActionListener {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 379999346711115098L;
	//Qua ci sono tutti i componenti della barra dei men�, le loro tendine e le loro voci.
	static boolean win=false;
  static JMenuBar mainmenu = new JMenuBar();
  
  static JMenu mgame = new JMenu("Gioco");
  static JMenu mhelp = new JMenu ("?");
  
  static JMenuItem minewgame = new JMenuItem ( "Nuova partita" );
  static JMenuItem miexit = new JMenuItem ( "Esci" );
  static JMenuItem mihints = new JMenuItem ( "Come giocare..." );
  static JMenuItem micredits = new JMenuItem ( "Crediti" );
	
  //Pannelli per scegliere chi muove, cosa e dove disporre le carte.
	
  static JPanel MovePanel  = new JPanel(new GridLayout( 1, 3, 0, 0));
  static JPanel WhatPanel  = new JPanel(new GridLayout( 1, 5, 0, 0));
  static JPanel TablePanel = new JPanel(new GridLayout( 2, 5, 0, 0));
  static JPanel TrashPanel = new JPanel(new GridLayout( 1, 1, 0, 0));
  static JPanel VerifyPanel = new JPanel();
	
  //Etichette da mettere affianco i panel, per dare informazioni.
  
  static JTextArea MoveLabel   = new JTextArea ("Queste tre carte rappresentano chi o che cosa muove le parti del mulino."),
  				   WhatLabel   = new JTextArea ("Queste cinque carte rappresentano le parti del mulino e il suo prodotto."),
  				   MvTblLabel  = new JTextArea ("Sposta in questa fila le prime tre carte mettendole in ordine\n" +
  				   								"(chi agisce prima va pi� a sinistra).\n" +
  				   								"Puoi ripetere la stessa carta pi� volte."),
  				   WtTblLabel  = new JTextArea ("Sposta in questa fila le carte che rappresentano le parti del mulino " + 
  						   						"o il suo prodotto in corrispondenza di chi o che cosa le muove"),
  				   TrashLabel  = new JTextArea ("Sposta una carta qui per liberare il posto che occupa."),
  				   VerifyLabel = new JTextArea ("Quando hai finito, clicca qui per vedere se l'ordine � giusto. Buona fortuna!");
  
  //Variabili globali per condividere fra i componenti le dimensioni del frame.

  static int globalwidth = 0 , globalheight = 0 , panelsVgap = 0;
  
  //Variabile che conta i tentativi rimasti
  
  static byte counter = 5;
  
  //Costanti da utilizzare per il calcolo delle varie dimensioni e coordinate di posizione.
  
  final static int IMG_X = 98 , IMG_Y = 130 , BUT_Y = 50 , TRSH_X = 82 , LBL_X = 250 , panelsHgap = 20;
  
  //Oggetti DTPicture che serviranno nella GUI.
  
  DTPicture moveA, moveB, moveC, whatA, whatB, whatC, whatD, whatE, move1, move2,
            move3, move4, move5, what1, what2, what3, what4, what5, trash;
  
  //Bottone per la verifica.
  
  JButton verifica;

  //Vettori di immagini usati negli algoritmi di mescolamento e disposizione delle carte.
  
  static Image[] vetMove;
  static Image[] vetMovernd;
  static Image[] vetWhat;
  static Image[] vetWhatrnd;
  
  //Tre tipi di handler per tre diversi comportamenti delle carte.
  
  PictureTransferHandler picHandler;
  PictureTransferHandler picHandler2;
  PictureTransferHandler picHandler3;

  //Il costruttore.
  
  public DragPictureDemo() {
	  
	//Nessun LayoutManager.
	  
	setLayout(null);

	//Impostazione degli handler.
	
    picHandler = new PictureTransferHandler(-1);
    picHandler2 = new PictureTransferHandler(1);
    picHandler3 = new PictureTransferHandler(0);
    
    //Attribuzione dei nomi delle immagini di chi muove.
    
    vetMove=new Image[3];
    vetMovernd=new Image[3];
    vetMove[0]=createImageIcon("muove1.jpg", "muove1").getImage();
    vetMove[1]=createImageIcon("muove2.jpg", "muove2").getImage();
    vetMove[2]=createImageIcon("muove3.jpg", "muove3").getImage();
    
    //Attribuzione dei nomi delle immagini di cosa � mosso.
    
    vetWhat=new Image[5];
    vetWhatrnd=new Image[5];
    vetWhat[0]=createImageIcon("cosa1.jpg", "cosa1").getImage();
    vetWhat[1]=createImageIcon("cosa2.jpg", "cosa2").getImage();
    vetWhat[2]=createImageIcon("cosa3.jpg", "cosa3").getImage();
    vetWhat[3]=createImageIcon("cosa4.jpg", "cosa4").getImage();
    vetWhat[4]=createImageIcon("cosa5.jpg", "cosa5").getImage();
    
    //Randomizzazione per chi muove.
    
    for(int i=0;i<3;i++)
    	vetMovernd[i]=vetMove[i];
    
    for(int i=0;i<100;i++){
    	int x=(int)(Math.random()*3);
    	int y=(int)(Math.random()*3);
    	Image temp=vetMovernd[x];
    	vetMovernd[x]=vetMovernd[y];
    	vetMovernd[y]=temp;
    }
    
    //Randomizzazione per cosa � mosso.
    
    for(int i=0;i<5;i++)
    	vetWhatrnd[i]=vetWhat[i];
    
    for(int i=0;i<100;i++){
    	int x=(int)(Math.random()*5);
    	int y=(int)(Math.random()*5);
    	Image temp=vetWhatrnd[x];
    	vetWhatrnd[x]=vetWhatrnd[y];
    	vetWhatrnd[y]=temp;
    }
    
    //Le tre immagini di chi muove.
    
    moveA = new DTPicture(vetMovernd[0],true);
    moveA.setTransferHandler(picHandler);
    MovePanel.add(moveA);
    
    moveB = new DTPicture(vetMovernd[1],true);
    moveB.setTransferHandler(picHandler);
    MovePanel.add(moveB);
    
    moveC = new DTPicture(vetMovernd[2],true);
    moveC.setTransferHandler(picHandler);
    MovePanel.add(moveC);
    
    //Le cinque immagini di cosa viene mosso.
    
    whatA = new DTPicture(vetWhatrnd[0],true);
    whatA.setTransferHandler(picHandler);
    WhatPanel.add(whatA);
    
    whatB = new DTPicture(vetWhatrnd[1],true);
    whatB.setTransferHandler(picHandler);
    WhatPanel.add(whatB);
    
    whatC = new DTPicture(vetWhatrnd[2],true);
    whatC.setTransferHandler(picHandler);
    WhatPanel.add(whatC);
    
    whatD = new DTPicture(vetWhatrnd[3],true);
    whatD.setTransferHandler(picHandler);
    WhatPanel.add(whatD);
   
    whatE = new DTPicture(vetWhatrnd[4],true);
    whatE.setTransferHandler(picHandler);
    WhatPanel.add(whatE);
    
    //I dieci contenitori dove disporre le immagini.
    //ho racchiuso in un metodo l'inizializzazione -- Patrizia
    inizializzaCaselleVuote();
        
    trash = new DTPicture(createImageIcon("trash.jpg", "trash").getImage(),false);
    trash.setTransferHandler(picHandler3); 
    TrashPanel.add(trash);
    
    //Bottone per la verifica.
    
    verifica = new JButton("Verifica");
    verifica.addActionListener(this);
    VerifyPanel.add(verifica);
    
    //Impostazioni fisse delle etichette.
    //"A capo" automatico.
    
    MoveLabel.setLineWrap(true);
    WhatLabel.setLineWrap(true);
    MvTblLabel.setLineWrap(true);
    WtTblLabel.setLineWrap(true);
    TrashLabel.setLineWrap(true);
    VerifyLabel.setLineWrap(true);
    
    //"A capo" solo tra gli spazi.
    
    MoveLabel.setWrapStyleWord(true);
    WhatLabel.setWrapStyleWord(true);
    MvTblLabel.setWrapStyleWord(true);
    WtTblLabel.setWrapStyleWord(true);
    TrashLabel.setWrapStyleWord(true);
    VerifyLabel.setWrapStyleWord(true);

    //Non opache.
    
    MoveLabel.setOpaque(false);
    WhatLabel.setOpaque(false);
    MvTblLabel.setOpaque(false);
    WtTblLabel.setOpaque(false);
    TrashLabel.setOpaque(false);
    VerifyLabel.setOpaque(false);
    
    //Non editabile.
    
    MoveLabel.setEditable(false);
    WhatLabel.setEditable(false);
    MvTblLabel.setEditable(false);
    WtTblLabel.setEditable(false);
    TrashLabel.setEditable(false);
    VerifyLabel.setEditable(false);
    
    //Imposta tutti i jpanel sul jpanel principale.
    
    jpanelsUpdate();

    //Costruisce la barra dei menu.

    mgame.add( minewgame );//ho tolto il commento- Patrizia
    minewgame.addActionListener(this);//aggiunto -Patrizia
    
    mgame.addSeparator();
   
    mgame.add( miexit );
    miexit.addActionListener(this);//modificato-Patrizia
    mainmenu.add( mgame );
    
    mihints.addActionListener(this);//modificato-Patrizia
    	
    mhelp.add( mihints );
    
    micredits.addActionListener(this);//modificato-Patrizia
    	
    mhelp.add( micredits );
    mainmenu.add( mhelp );
    
    //Aggiunge le label.

    add(MoveLabel);
    add(WhatLabel);
    add(MvTblLabel);
    add(WtTblLabel);
    add(TrashLabel);
    add(VerifyLabel);

    //Aggiunge tutti i jpanel sul jpanel principale.
    
    add(MovePanel);
    add(WhatPanel);
    add(TablePanel);
    add(TrashPanel);
    add(VerifyPanel);

  }
    
  //metodo per creare le caselle vuote (inizialmente) -- Patrizia
  
  private void inizializzaCaselleVuote(){
	  	
	    move1 = new DTPicture(null,true);
	    move1.setTransferHandler(picHandler2);
	    TablePanel.add(move1);
	    
	    move2 = new DTPicture(null,true);
	    move2.setTransferHandler(picHandler2);
	    TablePanel.add(move2);
	    
	    move3 = new DTPicture(null,true);
	    move3.setTransferHandler(picHandler2);
	    TablePanel.add(move3);
	    
	    move4 = new DTPicture(null,true);
	    move4.setTransferHandler(picHandler2);
	    TablePanel.add(move4);
	    
	    move5 = new DTPicture(null,true);
	    move5.setTransferHandler(picHandler2);
	    TablePanel.add(move5);
	    
	    what1 = new DTPicture(null,true);
	    what1.setTransferHandler(picHandler2);
	    TablePanel.add(what1);
	    
	    what2 = new DTPicture(null,true);
	    what2.setTransferHandler(picHandler2);
	    TablePanel.add(what2);
	    
	    what3 = new DTPicture(null,true);
	    what3.setTransferHandler(picHandler2);
	    TablePanel.add(what3);
	    
	    what4 = new DTPicture(null,true);
	    what4.setTransferHandler(picHandler2);
	    TablePanel.add(what4);
	    
	    what5 = new DTPicture(null,true);
	    what5.setTransferHandler(picHandler2);
	    TablePanel.add(what5);
  }
  
  //per azzerare le caselle bianche -- Patrizia
  
  private void impostaCaselleVuote(){
	  
	  move1.setImage(null);
	  move2.setImage(null);
	  move3.setImage(null);
	  move4.setImage(null);
	  move5.setImage(null);
	  what1.setImage(null);
	  what2.setImage(null);
	  what3.setImage(null);
	  what4.setImage(null);
	  what5.setImage(null);
	  
  }
  
  //Returns an ImageIcon, or null if the path was invalid.
  
  protected static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imageURL = DragPictureDemo.class.getResource(path);
    //System.out.println("metodo createImageIcon.  Path: "+path+"  --  description: "+description);
    if (imageURL == null) {
      System.err.println("Resource not found: " + path);
      return null;
    } else {
      return new ImageIcon(imageURL, description);
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  
  private static void createAndShowGUI() {

	//Ripristino i tentativi.
	  
	counter = 5;
	  
    //Crea il frame, toglie il LayoutManager e abilit� il tasto X per chiudere la finestra.
	//Dichiarato final perch� se no il listener rompe.
	  
    final JFrame frame = new JFrame("Gioco delle carte di chi muove cosa.");
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Crea una nuova istanza della GUI, la imposta come pannello del contenuto del frame
    //e aggiunge la barra dei menu.
    
    DragPictureDemo mainpanel = new DragPictureDemo();
    mainpanel.setOpaque(true);
    frame.setContentPane( mainpanel );
    
    frame.setJMenuBar( mainmenu );

    //Impostazioni del frame per visualizzarlo bene.
    
    frame.setSize( globalwidth , globalheight );
    frame.setVisible(true);
    
    //Aggiunta del listener per captare l'evento resize, aggiornare le due variabili globali
    //delle dimensioni e adattare di conseguenza i panelli al suo interno.
    
    frame.addComponentListener(new ComponentAdapter() {
    	
    	  public void componentResized(ComponentEvent event) {
    		  
    		  //Aggiornamento delle variabili globali.
    		  //Se la nuova dimensione � minore di quella minima utile per disegnare il tutto,
    		  //allora viene ripristinata quella minima.
    		  
    		  globalwidth = Math.max( (int)frame.getSize().getWidth() , IMG_X*5 + LBL_X + 60 );
    		  globalheight = Math.max( (int)frame.getSize().getHeight() , IMG_Y*4 + IMG_X + BUT_Y*2 + 80 );
    		  
    		  //Aggiornamento delle dimensioni del frame.
    		  
    		  frame.setSize( globalwidth , globalheight );
    		  
    		  //Chiamata alla funzione che ha come compito di adattare tutti i JPanel.
    		  
    		  jpanelsUpdate();
    		  
    		  //Ridisegno del frame intero.
    		  
    		  frame.setVisible(true);
    		  
    	  }
    });
  }
  public void actionPerformed ( ActionEvent e ) {
	  
	if(e.getActionCommand().equals("Verifica")){
		
		//Controlla se ci sono tessere vuote.
		
		if ( move1.image != null && 
			 move2.image != null && 
			 move3.image != null && 
			 move4.image != null && 
			 move5.image != null && 
			 what1.image != null && 
			 what2.image != null && 
			 what3.image != null &&
			 what4.image != null && 
			 what5.image != null ){
		
			//Se sono tutte piene, controlla che ci siano ancora tentativi disponibili.
			
			if ( counter > 0 ){
		
				//Se ancora va tutto bene, allora controlla che le tessere siano nell'ordine giusto.
				//Se lo sono, comunica la vittoria e i tentativi necessari,
				//altrimenti toglie un tentativo ( variabile globale counter ) e invita a ritentare.
				
				if( move1.image.getSource().equals(vetMove[1].getSource()) &&
					move2.image.getSource().equals(vetMove[0].getSource()) &&
					move3.image.getSource().equals(vetMove[2].getSource()) &&
					move4.image.getSource().equals(vetMove[2].getSource()) &&
					move5.image.getSource().equals(vetMove[1].getSource()) &&
					what1.image.getSource().equals(vetWhat[1].getSource()) &&
					what2.image.getSource().equals(vetWhat[2].getSource()) &&
					what3.image.getSource().equals(vetWhat[3].getSource()) &&
					what4.image.getSource().equals(vetWhat[0].getSource()) &&
					what5.image.getSource().equals(vetWhat[4].getSource()) ){
					
					JOptionPane.showMessageDialog( null, "Hai vinto!!! Ce l'hai fatta in " +
														 (6 - counter) + " tentativ" +
														 ( (6 - counter) == 1 ? 'o' : 'i' ) + '.' );
					verifica.setEnabled(false);											//aggiunto -- Patrizia
					Frame f= new Frame (" HAI VINTO ");
					f.setSize(500,400);
					
					Win pp= new Win(50);
					pp.setBackground(Color.white);
					f.add(pp);
					f.setVisible(true);
					JOptionPane.showMessageDialog(null,"Sei davvero un esperto di mulini ad acqua e del loro funzionamento!!",
							"HAI VINTO!!!",JOptionPane.INFORMATION_MESSAGE);
					/*try{
						Thread.sleep((long)5000);
					}catch(Exception e1){
						System.err.println(e1);
					}*/
					
					f.setVisible(false);
					counter=5;								   //aggiunto. In caso di vittoria azzero il contatore -- Patrizia
				}
				else
				{
					
					counter--;
					if(counter==0){																	//aggiunto -- Patrizia
						
						JOptionPane.showMessageDialog( null, "Tentativi esauriti. Hai perso!" ,"GAME OVER",JOptionPane.ERROR_MESSAGE);
						verifica.setEnabled(false);													//aggiunto -- Patrizia
					
					}
					else
					{
						
					JOptionPane.showMessageDialog( null, "Sbagliato. Ti rimangono ancora " + counter +
														 " tentativ" + ( ( counter == 1 ) ? 'o' : 'i' ) + " : riprova!\n\n" +
														 "Le tessere al posto giusto sono la :" +
														 ( ( move1.image.getSource().equals(vetMove[1].getSource()) ) ? "\n\t_Prima" : "" ) +
														 ( ( move2.image.getSource().equals(vetMove[0].getSource()) ) ? "\n\t_Seconda" : "" ) +
														 ( ( move3.image.getSource().equals(vetMove[2].getSource()) ) ? "\n\t_Terza" : "" ) +
														 ( ( move4.image.getSource().equals(vetMove[2].getSource()) ) ? "\n\t_Quarta" : "" ) +
														 ( ( move5.image.getSource().equals(vetMove[1].getSource()) ) ? "\n\t_Quinta" : "" ) +
														 ( ( what1.image.getSource().equals(vetWhat[1].getSource()) ) ? "\n\t_Sesta" : "" ) +
														 ( ( what2.image.getSource().equals(vetWhat[2].getSource()) ) ? "\n\t_Settima" : "" ) +
														 ( ( what3.image.getSource().equals(vetWhat[3].getSource()) ) ? "\n\t_Ottava" : "" ) +
														 ( ( what4.image.getSource().equals(vetWhat[0].getSource()) ) ? "\n\t_Nona" : "" ) +
														 ( ( what5.image.getSource().equals(vetWhat[4].getSource()) ) ? "\n\t_Decima" : "" ),null,JOptionPane.INFORMATION_MESSAGE );
					
					}
				}

			//Se non ci sono pi� tentativi a disposizione, comunica la sconfitta.
				
			}
			else
			{
				
				JOptionPane.showMessageDialog( null, "Tentativi esauriti. Hai perso!" ,"GAME OVER",JOptionPane.ERROR_MESSAGE);
				verifica.setEnabled(false);													//aggiunto -- Patrizia
			
			}

		//Se ci sono tessere vuote, chiede di ricontrollarle.
			
		}
		else
		{	
			
			counter--;																		//aggiunto --Patrizia
			if(counter==0){																	//aggiunto -- Patrizia
				
				JOptionPane.showMessageDialog( null, "Tentativi esauriti. Hai perso!" ,"GAME OVER",JOptionPane.ERROR_MESSAGE);
				verifica.setEnabled(false);													//aggiunto -- Patrizia
			
			}
			else
			{
				
				JOptionPane.showMessageDialog( null, "Hai dimenticato delle tessere, ricontrolla! Ti rimangono ancora " + counter +
													 " tentativ" + ( ( counter == 1 ) ? 'o' : 'i' ) + " : riprova!" ,"Attenzione",JOptionPane.WARNING_MESSAGE);
	
			}
			
		}
	}
	
	//Gli if seguenti servono per gestire le voci del menu. -- Patrizia
	
	if (e.getSource()==minewgame){
		
		//System.out.println("Hai premuto \"Nuova partita\" ");
		impostaCaselleVuote();
		counter = 5;
		verifica.setEnabled(true);
		jpanelsUpdate();
		
	}
	if(e.getSource()==miexit){
		System.exit(0);
	}
	if(e.getSource()==mihints){
		JOptionPane.showMessageDialog( null, "Nelle caselle bianche dovete mettere le varie figure secondo queste regole:\n" +
				 							 "_l'ordine cronologico va da sinistra verso destra;\n" +
				 							 "_chi muove va nella fila sopra mentre chi � mosso va sotto di esso;\n" +
				 							 "_la sequenza esatta rappresenta il ciclo di azioni che si deve fare per ottenere" +
				 							 " la farina dal grano usando un mulino ad acqua." );
	}
	if(e.getSource()==micredits){
		JOptionPane.showMessageDialog( null, "Sviluppato da:\n\nprof. Martemucci Patrizia\nLuca Biavati\nValgimigli Filip\n\n" +
				 							 "\n\nCopyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.",null,JOptionPane.INFORMATION_MESSAGE);
	}
  }

  public static void jpanelsUpdate(){
	  
	  //Calcola la spaziatura verticale tra i pannelli.
	  
	  panelsVgap = ( globalheight - IMG_Y*5 - BUT_Y )/7 ;
	  
	  //Reimposta le dimensioni delle etichette.
	  
	  MoveLabel.setSize( LBL_X , IMG_Y );
	  WhatLabel.setSize( LBL_X , IMG_Y );
	  MvTblLabel.setSize( LBL_X , IMG_Y );
	  WtTblLabel.setSize( LBL_X , IMG_Y );
	  TrashLabel.setSize( LBL_X , IMG_X );
	  VerifyLabel.setSize( LBL_X , BUT_Y );
	  
	  //Reimpostazione delle dimensioni di ogni pannello.
	  //Da notare che l'altezza dell'icone del cestino �
	  //la stessa misura della larghezza delle altre icone
	  //quindi uso la costante IMG_X.
	  
	  MovePanel.setSize( globalwidth - LBL_X - panelsHgap*3 , IMG_Y );
	  WhatPanel.setSize( globalwidth - LBL_X - panelsHgap*3 , IMG_Y );
	  TablePanel.setSize( globalwidth - LBL_X - panelsHgap*3 , IMG_Y*2 + panelsVgap );
	  TrashPanel.setSize( IMG_X , IMG_X );
	  VerifyPanel.setSize( globalwidth - LBL_X - panelsHgap*3 , BUT_Y );

	  //Reimposta le posizioni delle etichette.
	  
	  MoveLabel.setLocation( panelsHgap , panelsVgap );
	  WhatLabel.setLocation( panelsHgap , IMG_Y + panelsVgap*2 );
	  MvTblLabel.setLocation( panelsHgap , IMG_Y*2 + panelsVgap*3  );
	  WtTblLabel.setLocation( panelsHgap , IMG_Y*3 + panelsVgap*4 );
	  TrashLabel.setLocation( panelsHgap , IMG_Y*4 + panelsVgap*5 );
	  VerifyLabel.setLocation( panelsHgap , IMG_Y*4 + IMG_X + panelsVgap*6 );
	  
	  //Reimpostazione delle posizioni di ogni pannello.
	  
	  MovePanel.setLocation( LBL_X + panelsHgap*2 , panelsVgap );
	  WhatPanel.setLocation( LBL_X + panelsHgap*2 , IMG_Y + panelsVgap*2 );
	  TablePanel.setLocation( LBL_X + panelsHgap*2 , IMG_Y*2 + panelsVgap*3 );
	  TrashPanel.setLocation( ( LBL_X + panelsHgap + globalwidth )/2 - TRSH_X/2 , IMG_Y*4 + panelsVgap*5 );
	  VerifyPanel.setLocation( LBL_X + panelsHgap*2 , IMG_Y*4 + IMG_X + panelsVgap*6 );
	  
  }
  
  public static void main(String[] args) {
	
	//Dimensioni iniziali del frame.  
	  
	globalwidth = IMG_X*5 + LBL_X;
	globalheight = IMG_Y*4 + IMG_X + BUT_Y*2 + panelsVgap*7;
	
    /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
      
    	public void run() {
    		*/
	createAndShowGUI();
	/*
    	}
    });*/
  }
}

  