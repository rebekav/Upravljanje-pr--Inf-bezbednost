package com.fax.lekari.service.impl;

import com.fax.lekari.service.EncriptionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
@Service
public class EncriptionServiceImpl implements EncriptionService {
    @Override
    public String sifruj(String tekst) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Resource resource = loadPublicKeyFile();
        File publicKeyFile = new File(resource.getURI());
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] secretMessageBytes = tekst.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);
        return encodedMessage;
    }

    public Resource loadPublicKeyFile() {
        return new ClassPathResource("certificates/public.key");
    }

    public Resource loadPrivateKeyFile() {
        return new ClassPathResource("certificates/private.key");
    }

    @Override
    public String desifruj(String tekst) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Resource resource = loadPrivateKeyFile();
        File privateKeyFile = new File(resource.getURI());
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedMessageBytes = Base64.getDecoder().decode(tekst);
        byte[] decryptedMessageBytes = decryptCipher.doFinal(encryptedMessageBytes);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);
        return decryptedMessage;
    }


}
