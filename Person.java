public class Person {
    private String firstName;
    private String lastName;
    private double age;
    private String levelOfSecurity; // General, Admin
    private int rows;
    private int columns;
    private int numSuccesses;
    private double numTries;
    private boolean loggedIn;
    private char[][] fingerprint;

    //// CONSTRUCTOR ////
    public Person(String firstName, String lastName, double age, String levelOfSecurity, int rows, int columns, int numSuccesses, int numTries, boolean loggedIn, char[][] fingerprint) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.levelOfSecurity = levelOfSecurity;
        this.rows = rows;
        this.columns = columns;
        this.numSuccesses = numSuccesses;
        this.numTries = numTries;
        this.loggedIn = loggedIn;
        this.fingerprint = fingerprint;
    }

    //// GETTERS ////
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAge() {
        return age;
    }

    public String getLevelOfSecurity() {
        return levelOfSecurity;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumSuccesses() {
        return numSuccesses;
    }

    public double getNumTries(){
        return numTries;
    }

    public boolean getLoggedIn(){
        return loggedIn;
    }

    public char[][] getFingerprint() {
        return fingerprint;
    }

    //// SETTERS ////

    public void setNumSuccesses(int numSuccesses) {
        this.numSuccesses = numSuccesses;
    }

    public void setNumTries(double numTries){
        this.numTries = numTries;
    }

    public void setLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }

    public void setFingerprint(char[][] fingerprint) {
        this.fingerprint = fingerprint;
    }    
}
