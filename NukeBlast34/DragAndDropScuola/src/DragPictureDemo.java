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
/*
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
*/
import java.awt.event.*;
/*
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
*/
import javax.swing.*;
/*
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
*/

public class DragPictureDemo extends JPanel implements ActionListener{

    //Pannelli per scegliere chi muove, cosa e dove disporre le carte.
	
	static JPanel MovePanel  = new JPanel(new GridLayout( 1, 3, 0, 0));
    static JPanel WhatPanel  = new JPanel(new GridLayout( 1, 5, 0, 0));
    static JPanel TablePanel = new JPanel(new GridLayout( 2, 5, 0, 0));
    static JPanel TrashPanel = new JPanel(new GridLayout( 1, 1, 0, 0));
    static JPanel VerifyPanel = new JPanel();
	
    static int globalwidth = 0 , globalheight = 0;	//Variabili globali per condividere fra i componenti le dimensioni del frame.
	
    DTPicture moveA, moveB, moveC, whatA, whatB, whatC, whatD, whatE, move1, move2,
              move3, move4, move5, what1, what2, what3, what4, what5, trash;

  /*  
  static String denari1 = "Denari1";

  static String denari2 = "Denari2";

  static String denari3 = "Denari3";

  static String denari4 = "Denari4";

  static String denari5 = "Denari5";

  static String denari6 = "Denari6";
  
  static String trash = "trash";
  */

  static Image[] vetMove;
  static Image[] vetMovernd;
  static Image[] vetWhat;
  static Image[] vetWhatrnd;
  
  PictureTransferHandler picHandler;
  PictureTransferHandler picHandler2;
  PictureTransferHandler picHandler3;
  
  GridBagConstraints c = new GridBagConstraints();
  
