import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;


/*
 * PictureTransferHandler.java is used by the 1.4 DragPictureDemo.java example.
 */

class PictureTransferHandler extends TransferHandler {
	  DataFlavor pictureFlavor = DataFlavor.imageFlavor;

  DTPicture sourcePic;
  DTPicture picTrash;
  boolean shouldRemove;
  int trasferibile;

  public PictureTransferHandler(int trasferibile){
	  super();
	  this.trasferibile=trasferibile;
	
	  //trasferibile=0 non si può spostare (è il cestino)
	  //trasferibile=1 si può spostare
	  //trasferibile=-1 si può spostare, ma l'immagine originale resta al suo posto
  }
  
  public boolean importData(JComponent c, Transferable t) {
    Image image;
    System.out.println("Metodo importData: ");
    if (canImport(c, t.getTransferDataFlavors())) {
      DTPicture pic = (DTPicture) c;
      //Don't drop on myself.
     
      if (sourcePic == pic) {
    	 // System.out.println("sorgente == destinazione   "+sourcePic.trasfer);
        shouldRemove = false;
        return true;
      }
      
    
      if(pic.image!=null){//se l'immagine destinazione non è vuota
    	  //System.out.println("destinazione non vuota   "+sourcePic.trasfer);
    	  shouldRemove = false;
          return true;
      }
 
      try {
    	  
    		  image = (Image) t.getTransferData(pictureFlavor);
    		  
    		  //Set the component to the new picture.
    		  pic.setImage(image);
    		  return true;
    	 
      } catch (UnsupportedFlavorException ufe) {
        System.out.println("importData: unsupported data flavor");
      } catch (IOException ioe) {
        System.out.println("importData: I/O exception");
      }
    }
    return false;
  }

  protected Transferable createTransferable(JComponent c) {
    sourcePic = (DTPicture) c;
    shouldRemove = true;
    System.out.println("Classe PictureTransferHandler. createTransferable -- sourcePic:"+sourcePic);
    return new PictureTransferable(sourcePic);
  }

  public int getSourceActions(JComponent c) {
    return COPY_OR_MOVE;
  }

  protected void exportDone(JComponent c, Transferable data, int action) {
	  System.out.println("Classe PictureTransferHandler. metodo exportDone");
    if (shouldRemove && (action == MOVE) && (trasferibile==1) ){
    		sourcePic.setImage(null);
    		sourcePic = null;
    }
    
    //sourcePic = null;
  }

  public boolean canImport(JComponent c, DataFlavor[] flavors) {
    for (int i = 0; i < flavors.length; i++) {
      if (pictureFlavor.equals(flavors[i])) {
        return true;
      }
    }
    return false;
  }

 
}