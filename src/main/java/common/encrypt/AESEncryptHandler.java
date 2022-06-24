package common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

public class AESEncryptHandler implements EncryptHandler {

    private final Key key;

    public AESEncryptHandler(String keyString) {
        this(createKey(keyString));
    }

    public AESEncryptHandler(Key key) {
        this.key = key;
    }

    @Override
    public String encrypt(String originText) throws GeneralSecurityException {
        Cipher encryptCipher = createCipher(Cipher.ENCRYPT_MODE, key);

        byte[] originBytes = originText.getBytes(StandardCharsets.UTF_8);
        byte[] secretBytes = encryptCipher.doFinal(originBytes);
        return Base64.getEncoder().encodeToString(secretBytes);
    }

    @Override
    public String decrypt(String secretText) throws GeneralSecurityException {
        Cipher decryptCipher = createCipher(Cipher.DECRYPT_MODE, key);

        byte[] secretBytes = Base64.getDecoder().decode(secretText);
        byte[] originBytes = decryptCipher.doFinal(secretBytes);
        return new String(originBytes, StandardCharsets.UTF_8);
    }

    private static Cipher createCipher(int mode, Key key) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(mode, key);
        return cipher;
    }

    private static Key createKey(String keyString) {
        // TODO 检查字符串长度满足16字节，或者自动补充至16字节
        return new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), "AES");
    }

}
