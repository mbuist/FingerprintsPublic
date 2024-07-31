import java.util.*;

public class Company {

    //// INSTANCE VARIABLE ////
    private ArrayList<Person> companyAL;

    //// DEFAULT CONSTRUCTOR ////
    public Company() {
        companyAL = new ArrayList<Person>();
    }

    //// RESETS VARIABLE BACK TO ORIGINAL STATE ////
    public void quit(){
        this.companyAL = null;
    }

    //// GETTER ////
    public ArrayList<Person> getCompanyAL() {
        return companyAL;
    }
 
    /*
     * METHODS TO BE WRITTEN
     */

    /**
     * reads person from input txt file and returns Person object
     *
     * @param String filename
     * @return Person object
     */
    public Person processPerson(String filename) {
        
        // WRITE YOUR CODE HERE //

        // Students will read in a Person txt file and return a Person object

        return null;
    }

    /** reads in CSV creating Person objects
     * 
     * @param String filename
     * @return void
     */
    public boolean processCSV(String csvname){
        
        // WRITE YOUR CODE HERE //

        // Students will read Person txt files from a csv and process those new employees

        return true;
    }

    /**
     * adds Person to company's array list assuming a security clearance of admin
     *
     * @param Person personObject
     * @return boolean for success
     */
    public boolean addPerson(Person personObject) {
        
        // WRITE YOUR CODE HERE //

        // Students will attempt to add a non-duplicate Person as an employee

        return true;
    }

    /**
     * deletes fingerprint with given first and last name
     *
     * @param String person's first and last name
     * @return boolean for success
     */
    public boolean deletePerson(String firstName, String lastName) {
        
        // WRITE YOUR CODE HERE //

        // Students will attempt to delete an employee from the company

        return true;
    }

    /**
     * checks if input fingerprint has a threshold% match with any fingerprint in device
     *
     * @param Person personObject
     * @param int threshold in percent [0,100]
     * @return boolean for success
     */
    public boolean authorized(Person personObject, int threshold) {
        
        // WRITE YOUR CODE HERE //

        // Students will compare the chars in their respective fingerprints 2D arrays
        //  and authorize if it matches above the threshold

        return true;
    }

    /**
     * runs reports on logging in many people
     *
     * @param String filename Report.out
     * @return void
     */
    public void analyzeReports (){
        
        // WRITE YOUR CODE HERE //

        // Students will traverse the company and run reports on employee's login attempts

    }

    /**
     * PROVIDED prints out character version of all fingerprints in company
     *
     * @param String person's first name
     * @param String person's last name
     * @return void
     */
    public void getImageToString(String personFirst, String personLast) {
        for (Person person : companyAL) { // prints out to "FirstLastAccountInformation.out"
            if (person.getFirstName().equalsIgnoreCase(personFirst) && person.getLastName().equalsIgnoreCase(personLast)){
                if (person.getLoggedIn()) {
                    StdOut.println("Full Name: " + person.getFirstName() + " " + person.getLastName() + " is logged in");
                } else {
                    StdOut.println("Full Name: " + person.getFirstName() + " " + person.getLastName() + " is logged out");
                }
                StdOut.println("NumSuccesses " + person.getNumSuccesses() + "\nNumTries " + person.getNumTries());
                StdOut.println("Age: " + person.getAge());
                StdOut.println("Level of Security: " + person.getLevelOfSecurity());
                StdOut.println("Fingerprint dimensions: " + person.getRows() + " by " + person.getColumns());
                for (int i = 0; i < person.getRows(); i++) {
                    for (int j = 0; j < person.getColumns(); j++) {
                        StdOut.print(person.getFingerprint()[i][j]);
                    }
                    StdOut.println();
                }
                StdOut.println();
            }
        }
    }

    /**
     * PROVIDED prints out character version of all fingerprints in entire company
     *
     * @param void
     * @return void
     */
    public void getImageToString() {
        for (Person person : companyAL) { // loops through companyAL and prints out to "CompanyInformation.out"
            if (person.getLoggedIn()){
                StdOut.println("Full Name: " + person.getFirstName() + " " + person.getLastName() + " is logged in");
            }
            else{
                StdOut.println("Full Name: " + person.getFirstName() + " " + person.getLastName() + " is logged out");
            }
            StdOut.println("NumSuccesses " + person.getNumSuccesses() + "\nNumTries " + person.getNumTries());
            StdOut.println("Age: " + person.getAge());
            StdOut.println("Level of Security: " + person.getLevelOfSecurity());
            StdOut.println("Fingerprint dimensions: " + person.getRows() + " by " + person.getColumns());
            for (int i = 0; i < person.getRows(); i++) {
                for (int j = 0; j < person.getColumns(); j++) {
                    StdOut.print(person.getFingerprint()[i][j]);
                }
                StdOut.println();
            }
            StdOut.println();
        }
    }

    /**
     * PROVIDED logs out one specific person
     *
     * @param void
     * @return void
     */
    public boolean logout(String personFirst, String personLast) {
        for (Person person : companyAL) { // loops through companyAL and logs out and resets successes & tries for specific person
            if (person.getFirstName().equalsIgnoreCase(personFirst) && person.getLastName().equalsIgnoreCase(personLast)) {
                // person.setNumSuccesses(0);
                // person.setNumTries(0);
                person.setLoggedIn(false);
                return true;
            }
        }
        return false;
    }

    /**
     * PROVIDED logs out entire company
     *
     * @param void
     * @return void
     */
    public void logoutAll() {
        for (Person person : companyAL) { // loops through companyAL and logs out and resets successes & tries for every employee
            // person.setNumSuccesses(0);
            // person.setNumTries(0);
            person.setLoggedIn(false);
        }
    }

    public static void main(String[] args) {
        System.out.println("Heyy, did you try to run the wrong file??");
        System.out.println("Make sure you run the DRIVER to test your output in the terminal");
    }
}
