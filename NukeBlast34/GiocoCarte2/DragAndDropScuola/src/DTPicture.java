import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;

/*
 * DTPicture.java is used by the 1.4 DragPictureDemo.java example.
 */

//A subclass of Picture that supports Data Transfer.

class DTPicture extends Picture implements MouseMotionListener {
  
  private MouseEvent firstMouseEvent = null;

  private static boolean installInputMapBindings = true;
  
  public boolean trasfer;

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

  public DTPicture(Image image, boolean trasferibile) {

	super(image);
    System.out.println("Classe DTPicture. Costruttore -- image: "+image);
    addMouseMotionListener(this);
    System.out.println("Classe DTPicture. Metodo Costruttore ");
    this.trasfer=trasferibile;
    //Add the cut/copy/paste key bindings to the input map.
    //Note that this step is redundant if you are installing
    //menu accelerators that cause these actions to be invoked.
    //DragPictureDemo does not use menu accelerators and, since
    //the default value of installInputMapBindings is true,
    //the bindings are installed. DragPictureDemo2 does use
    //menu accelerators and so calls setInstallInputMapBindings
    //with a value of false. Your program would do one or the
    //other, but not both.
    
    /*
      il codice seguente serve per il taglia-copia-incolla con tastiera
    
    if (installInputMapBindings) {
      InputMap imap = this.getInputMap();
      imap.put(KeyStroke.getKeyStroke("ctrl X"), TransferHandler
          .getCutAction().getValue(Action.NAME));
      imap.put(KeyStroke.getKeyStroke("ctrl C"), TransferHandler
          .getCopyAction().getValue(Action.NAME));
      imap.put(KeyStroke.getKeyStroke("ctrl V"), TransferHandler
          .getPasteAction().getValue(Action.NAME));
    }

    //Add the cut/copy/paste actions to the action map.
    //This step is necessary because the menu's action listener
    //looks for these actions to fire.
    ActionMap map = this.getActionMap();
    map.put(TransferHandler.getCutAction().getValue(Action.NAME),
        TransferHandler.getCutAction());
    map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
        TransferHandler.getCopyAction());
    map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
        TransferHandler.getPasteAction());
        
      */
    if (installInputMapBindings) {
        InputMap imap = this.getInputMap();
        //imap.put(KeyStroke.getKeyStroke("ctrl X"), TransferHandler
        //    .getCutAction().getValue(Action.NAME));
        imap.put(KeyStroke.getKeyStroke("ctrl C"), TransferHandler
            .getCopyAction().getValue(Action.NAME));
        imap.put(KeyStroke.getKeyStroke("ctrl V"), TransferHandler
            .getPasteAction().getValue(Action.NAME));
      }
    
    //Add the cut/copy/paste actions to the action map.
    //This step is necessary because the menu's action listener
    //looks for these actions to fire.
    ActionMap map = this.getActionMap();
    //map.put(TransferHandler.getCutAction().getValue(Action.NAME),
    //    TransferHandler.getCutAction());
    map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
        TransferHandler.getCopyAction());
    map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
        TransferHandler.getPasteAction());
  }

  public void setImage(Image image) {
		  this.image = image;
		   this.repaint();
	
  }

  public void mousePressed(MouseEvent e) {
    //Don't bother to drag if there is no image.
    if (image == null)
      return;
    
    firstMouseEvent = e;
    e.consume();
    
  }

  public void mouseDragged(MouseEvent e) {
    //Don't bother to drag if the component displays no image.
    if (image == null)
      return;
   
    if (firstMouseEvent != null) {
      e.consume();

      //If they are holding down the control key, COPY rather than MOVE
      int ctrlMask = InputEvent.CTRL_DOWN_MASK;
      int action = ((e.getModifiersEx() & ctrlMask) == ctrlMask) ? TransferHandler.COPY
          : TransferHandler.MOVE;

      int dx = Math.abs(e.getX() - firstMouseEvent.getX());
      int dy = Math.abs(e.getY() - firstMouseEvent.getY());
      
      //Arbitrarily define a 5-pixel shift as the
      //official beginning of a drag.
      
      //---------------------------------------------------------------------------------------------\
      //Qui ci vuole un commento italiano.															 |
      //In questo if vado a controllare se per caso l'immagine non corrisponde a quella del cestino. |
      //Se NON corrisponde, vado a controlare se è un drag o un click.								 |
      //---------------------------------------------------------------------------------------------/
      
      if( !this.image.equals( createImageIcon("trash.jpg", "trash").getImage() ) ){
    	  
    	  if (dx > 5 || dy > 5) {
    		  
    		  //This is a drag, not a click.
    		  
    		  JComponent c = (JComponent) e.getSource();
    		  System.out.println("Classe DTPicuture.  metodo mouseDragged -- e.getSource: "+e.getSource());
    		  TransferHandler handler = c.getTransferHandler();
    		  
    		  //Tell the transfer handler to initiate the drag.
    		  
    		  handler.exportAsDrag(c, firstMouseEvent, action);
    		  firstMouseEvent = null;
    		  
    	  }
    	  
      }
    }
    
  }

  public void mouseReleased(MouseEvent e) {
    firstMouseEvent = null;
  }

  public void mouseMoved(MouseEvent e) {
  }

  //This method is necessary because DragPictureDemo and
  //DragPictureDemo2 both use this class and DragPictureDemo
  //needs to have the input map bindings installed for
  //cut/copy/paste. DragPictureDemo2 uses menu accelerators
  //and does not need to have the input map bindings installed.
  //Your program would use one approach or the other, but not
  //both. The default for installInputMapBindings is true.
  public static void setInstallInputMapBindings(boolean flag) {
    installInputMapBindings = flag;
  }

  public static boolean getInstallInputMapBindingds() { //for completeness
    return installInputMapBindings;
  }
}




           
         
