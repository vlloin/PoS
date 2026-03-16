public class Person {
    public String firstName;
    public String lastName;
    public String hash;
    public String foundPassword;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", foundPassword='" + foundPassword + '\'' +
                '}';
    }

    public Person(String line) {
        String[] tokens = line.split(",");

        this.firstName = tokens[0];
        this.lastName = tokens[1];
        this.hash = tokens[2];


    }
}
