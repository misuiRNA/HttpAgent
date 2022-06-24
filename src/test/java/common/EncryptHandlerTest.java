package common;

import common.encrypt.AESEncryptHandler;
import common.encrypt.EncryptHandler;
import common.encrypt.RSAEncryptHandler;
import org.junit.Test;

import java.security.GeneralSecurityException;

import static junit.framework.TestCase.assertEquals;

public class EncryptHandlerTest {

    @Test
    public void test_aes_encrypt_success() throws GeneralSecurityException {
        String originText = "admin";
        EncryptHandler rsa = new AESEncryptHandler("0123456789ABCDEF");
        String encryptText = rsa.encrypt(originText);

        assertEquals("O7HzbBvrCRynvyMNo4gh/g==", encryptText);
    }

    @Test
    public void test_aes_decrypt_success() throws GeneralSecurityException {
        String secretText = "O7HzbBvrCRynvyMNo4gh/g==";
        EncryptHandler rsa = new AESEncryptHandler("0123456789ABCDEF");
        String originText = rsa.decrypt(secretText);
        assertEquals("admin", originText);
    }

    @Test
    public void test_rsa_encrypt_success() throws GeneralSecurityException {
        String org = "admin";
        EncryptHandler rsa = new RSAEncryptHandler("0123456789ABCDEF");
        String encryptText = rsa.encrypt(org);

        String expectEncryptText = "KAkXxss0N5qQi+AdWKTQNZc+bzO+3fN6DWrDoEhXU8oSAQJUdnOj0gAzcFX4zd8YauvJ1eKMNI6xJ/EDvwcIN46i0jNSNJeIC86R2bqUzfSiI7s00dDsyjvB8NxBjrE763m1d4H12TnfkP3h0IVJ/wuckn+67kDwEyqgKTF5yRo=";
        assertEquals(expectEncryptText, encryptText);
    }

    @Test
    public void test_rsa_decrypt_success() throws GeneralSecurityException {
        String secretText = "KAkXxss0N5qQi+AdWKTQNZc+bzO+3fN6DWrDoEhXU8oSAQJUdnOj0gAzcFX4zd8YauvJ1eKMNI6xJ/EDvwcIN46i0jNSNJeIC86R2bqUzfSiI7s00dDsyjvB8NxBjrE763m1d4H12TnfkP3h0IVJ/wuckn+67kDwEyqgKTF5yRo=";
        EncryptHandler rsa = new RSAEncryptHandler("0123456789ABCDEF");
        String originText = rsa.decrypt(secretText);
        assertEquals("admin", originText);
    }

}