  public DragPictureDemo() {
	  
    //super(new GridLayout(5,1));
    //super(new GridBagLayout());
	//setLayout(new GridLayout(5,1,0,0));
	setLayout(null);
	
    //System.out.println("Costruttore di DragPictureDemo");
    
    picHandler = new PictureTransferHandler(-1);
    picHandler2 = new PictureTransferHandler(1);
    picHandler3 = new PictureTransferHandler(0);
    
    //Attribuzione dei nomi delle immagini di chi muove.
    
    vetMove=new Image[3];
    vetMovernd=new Image[3];
    vetMove[0]=createImageIcon("muove1.jpg", "muove1").getImage();
    vetMove[1]=createImageIcon("muove2.jpg", "muove2").getImage();
    vetMove[2]=createImageIcon("muove3.jpg", "muove3").getImage();
    
    //Attribuzione dei nomi delle immagini di cosa è mosso.
    
    vetWhat=new Image[5];
    vetWhatrnd=new Image[5];
    vetWhat[0]=createImageIcon("cosa1.jpg", "cosa1").getImage();
    vetWhat[1]=createImageIcon("cosa2.jpg", "cosa2").getImage();
    vetWhat[2]=createImageIcon("cosa3.jpg", "cosa3").getImage();
    vetWhat[3]=createImageIcon("cosa4.jpg", "cosa4").getImage();
    vetWhat[4]=createImageIcon("cosa5.jpg", "cosa5").getImage();
    
    //Randomizzazione per chi muove.
    
    for(int i=0;i<3;i++) vetMovernd[i]=vetMove[i];
    for(int i=0;i<100;i++){
    	int x=(int)(Math.random()*3);
    	int y=(int)(Math.random()*3);
    	Image temp=vetMovernd[x];
    	vetMovernd[x]=vetMovernd[y];
    	vetMovernd[y]=temp;
    }
    
    //Randomizzazione per cosa è mosso.
    
    for(int i=0;i<5;i++) vetWhatrnd[i]=vetWhat[i];
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
    
    trash = new DTPicture(createImageIcon("trash.jpg", "trash").getImage(),false);
    trash.setTransferHandler(picHandler3); 
    TrashPanel.add(trash);
    
    //Bottone per la verifica.
    
    JButton verifica = new JButton("Verifica");
    verifica.addActionListener(this);
    VerifyPanel.add(verifica);
    
    //Dimensioni Finestra intera.
    //setPreferredSize(new Dimension(1000, 600));
    
    //add(mugshots, BorderLayout.CENTER);
    //add(pannelloBottone,BorderLayout.AFTER_LAST_LINE);
    
    //c.gridheight = 1;
	//c.gridwidth = 1;

    //add(MovePanel, 1, 1);
    //add(WhatPanel,2,2);
    //add(TablePanel,3,3);
    //add(trash,4,4);
    //add(verifica,5,5);
    
    //MovePanel.setSize( globalwidth , 140);
    add(MovePanel);
    //WhatPanel.setSize( globalwidth , 140);
    WhatPanel.setLocation( 0 , 180 );
    add(WhatPanel);
    //TablePanel.setSize( globalwidth , 280 );
    TablePanel.setLocation( 0 , 360 );
    add(TablePanel);
    //TrashPanel.setSize( globalwidth , 140 );
    TrashPanel.setLocation( 0 , 600 );
    add(TrashPanel);
    //VerifyPanel.setSize( globalwidth , 50 );
    VerifyPanel.setLocation( 0 , 760 );
    add(VerifyPanel);
    
    //setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }
  
  public void add(Component comp, int x, int y){
	  c.gridx = x;
	  c.gridy = y;
	  add(comp, c);
  }

  //Returns an ImageIcon, or null if the path was invalid.
  
  protected static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imageURL = DragPictureDemo.class.getResource(path);
    System.out.println("metodo createImageIcon.  Path: "+path+"  --  description: "+description);
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
    //Make sure we have nice window decorations.
	//System.out.println("Metodo createAndShow");
    
	//JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    final JFrame frame = new JFrame("Gioco delle carte di chi muove cosa."); //Dichiarata final perchè se no il listener rompe.
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //Create and set up the menu bar and content pane.
    DragPictureDemo mainpanel = new DragPictureDemo();
    mainpanel.setOpaque(true); //content panes must be opaque
    frame.setContentPane(mainpanel);
    

    //Display the window.
    //frame.pack();
    frame.setSize( globalwidth , globalheight );
    frame.setVisible(true);
    frame.addComponentListener(new ComponentAdapter() {				//Qui aggiungo il listener
    	  public void componentResized(ComponentEvent event) {		//per controllare il ridimensionamento.
    		  globalwidth = (int)frame.getSize().getWidth();
    		  globalheight = (int)frame.getSize().getHeight();
    		  System.out.println(globalwidth + " x " + globalheight);
    		  jpanelsUpdate();
    	  }
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getActionCommand().equals("Verifica")){
		//System.out.println("Hai cliccato sul pulsante verifica");
		//System.out.println("pic1.image.getsource: "+moveA.image);

		int conta=0;
/*		for(int i=0;i<6;i++){
			switch (i){
				case 0:
					if(whatD.image!=null && vetImage[0].getSource().equals(pic7.image.getSource()))conta++;
					break;
					
				case 1:
					if(whatE.image!=null && vetImage[1].getSource().equals(pic8.image.getSource()))conta++;
					break;
					
				case 2:
					if(move1.image!=null && vetImage[2].getSource().equals(pic9.image.getSource()))conta++;
					break;
					
				case 3:
					if(move2.image!=null && vetImage[3].getSource().equals(pic10.image.getSource()))conta++;
					break;
					
				case 4:
					if(move3.image!=null && vetImage[4].getSource().equals(pic11.image.getSource()))conta++;
					break;
					
				case 5:
					if(move4.image!=null && vetImage[5].getSource().equals(pic12.image.getSource()))conta++;
			}
			
		}
*/
		if(conta==6){
			System.out.println("Tutto OK!!!!");
			JOptionPane.showMessageDialog(null,"Hai vinto!!! ");
		}
		else{
			System.out.println("Riprova!");
			JOptionPane.showMessageDialog(null,"Hai perso!!! ");
		}
	}
  }

  public static void jpanelsUpdate(){
	  
	  System.out.println("Repaint!!!");
	  
	  MovePanel.setSize( globalwidth , 140 );
	  WhatPanel.setSize( globalwidth , 140 );
	  TablePanel.setSize( globalwidth , 140 );
	  TrashPanel.setSize( globalwidth , 140 );
	  VerifyPanel.setSize( globalwidth , 140 );

	  MovePanel.repaint();
	  WhatPanel.repaint();
	  TablePanel.repaint();
	  TrashPanel.repaint();
	  VerifyPanel.repaint();
	   
  }
  
  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
	
	globalwidth = 800;
	globalheight = 600;
	
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}

  