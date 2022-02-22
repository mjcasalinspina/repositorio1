/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proyecto de ejemplo para generar una pareja de claves (privada y pública) y firmar datos
 * @author profesoro
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Creamos una instancia de Signature
            Signature signature = Signature.getInstance("SHA256WithDSA");
            
            // Inicializamos la instancia de Signature mediante un número aleatorio seguro
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            signature.initSign(keyPair.getPrivate(), secureRandom);
            
            // Creamos una firma de un texto
            byte[] data = "En un lugar de la mancha...".getBytes("UTF-8");
            signature.update(data);
            byte[] digitalSignature = signature.sign();
            
            
            // Verificamos la firma digital
            Signature signatureVerify = Signature.getInstance("SHA256WithDSA");
            signatureVerify.initVerify(keyPair.getPublic());
            
            // Para verificar necesitamos el mensaje original que se firmó
            byte[] data2 = "En un lugar de la mancha...".getBytes("UTF-8");
            signatureVerify.update(data2);
            if (signatureVerify.verify(digitalSignature)) {
                System.out.println("La firma es correcta");
            } else {
                System.out.println("La firma NO es correcta");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

