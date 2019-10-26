import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;

public class FileEncryption {

    // Process a file (Encrypts or decrypts depending on cipherMode)
    private void processFile(boolean encrypt, File inputFile, String inputKey, File outputFile) throws Exception {
        // Convert key into bytes
        Key key = new SecretKeySpec(inputKey.getBytes(),"AES");

        // Get cipher instance
        Cipher cipher = Cipher.getInstance("AES");


        if(encrypt) {
            cipher.init(Cipher.ENCRYPT_MODE,key);
        }
        else {
            cipher.init(Cipher.DECRYPT_MODE,key);
        }

        // Read input file into byte array
        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] inputBytes = new byte[(int)inputFile.length()];
        fileInputStream.read(inputBytes);

        // Process the byte array from the input file
        byte[] outputBytes = cipher.doFinal(inputBytes);

        // Write the output byte array to the output file
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(outputBytes);

        // Close file streams
        fileInputStream.close();
        fileOutputStream.close();
    }

    // Encrypts a file
    void encrypt(File inputFile, String inputKey, File outputFile) throws Exception {
        processFile(true,inputFile,inputKey,outputFile);
    }

    // Decrypts a file
    void decrypt(File inputFile, String inputKey, File outputFile) throws Exception {
        processFile(false,inputFile,inputKey,outputFile);
    }

}
