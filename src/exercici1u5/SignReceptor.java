package exercici1u5;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric
 */
public class SignReceptor {

    /**
     * Metode que li arriba una ruta del fitxer, un array de bytes amb el
     * contingut d'aquest pero signat i la clau publica. Llegim el contingut del
     * fitxer i amb Signature.verify comprovem que la firma ha sigut valida.
     *
     * @param fitxer
     * @param signature
     * @param pub
     * @return
     */
    public boolean validarSignatura(String fitxer, byte[] signature, PublicKey pub) {
        boolean isValid = false;
        FileInputStream fis;
        BufferedInputStream bis;
        byte[] buffer = new byte[1024];
        int mida;
        try {
            fis = new FileInputStream(fitxer);
            bis = new BufferedInputStream(fis);
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initVerify(pub);
            while (bis.available() != 0) {
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida);
            }
            bis.close();
            isValid = signer.verify(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | FileNotFoundException ex) {
            Logger.getLogger(SignReceptor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SignReceptor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isValid;
    }

}
