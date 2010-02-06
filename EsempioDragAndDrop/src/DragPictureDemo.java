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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DragPictureDemo extends JPanel implements ActionListener {

  private static final Icon Icon = null;

DTPicture pic1, pic2, pic3, pic4, pic5, pic6;

 DTPicture pic7;

 DTPicture pic8;

 DTPicture pic9;

 DTPicture pic10;

 DTPicture pic11;

 DTPicture pic12;
  
  static JButton verifica = new JButton ("Verifica");
  
  static String denari1 = "Denari1";

  static String denari2 = "Denari2";

  static String denari3 = "Denari3";

  static String denari4 = "Denari4";

  static String denari5 = "Denari5";
  
  static String denari6 = "Denari6";
 
   DTPicture vett[];
  static String immagini[];

  PictureTransferHandler picHandler;

  public DragPictureDemo() {
    super(new BorderLayout());
    picHandler = new PictureTransferHandler();
    immagini=new String [6];
    immagini[0]=denari1;
    immagini[1]=denari2;
    immagini[2]=denari3;
    immagini[3]=denari4;
    immagini[4]=denari5;
    immagini[5]=denari6;
    
    int v[]=new int[6];
    for(int i=0;i<6;i++)
    	v[i]=i;
    disordina_immagini(v);
    
    JPanel mugshots = new JPanel(new GridLayout(4, 3));
    JPanel button=new JPanel(new GridLayout(1,2));
    verifica.addActionListener(this);
    button.add(verifica);
    
    vett=new DTPicture[6];
    //inizializzo vettore
    vett[0]=new DTPicture(createImageIcon(immagini[0] + ".jpg",immagini[0]).getImage());
    vett[1]=new DTPicture(createImageIcon(immagini[1] + ".jpg",immagini[1]).getImage());
    vett[2]=new DTPicture(createImageIcon(immagini[2] + ".jpg",immagini[2]).getImage());
    vett[3]=new DTPicture(createImageIcon(immagini[3] + ".jpg",immagini[3]).getImage());
    vett[4]=new DTPicture(createImageIcon(immagini[4] + ".jpg",immagini[4]).getImage());
    vett[5]=new DTPicture(createImageIcon(immagini[5] + ".jpg",immagini[5]).getImage());
   //Assegna immagini a contenitori in alto
    
    pic1 = new DTPicture(createImageIcon(immagini[v[0]] + ".jpg",immagini[v[0]]).getImage());
    pic1.setTransferHandler(picHandler);
    mugshots.add(pic1);
    pic2 = new DTPicture(createImageIcon(immagini[v[1]] + ".jpg",immagini[v[1]]).getImage());
    pic2.setTransferHandler(picHandler);
    mugshots.add(pic2);
    pic3 = new DTPicture(createImageIcon(immagini[v[2]] + ".jpg",immagini[v[2]]).getImage());
    pic3.setTransferHandler(picHandler);
    mugshots.add(pic3);
    pic4 = new DTPicture(createImageIcon(immagini[v[3]] + ".jpg",immagini[v[3]]).getImage());
    pic4.setTransferHandler(picHandler);
    mugshots.add(pic4);
    pic5 = new DTPicture(createImageIcon(immagini[v[4]] + ".jpg",immagini[v[4]]).getImage());
    pic5.setTransferHandler(picHandler);
    mugshots.add(pic5);
    pic6 = new DTPicture(createImageIcon(immagini[v[5]] + ".jpg",immagini[v[5]]).getImage());
    pic6.setTransferHandler(picHandler);
    mugshots.add(pic6);
    
    
    
    //These six components with no pictures provide handy
    //drop targets.
    
    pic7 = new DTPicture(null);
    pic7.setTransferHandler(picHandler);
    mugshots.add(pic7);
    pic8 = new DTPicture(null);
    pic8.setTransferHandler(picHandler);
    mugshots.add(pic8);
    pic9 = new DTPicture(null);
    pic9.setTransferHandler(picHandler);
    mugshots.add(pic9);
    pic10 = new DTPicture(null);
    pic10.setTransferHandler(picHandler);
    mugshots.add(pic10);
    pic11 = new DTPicture(null);
    pic11.setTransferHandler(picHandler);
    mugshots.add(pic11);
    pic12 = new DTPicture(null);
    pic12.setTransferHandler(picHandler);
    mugshots.add(pic12);

    setPreferredSize(new Dimension(450, 630));
    add(mugshots, BorderLayout.CENTER);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    add(button, BorderLayout.PAGE_END);
    
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imageURL = DragPictureDemo.class.getResource(path);

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
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new JFrame("DragPictureDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the menu bar and content pane.
    DragPictureDemo demo = new DragPictureDemo();
    demo.setOpaque(true); //content panes must be opaque
    frame.setContentPane(demo);
    
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  public void actionPerformed(ActionEvent arg0) {// dove fare esecuzione
	// TODO Auto-generated method stub
	  //Controllo sistematico di tutte le immagini
	  //restituisce un MsgBox con scritto l'esito dell'ordinamento
		if(pic7.equals(vett[0])){
			if(pic8.equals(vett[1])){
				if(pic9.equals(vett[2])){
					if(pic10.equals(vett[3])){
						if(pic11.equals(vett[4])){
							if(pic12.equals(vett[5])){
								JOptionPane.showMessageDialog(this,
										  "Ordinamento Corretto",
										  "VERIFICA ORDINAMENTO",
										  JOptionPane.INFORMATION_MESSAGE,
										  Icon);
								//System.exit(1);
							}else{
								JOptionPane.showMessageDialog(this,
										  "Ordinamento Errato",
										  "VERIFICA ORDINAMENTO",
										  JOptionPane.INFORMATION_MESSAGE,
										  Icon);
							}
						}else{
							JOptionPane.showMessageDialog(this,
									  "Ordinamento Errato",
									  "VERIFICA ORDINAMENTO",
									  JOptionPane.INFORMATION_MESSAGE,
									  Icon);
						}
					}else{
						JOptionPane.showMessageDialog(this,
								  "Ordinamento Errato",
								  "VERIFICA ORDINAMENTO",
								  JOptionPane.INFORMATION_MESSAGE,
								  Icon);
					}
				}else{
					JOptionPane.showMessageDialog(this,
							  "Ordinamento Errato",
							  "VERIFICA ORDINAMENTO",
							  JOptionPane.INFORMATION_MESSAGE,
							  Icon);
				}
			}else{
				JOptionPane.showMessageDialog(this,
						  "Ordinamento Errato",
						  "VERIFICA ORDINAMENTO",
						  JOptionPane.INFORMATION_MESSAGE,
						  Icon);
			}
		}else{
			JOptionPane.showMessageDialog(this,
					  "Ordinamento Errato",
					  "VERIFICA ORDINAMENTO",
					  JOptionPane.INFORMATION_MESSAGE,
					  Icon);
		}
			
	  
  }
  public static void disordina_immagini(int imma[]){
	  int num=0;
	  int images[]=imma.clone();
	  boolean v[]=new boolean [6];
	  for(int i=0;i<6;i++)
		  v[i]=false;
	  for(int i=0;i<6;i++){
		  do{
			 num=(int)(Math.random()*6); 
		  }while(v[num]);
		  v[num]=true;
		  imma[i]=images[num];
		  //System.out.println("immagini["+i+"] = "+num);
	  }
  }  
}
  
  


  