import java.util.ArrayList;
import java.util.Scanner;
public class StudentGradeManager {
    static class Student {
        String name;
        double score;
        Student(String name, double score) {
            this.name = name;
            this.score = score;
        }
    }
    private static final ArrayList<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
        boolean running = true;
        System.out.println("=================================================");
        System.out.println("       STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("=================================================");
        while (running) {
            printMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    displayStatistics();
                    break;
                case 4:
                    displaySummaryReport();
                    break;
                case 5:
                    running = false;
                    System.out.println("\nExiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-5.");
            }
        }
        scanner.close();
    }
    private static void printMenu() {
        System.out.println("\n---------------- MENU ----------------");
        System.out.println("1. Add Student Grade");
        System.out.println("2. Display All Students");
        System.out.println("3. Show Statistics (Avg/High/Low)");
        System.out.println("4. Display Summary Report");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    private static int readMenuChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // triggers "Invalid option" in the switch
        }
    }
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Student not added.");
            return;
        }
        double score = readValidScore();
        students.add(new Student(name, score));
        System.out.printf("Added: %s -> %.2f%n", name, score);
    }
    private static double readValidScore() {
        double score = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print("Enter score (0-100): ");
            try {
                score = Double.parseDouble(scanner.nextLine().trim());
                if (score < 0 || score > 100) {
                    System.out.println("Score must be between 0 and 100. Try again.");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a numeric score.");
            }
        }
        return score;
    }
    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo student records yet.");
            return;
        }
        System.out.println("\n---------------- STUDENT LIST ----------------");
        System.out.printf("%-5s %-20s %-10s%n", "No.", "Name", "Score");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-5d %-20s %-10.2f%n", i + 1, s.name, s.score);
        }
    }
    private static void displayStatistics() {
        if (students.isEmpty()) {
            System.out.println("\nNo student records available for statistics.");
            return;
        }
        double sum = 0;
        double highest = students.get(0).score;
        double lowest = students.get(0).score;
        Student topStudent = students.get(0);
        Student bottomStudent = students.get(0);
        for (Student s : students) {
            sum += s.score;
            if (s.score > highest) {
                highest = s.score;
                topStudent = s;
            }
            if (s.score < lowest) {
                lowest = s.score;
                bottomStudent = s;
            }
        }
        double average = sum / students.size();
        System.out.println("\n---------------- STATISTICS ----------------");
        System.out.printf("Total Students : %d%n", students.size());
        System.out.printf("Average Score  : %.2f%n", average);
        System.out.printf("Highest Score  : %.2f (%s)%n", highest, topStudent.name);
        System.out.printf("Lowest Score   : %.2f (%s)%n", lowest, bottomStudent.name);
    }
    private static void displaySummaryReport() {
        if (students.isEmpty()) {
            System.out.println("\nNo data available to generate a report.");
            return;
        }
        displayAllStudents();
        displayStatistics();
        System.out.println("\n---------------- GRADE BREAKDOWN ----------------");
        int a = 0, b = 0, c = 0, d = 0, f = 0;
        for (Student s : students) {
            char grade = letterGrade(s.score);
            switch (grade) {
                case 'A': a++; break;
                case 'B': b++; break;
                case 'C': c++; break;
                case 'D': d++; break;
                default:  f++; break;
            }
        }
        System.out.printf("A (90-100): %d%n", a);
        System.out.printf("B (80-89) : %d%n", b);
        System.out.printf("C (70-79) : %d%n", c);
        System.out.printf("D (60-69) : %d%n", d);
        System.out.printf("F (0-59)  : %d%n", f);
    }
    private static char letterGrade(double score) {
        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';
        return 'F';
    }
}
