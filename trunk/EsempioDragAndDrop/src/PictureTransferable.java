import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

class PictureTransferable implements Transferable {
    private Image image;
    DataFlavor pictureFlavor;
    //
    PictureTransferable(DTPicture pic) {
      image = pic.image;
    }

    public Object getTransferData(DataFlavor flavor)
        throws UnsupportedFlavorException {
      if (!isDataFlavorSupported(flavor)) {
        throw new UnsupportedFlavorException(flavor);
      }
      return image;
    }

    public DataFlavor[] getTransferDataFlavors() {
    	pictureFlavor= DataFlavor.imageFlavor;
	return new DataFlavor[] { pictureFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
      return pictureFlavor.equals(flavor);
    }
  }