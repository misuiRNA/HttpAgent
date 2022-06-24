package common.encrypt;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class RSAEncryptHandler implements EncryptHandler  {

    private final KeyPair keyPair;

    public RSAEncryptHandler(String keySeed) throws GeneralSecurityException {
        this(createKeyPair(keySeed));
    }

    public RSAEncryptHandler(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public String encrypt(String originText) throws GeneralSecurityException {
        Cipher encryptCipher = createCipher(Cipher.ENCRYPT_MODE, keyPair.getPrivate());

        byte[] originBytes = originText.getBytes(StandardCharsets.UTF_8);
        byte[] secretBytes = encryptCipher.doFinal(originBytes);
        return Base64.getEncoder().encodeToString(secretBytes);
    }

    @Override
    public String decrypt(String secretText) throws GeneralSecurityException {
        Cipher decryptCipher = createCipher(Cipher.DECRYPT_MODE, keyPair.getPublic());

        byte[] secretBytes = Base64.getDecoder().decode(secretText);
        byte[] originBytes = decryptCipher.doFinal(secretBytes);
        return new String(originBytes, StandardCharsets.UTF_8);
    }

    private static Cipher createCipher(int mode, Key key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(mode, key);
        return cipher;
    }

    private static KeyPair createKeyPair(String seed) throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(seed.getBytes(StandardCharsets.UTF_8));

        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(1024, random);

        return generator.generateKeyPair();
    }

}
