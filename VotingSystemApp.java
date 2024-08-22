import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Voter {
    private String name;
    private int age;
    private String voterID;
    private boolean hasVoted;

    public Voter(String name, int age, String voterID) {
        this.name = name;
        this.age = age;
        this.voterID = voterID;
        this.hasVoted = false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getVoterID() {
        return voterID;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}

class ElectronicVotingSystem {
    private Map<String, Integer> candidates; // Store candidates and their respective votes
    private Map<String, Voter> voters;       // Store registered voters
    private int totalVotes;

    public ElectronicVotingSystem() {
        candidates = new HashMap<>();
        voters = new HashMap<>();
        totalVotes = 0;
    }

    // Add candidates to the system
    public void addCandidate(String candidateName) {
        candidates.put(candidateName, 0);
    }

    // Register a voter
    public void registerVoter(String name, int age, String voterID) {
        if (age >= 18) {
            voters.put(voterID, new Voter(name, age, voterID));
            System.out.println("Voter registration successful for " + name + "!");
        } else {
            System.out.println("Voter registration failed. You must be 18 or older to vote.");
        }
    }

    // Vote for a candidate
    public void vote(String voterID, String candidateName) {
        if (voters.containsKey(voterID)) {
            Voter voter = voters.get(voterID);
            if (!voter.hasVoted()) {
                if (candidates.containsKey(candidateName)) {
                    candidates.put(candidateName, candidates.get(candidateName) + 1);
                    voter.setHasVoted(true);
                    totalVotes++;
                    System.out.println("Your vote has been cast successfully, " + voter.getName() + "!");
                } else {
                    System.out.println("Invalid candidate. Vote not counted.");
                }
            } else {
                System.out.println("You have already voted. Multiple votes are not allowed.");
            }
        } else {
            System.out.println("Invalid voter ID. Please register first.");
        }
    }

    // Display election results
    public void displayResults() {
        System.out.println("\nElection Results:");
        System.out.println("+----------------+-------+");
        System.out.println("| Candidate Name | Votes |");
        System.out.println("+----------------+-------+");
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            System.out.printf("| %-14s | %-5d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println("+----------------+-------+");
        System.out.println("Total Votes Cast: " + totalVotes);
    }

    // Display candidates
    public void displayCandidates() {
        System.out.println("\nAvailable Candidates:");
        System.out.println("+----------------+");
        System.out.println("| Candidate Name |");
        System.out.println("+----------------+");
        for (String candidate : candidates.keySet()) {
            System.out.printf("| %-14s |\n", candidate);
        }
        System.out.println("+----------------+");
    }

    // Check if the voting process is still ongoing
    public boolean isOngoing() {
        return totalVotes < 100; // For example, limit voting to 100 votes
    }
}

public class VotingSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ElectronicVotingSystem votingSystem = new ElectronicVotingSystem();

        // Add some candidates
        votingSystem.addCandidate("Vasavi");
        votingSystem.addCandidate("Varun");
        votingSystem.addCandidate("Padmaja");

        System.out.println("Welcome to the Electronic Voting System!");

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Register a Voter");
            System.out.println("2. View Candidates");
            System.out.println("3. Cast a Vote");
            System.out.println("4. View Election Results");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Voter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Voter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Voter ID: ");
                    String voterID = scanner.nextLine();
                    votingSystem.registerVoter(name, age, voterID);
                    break;

                case 2:
                    votingSystem.displayCandidates();
                    break;

                case 3:
                    System.out.print("Enter your Voter ID: ");
                    voterID = scanner.nextLine();
                    votingSystem.displayCandidates();
                    System.out.print("Enter the name of the candidate you want to vote for: ");
                    String candidateName = scanner.nextLine();
                    votingSystem.vote(voterID, candidateName);
                    break;

                case 4:
                    votingSystem.displayResults();
                    break;

                case 5:
                    System.out.println("Thank you for using the Electronic Voting System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
