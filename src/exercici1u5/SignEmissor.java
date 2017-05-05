package exercici1u5;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eric
 */
public class SignEmissor {

    KeyPair key;

    /**
     * Metode que genera les claus privades i publiques.
     *
     * @return
     */
    public KeyPair generaClaus() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            key = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SignEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }

    /**
     * Metode que li arriba la ruta del fitxer i la clau privada. El metode
     * llegeix el contingut del document i el signa.
     *
     * @param fitxer
     * @param pKey
     * @return
     */
    public byte[] signarDocument(String fitxer, PrivateKey pKey) {
        byte[] sign = null;
        FileInputStream fis;
        BufferedInputStream bis;
        byte[] buffer = new byte[1024];
        int mida;
        try {
            //Inicialitzem el Signature per fer la firma amb el seu algoritme.
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(pKey);
            fis = new FileInputStream(fitxer);
            bis = new BufferedInputStream(fis);

            while (bis.available() != 0) {
                mida = bis.read(buffer);
                signer.update(buffer, 0, mida);
            }
            bis.close();
            //Generem la firma
            sign = signer.sign(); 
        } catch (NoSuchAlgorithmException | InvalidKeyException | IOException | SignatureException ex) {
            Logger.getLogger(SignEmissor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sign;
    }

}
