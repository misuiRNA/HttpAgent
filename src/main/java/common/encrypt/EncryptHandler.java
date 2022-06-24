package common.encrypt;

import java.security.GeneralSecurityException;

public interface EncryptHandler {

    String encrypt(String input) throws GeneralSecurityException;

    String decrypt(String secretText) throws GeneralSecurityException;

}
