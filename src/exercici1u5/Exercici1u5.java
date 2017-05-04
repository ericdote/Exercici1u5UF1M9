package exercici1u5;

import java.security.KeyPair;

/**
 *
 * @author Eric
 */
public class Exercici1u5 {

    public static void main(String[] args) {
        //Declarem emisor i receptor.
        SignEmissor emi = new SignEmissor();
        SignReceptor recep = new SignReceptor();
        //Array de bytes per emmagatzemar el contingut del document signat.
        byte[] contingut;
        //Declarem el KeyPair per les dos claus.
        KeyPair key;
        //Generem les claus privades i publiques.
        key = emi.generaClaus();
        //Emmagatzemem el contingut del document pero signat.
        contingut = emi.signarDocument("Test.docx", key.getPrivate());
        //Comprovacio de que s'ha validat la firma correctament.
        System.out.println(recep.validarSignatura("Test.docx", contingut, key.getPublic()) ? "Tot correcte" : "No s'ha validad la signa");

    }

}
