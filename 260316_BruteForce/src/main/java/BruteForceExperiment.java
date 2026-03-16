import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class BruteForceExperiment {
    public static void main(String[] args) {
        System.out.println("Hello Test");
        // "Pyotr,Knoller,17F0+F/FbpgKsHGYq3tFvA=="
        String hashPass = "17F0+F/FbpgKsHGYq3tFvA==";
        String token = "PyotrKnoller";

        for(char i = 'a'; i <= 'z'; i++) {
            for(char j = 'a'; j <= 'z'; j++){
                for(char k = 'a'; k <= 'z'; k++) {
                    for(char l = 'a'; l <= 'z'; l++) {
                        for(char m = 'a'; m <= 'z'; m++) {
                            char data[] = {i, j, k, l, m};
                            String passwd = new String(data);
                            String hashPassSearch = token + passwd;
                            try {
                                System.out.println(passwd);
                                String foundPass = BruteForceExperiment.toMd5(hashPassSearch);
                                if (foundPass.equals(hashPass)) {
                                    System.out.println("Pass found: " + passwd);
                                    return;
                                }
                            } catch (NoSuchAlgorithmException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    public static String toMd5(String token) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(messageDigest.digest(token.getBytes()));

    }
}
