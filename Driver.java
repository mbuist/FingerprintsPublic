import java.util.*;

public class Driver {

    public static Company company = new Company();
    public static ArrayList<Person> companyAL = company.getCompanyAL();

    public static void main(String[] args) {
        String [] startingOptions = new String[] {"Onboard employees", "Quit"};
        String [] secondOptions = new String[] {"Run Reports", "Analyze Reports", "Login One Employee", "Show Company Account Information", "Show Personal Account", "Add Employee", "Remove Employee", "Logout One Employee", "Logout Everyone", "Quit"};

        boolean companyOnboarded = false;
        boolean quit = false;

        do { // first do while loop that runs startingOptions
            System.out.println("\nWhat would you like to do?");
            for (int i = 0; i < startingOptions.length; i++) {
                System.out.println(i + 1 + ": " + startingOptions[i]);
            }
            System.out.print("Select option > ");
            int firstChoice = Integer.parseInt(StdIn.readLine());
            switch (firstChoice){
                case 1: // Onboard employees
                    System.out.print("csv file name > ");
                    String csvfilename = StdIn.readLine();
                    if (company.processCSV(csvfilename)){
                        companyOnboarded = true;
                    }
                    StdIn.resync();
                    break;

                case 2: // Quit
                    company.quit();
                    quit = true;
                    return;

                default:
                    System.out.println("\nSorry that's not an option");
                    break;
            }
        } while (!companyOnboarded);

        do { // second do while loop that runs secondOptions
            System.out.println("\nWhat would you like to do now that the company is onboarded?");
            for (int i = 0; i < secondOptions.length; i ++){
                System.out.println(i + 1 + ": " + secondOptions[i]);
            }
            System.out.print("Select option > ");
            int secondChoice = Integer.parseInt(StdIn.readLine());
            StdIn.resync();
            switch (secondChoice){
                case 1: // Run Reports
                    System.out.println("\nPart 1 is inputting the login file that will simulate multiple attempts to log in employees");
                    System.out.println("We will run the login report assuming all employees are logged out to start");
                    company.logoutAll();
                    System.out.print("Login file (LoginAttempts#.txt) > ");
                    String loginAttemptFile = StdIn.readLine();
                    StdIn.resync();
                    System.out.println("\nPart 2 is inputting the authentication threshold");
                    System.out.print("Threshold of error (numbers 1-100) > ");
                    int reportThreshold = Integer.parseInt(StdIn.readLine());
                    StdIn.resync();
                    // turning text file of login files into arraylist to read from
                    ArrayList<String> loginAttemptList = new ArrayList<String>();
                    StdIn.setFile(loginAttemptFile);
                    while (StdIn.hasNextLine()) {
                        loginAttemptList.add(StdIn.readLine());
                    }
                    StdIn.resetFile();
                    StdIn.resync();
                    // // writing the login report
                    // /*
                    // * format:
                    // * first last boolean
                    //  */
                    // StdOut.setFile("Report.out");

                    for (String loginFile : loginAttemptList) { // loops through login files
                        StdIn.setFile(loginFile);
                        Person person = company.processPerson(loginFile); // processes person we are trying to login
                        Person personInAL = null;
                        for (Person personAL : companyAL){ // finds corresponding matching person in companyAL that we are trying to login
                            if (personAL.getFirstName().equalsIgnoreCase(person.getFirstName()) && personAL.getLastName().equalsIgnoreCase(person.getLastName())){
                                personInAL = personAL;
                                break;
                            }
                        }
                        if (personInAL != null){ // if the person we are trying to login exists in companyAL
                            if (personInAL.getLoggedIn() == false) { // if statement that only attempts to login employees who haven't been able to login yet
                                if (company.authorized(person, reportThreshold)) { // authorized() will update isntance variables of successes, numTries and loggedIn
                                    // StdOut.println(person.getFirstName() + " " + person.getLastName() + " " + true);
                                } else {
                                    // StdOut.println(person.getFirstName() + " " + person.getLastName() + " " + false);
                                }
                            } else {
                                // ignore this attempt to login an employee that is already logged in
                            }
                        }
                        // else {
                        //     System.out.println("\nPerson is not an employee in the company");
                        // }
                    }
                    StdIn.resync();
                    System.out.println("\nReport has been run");
                    break;

                case 2: // Analyze Reports
                    System.out.println();
                    // StdIn.setFile("Report.out");
                    // if (StdIn.readLine() != null){
                    int hasNoTries = 0;
                    for (Person person : companyAL){ // loops to check if all employees have 0 login attempts
                        if (person.getNumTries() == 0){
                            person.setNumTries(1);
                            hasNoTries += 1;
                        }
                    }
                    if (hasNoTries == companyAL.size()){ // all employees have 0 login attempts
                        System.out.println("Please Run Reports first");
                    }
                    else {
                        company.analyzeReports(); // calls analyzeReports()
                    }
                    // }
                    // else {
                    //     System.out.println("Please Run Reports first");
                    // }
                    // StdIn.resetFile();
                    // StdIn.resync();
                    break;

                case 3: // Login Employee
                    System.out.print("Person's file name > ");
                    String personLogin = StdIn.readLine();
                    Person personLoginObject = company.processPerson(personLogin);
                    StdIn.resync();
                    System.out.print("Threshold of error (numbers 1-100) > ");
                    int loginThreshold = Integer.parseInt(StdIn.readLine());
                    StdIn.resync();
                    Person personInAL = null;
                    for (Person personAL : companyAL) { // finds corresponding matching person in companyAL that we are trying to login
                        if (personAL.getFirstName().equalsIgnoreCase(personLoginObject.getFirstName()) && personAL.getLastName().equalsIgnoreCase(personLoginObject.getLastName())) {
                            personInAL = personAL;
                            break;
                        }
                    }
                    if (personInAL != null){
                        if (personInAL.getLoggedIn()) { // employee is already logged in
                            System.out.println("\nThis employee is already logged in");
                        } else {
                            if (company.authorized(personLoginObject, loginThreshold)) { // authorized() will update isntance variables of successes, numTries and loggedIn
                                System.out.println("\nHello " + personLoginObject.getFirstName() + "! You are a " + personLoginObject.getLevelOfSecurity() + " employee.");
                            } else {
                                System.out.println("\nSorry we were not able to log the user in");
                            }
                        }
                    }
                    else {
                        System.out.println("\nPerson is not an employee in the company");
                    }
                    break;

                case 4: // Show Company Account Information
                    // Case is used to allow visual check for who's logged in and other details
                    StdOut.setFile("CompanyInformation.out");
                    company.getImageToString();
                    System.out.println("\nCheck your files for the output file - CompanyInformation.out.");
                    StdOut.resetFile();
                    StdIn.resync();
                    break;

                case 5: // Show Personal Information
                    System.out.println("\nWhich employee would you like to see the report for?");
                    for (int i = 0; i < companyAL.size(); i++) {
                        System.out.println(i + 1 + ": " + companyAL.get(i).getFirstName() + " " + companyAL.get(i).getLastName());
                    }
                    System.out.print("Employee number > ");
                    int informationIndex = Integer.parseInt(StdIn.readLine()) - 1;
                    if (informationIndex >= companyAL.size()){ // bounds check for arraylist
                        System.out.println("\nNumber out of bounds.");
                    }
                    else {
                        String informationPersonFirst = companyAL.get(informationIndex).getFirstName();
                        String informationPersonLast = companyAL.get(informationIndex).getLastName();
                        StdOut.setFile(informationPersonFirst + informationPersonLast + "AccountInformation.out");
                        company.getImageToString(informationPersonFirst, informationPersonLast); // prints account details to output file
                        StdOut.resetFile();
                        StdIn.resync();
                        System.out.println("\nCheck your files for the output file - " + informationPersonFirst + informationPersonLast + "Information.out");
                        StdOut.resetFile();
                    }
                    break;

                case 6: // Add Employees
                    System.out.print("Person's file name > ");
                    String newPersonFile = StdIn.readLine();
                    StdIn.resync();
                    Person newPerson = company.processPerson(newPersonFile);
                    StdIn.resync();
                    StdIn.resetFile();
                    if (company.addPerson(newPerson)) { // if addition successful
                        System.out.println("\nWelcome " + newPerson.getFirstName());
                    } else {
                        System.out.println("\nSorry we couldn't add this person to the company.");
                    }
                    break;

                case 7: // Remove Employees
                    System.out.println("\nWhich employee would you like to remove?");
                    for (int i = 0; i < companyAL.size(); i++) {
                        System.out.println(i + 1 + ": " + companyAL.get(i).getFirstName() + " " + companyAL.get(i).getLastName());
                    }
                    System.out.print("Employee number > ");
                    int removeIndex = Integer.parseInt(StdIn.readLine()) - 1;
                    if (removeIndex >= companyAL.size()){  // bounds check for arraylist
                        System.out.println("\nNumber out of bounds.");
                    }
                    else {
                        String removePersonFirst = companyAL.get(removeIndex).getFirstName();
                        String removePersonLast = companyAL.get(removeIndex).getLastName();
                        StdIn.resync();
                        if (company.deletePerson(removePersonFirst, removePersonLast)) { // if deletion was successful
                            StdIn.resync();
                            System.out.println("\nRemoval of " + removePersonFirst + " " + removePersonLast + " was successful");
                        } else {
                            System.out.println("\nRemoval of " + removePersonFirst + " " + removePersonLast + " was unsuccessful");
                        }
                    }
                    break;

                case 8: // Logout One Employee
                    int numLoggedIn = 0;
                    for (int i = 0; i < companyAL.size(); i++) { // counts how many employees are logged int
                        if (companyAL.get(i).getLoggedIn()){
                            numLoggedIn += 1;
                            System.out.println(companyAL.get(i));
                        }
                    }
                    if (numLoggedIn == 0){ // if no employees are logged in
                        System.out.println("There are no employees that are logged in");
                    }
                    else {
                        System.out.println("\nWhich employee would you like to logout?");
                        for (int i = 0; i < companyAL.size(); i++) {
                            if (companyAL.get(i).getLoggedIn()) {
                                System.out.println((i - 1) + ": " + companyAL.get(i).getFirstName() + " " + companyAL.get(i).getLastName());
                            }
                        }
                        System.out.print("Employee number > ");
                        int logoutIndex = Integer.parseInt(StdIn.readLine()) - 1;
                        if (logoutIndex >= numLoggedIn){  // bounds check for arraylist
                            System.out.println("\nNumber out of bounds.");
                        }
                        else {
                            Person personToLogout = companyAL.get(logoutIndex);
                            String logoutPersonFirst = personToLogout.getFirstName();
                            String logoutPersonLast = personToLogout.getLastName();
                            StdIn.resync();
                            Person personInAl = null;
                            for (Person personAL : companyAL) { // finds corresponding matching person in companyAL that we are trying to login
                                if (personAL.getFirstName().equalsIgnoreCase(personToLogout.getFirstName()) && personAL.getLastName().equalsIgnoreCase(personToLogout.getLastName())) {
                                    personInAl = personAL;
                                    break;
                                }
                            }
                            if (personInAl != null) {
                                if (!personInAl.getLoggedIn()) {
                                    System.out.println("\nThis employee is already logged out");
                                } else {
                                    if (company.logout(logoutPersonFirst, logoutPersonLast)) { // if logout was successful
                                        StdIn.resync();
                                        System.out.println("\n" + logoutPersonFirst + " " + logoutPersonLast + " is logged out");
                                    } else {
                                        System.out.println("\n" + logoutPersonFirst + " " + logoutPersonLast + " was not able to be logged out");
                                    }
                                }
                            } else {
                                System.out.println("\nPerson is not an employee in the company");
                            }
                        }
                    }
                    break;

                case 9: // Logout Everyone
                    company.logoutAll();
                    System.out.println("\nCompany has been logged out");
                    break;

                case 10: // Quit
                    company.quit();
                    quit = true;
                    return;

                default:
                    System.out.println("\nSorry that's not an option");
                    break;
            }
        } while (companyOnboarded && !quit);
    }
}
