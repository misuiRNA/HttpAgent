package common.encrypt;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

public class RSAPublicKeyUtils {

    public static final String PEM_WRAP_PKCS1_PUBLIC  = "RSA PUBLIC KEY";
    public static final String PEM_WRAP_PKCS1_PRIVATE  = "RSA PRIVATE KEY";
    public static final String PEM_WRAP_PKCS8_PUBLIC  = "PUBLIC KEY";
    public static final String PEM_WRAP_PKCS8_PRIVATE  = "PRIVATE KEY";

    public static String publicKeyPKCS8ToPKCS1(String pkcs8) throws IOException {
        byte[] pkcs8Bytes = Base64.getDecoder().decode(pkcs8);
        SubjectPublicKeyInfo keyInfo = SubjectPublicKeyInfo.getInstance(pkcs8Bytes);
        ASN1Primitive primitive = keyInfo.parsePublicKey();
        byte[] pkcs1Bytes = primitive.getEncoded();
        return Base64.getEncoder().encodeToString(pkcs1Bytes);
    }

    public static String privateKeyPKCS8ToPKCS1(String pkcs8) throws IOException {
        byte[] pkcs8Bytes = Base64.getDecoder().decode(pkcs8);
        PrivateKeyInfo keyInfo = PrivateKeyInfo.getInstance(pkcs8Bytes);
        ASN1Encodable encodable = keyInfo.parsePrivateKey();
        ASN1Primitive primitive = encodable.toASN1Primitive();
        byte[] pkcs1Bytes = primitive.getEncoded();
        return Base64.getEncoder().encodeToString(pkcs1Bytes);
    }

    public static String toPEM(String wrapper, String body) throws IOException {
        byte[] pkcs1Bytes = Base64.getDecoder().decode(body);
        PemObject pemObject = new PemObject(wrapper, pkcs1Bytes);
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        return stringWriter.toString();
    }

}
