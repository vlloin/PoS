import java.security.NoSuchAlgorithmException;

public class Worker implements Runnable{
    private Person person;

    public Worker(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        String hashPass = person.hash;
        String token = person.firstName + person.lastName;

        for(char i = 'a'; i <= 'z'; i++) {
            for(char j = 'a'; j <= 'z'; j++){
                for(char k = 'a'; k <= 'z'; k++) {
                    for(char l = 'a'; l <= 'z'; l++) {
                        for(char m = 'a'; m <= 'z'; m++) {
                            char data[] = {i, j, k, l, m};
                            String passwd = new String(data);
                            String hashPassSearch = token + passwd;
                            try {
                                String foundPass = BruteForceExperiment.toMd5(hashPassSearch);
                                if (foundPass.equals(hashPass)) {
                                    person.foundPassword = passwd;
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
}
