package common;

import common.encrypt.RSAPublicKeyUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RSAPublicKeyUtilsTest {

    private static final String publicPkcs8 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLrlUsPvzHcveuOzypZLiQ0GHpx0RW6Ji2fHziqMV9UUsu/hxCZ85TKIDRrfiC3WXEKY/tSd/5F+1urqbYYMajqWhJpGA5GvZZ+fBZCEOudn0H7cr/MuKjSiWfKnnkDyUNM3jELnVQLPcyAK2d7rmvNDhrIjmtmBraWGF+wVYJEwIDAQAB";
    private static final String publicPkcs1 = "MIGJAoGBAIuuVSw+/Mdy9647PKlkuJDQYenHRFbomLZ8fOKoxX1RSy7+HEJnzlMogNGt+ILdZcQpj+1J3/kX7W6upthgxqOpaEmkYDka9ln58FkIQ652fQftyv8y4qNKJZ8qeeQPJQ0zeMQudVAs9zIArZ3uua80OGsiOa2YGtpYYX7BVgkTAgMBAAE=";

    private static final String publicPkcs8PEM = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLrlUsPvzHcveuOzypZLiQ0GHp\n" +
            "x0RW6Ji2fHziqMV9UUsu/hxCZ85TKIDRrfiC3WXEKY/tSd/5F+1urqbYYMajqWhJ\n" +
            "pGA5GvZZ+fBZCEOudn0H7cr/MuKjSiWfKnnkDyUNM3jELnVQLPcyAK2d7rmvNDhr\n" +
            "IjmtmBraWGF+wVYJEwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";
    private static final String publicPkcs1PEM = "-----BEGIN RSA PUBLIC KEY-----\n" +
            "MIGJAoGBAIuuVSw+/Mdy9647PKlkuJDQYenHRFbomLZ8fOKoxX1RSy7+HEJnzlMo\n" +
            "gNGt+ILdZcQpj+1J3/kX7W6upthgxqOpaEmkYDka9ln58FkIQ652fQftyv8y4qNK\n" +
            "JZ8qeeQPJQ0zeMQudVAs9zIArZ3uua80OGsiOa2YGtpYYX7BVgkTAgMBAAE=\n" +
            "-----END RSA PUBLIC KEY-----\n";

    @Test
    public void test_publicKeyPKCS8ToPKCS1_success() throws IOException {
        String res = RSAPublicKeyUtils.publicKeyPKCS8ToPKCS1(publicPkcs8);
        assertEquals(publicPkcs1, res);
    }

    @Test
    public void test_publicPKCS_8_ToPEM_success() throws IOException {
        String res = RSAPublicKeyUtils.toPEM(RSAPublicKeyUtils.PEM_WRAP_PKCS8_PUBLIC, publicPkcs8);
        assertEquals(publicPkcs8PEM, res);
    }

    @Test
    public void test_publicPKCS_1_ToPEM_success() throws IOException {
        String res = RSAPublicKeyUtils.toPEM(RSAPublicKeyUtils.PEM_WRAP_PKCS1_PUBLIC, publicPkcs1);
        assertEquals(publicPkcs1PEM, res);
    }

}
