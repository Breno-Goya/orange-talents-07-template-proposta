package br.com.zupacademy.msPropostas.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


public class Encryption {

    private static Encryption instance = null;
    private static final String env ="SECRET_ENCRYPTION";
    private String secret;

    private Encryption() {
        secret = System.getenv().get(env);
    }

    public static synchronized Encryption getInstance() {
        if(instance == null)
            instance = new Encryption();
        return instance;
    }

    public String encrypt(@NotBlank String cleanText) {
        Assert.hasText(cleanText, "Texto é obrigatório");
        StandardPBEStringEncryptor encryptor = this.getEncryptor();
        return encryptor.encrypt(cleanText);
    }

    public String decipher(@NotBlank String cipherText) {
        Assert.hasText(cipherText, "Texto é obrigatório");
        StandardPBEStringEncryptor encryptor = this.getEncryptor();
        return encryptor.decrypt(cipherText);
    }

    public String hashGenerate(@NotBlank String cleanText) {
        Assert.hasText(cleanText,"Texto é obrigatório");

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(cleanText.getBytes(StandardCharsets.UTF_8));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    private StandardPBEStringEncryptor getEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(secret);
        encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());
        return encryptor;
    }
}
