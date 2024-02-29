import CryptoJS from 'crypto-js';

export default class encryptionService {

    static decryptData(encryptedString, Akey) {
        const ciphertext = CryptoJS.enc.Base64.parse(encryptedString);
        const key = CryptoJS.enc.Utf8.parse(Akey.secretKey);

        const decrypted = CryptoJS.AES.decrypt({ ciphertext }, key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7,
        });

        const decryptedString = CryptoJS.enc.Utf8.stringify(decrypted);
        return decryptedString;
    }

    static encryptData(message, Akey) {
        const key = CryptoJS.enc.Utf8.parse(Akey.secretKey);
        const encrypted = CryptoJS.AES.encrypt(message, key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7,
        });

        const encryptedString = encrypted.toString();
        return encryptedString;
    }
}
