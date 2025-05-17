import java.util.ArrayList;

public class Gradebook {
    private ArrayList<Double> studentGrades;

    // Constructor
    public Gradebook() {
        this.studentGrades = new ArrayList<>();
    }

    // Add a grade
    public void addGrade(double grade) {
        if (grade >= 0.0 && grade <= 4.0) {
            studentGrades.add(grade);
        } else {
            System.out.println("Grade must be between 0.0 and 4.0");
        }
    }

    // Get all grades
    public ArrayList<Double> getGrades() {
        return studentGrades;
    }

    // Calculate GPA (average)
    public double calculateGPA() {
        if (studentGrades.isEmpty()) return 0.0;

        double total = 0;
        for (double g : studentGrades) {
            total += g;
        }
        return total / studentGrades.size();
    }

    // Display grades
    public void displayGrades() {
        if (studentGrades.isEmpty()) {
            System.out.println("No grades recorded.");
        } else {
            System.out.println("Grades:");
            for (double g : studentGrades) {
                System.out.println("- " + g);
            }
        }
    }

    // Optional: Clear all grades (e.g., on student deletion)
    public void clearGrades() {
        studentGrades.clear();
    }
}
