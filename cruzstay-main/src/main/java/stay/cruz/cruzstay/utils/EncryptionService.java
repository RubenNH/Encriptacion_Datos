package stay.cruz.cruzstay.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/*
 Este es un servicio de encriptación que utiliza el algoritmo AES para encriptar y desencriptar datos.
 Para encriptar un dato, se utiliza el método encrypt, que recibe un String y devuelve un String encriptado.
    Para desencriptar un dato, se utiliza el método decrypt, que recibe un String encriptado y devuelve el String original.
    El algoritmo y la transformación utilizada son AES y AES/ECB/PKCS5Padding respectivamente.
    La clave secreta utilizada para encriptar y desencriptar los datos se obtiene de la variable de entorno SECRET_KEY.
*/
@Service
public class EncryptionService {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    @Value("${encript.key}")
    private String SECRET_KEY;
    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
