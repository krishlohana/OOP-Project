import java.util.ArrayList;

public class Student {
    private String studentId;
    private String name;
    private double attendance;
    private ArrayList<Double> grades;

    // Constructor
    public Student(String studentId, String name, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.attendance = attendance;
        this.grades = new ArrayList<>();
    }

    // Add a grade
    public void addGrade(double grade) {
        if (grade >= 0.0 && grade <= 4.0) {
            grades.add(grade);
        } else {
            System.out.println("Invalid grade! Must be between 0.0 and 4.0.");
        }
    }

    // Update attendance
    public void updateAttendance(double newAttendance) {
        if (newAttendance >= 0 && newAttendance <= 100) {
            this.attendance = newAttendance;
        } else {
            System.out.println("Invalid attendance! Must be between 0 and 100.");
        }
    }

    // Calculate GPA
    public double calculateGPA() {
        if (grades.isEmpty()) return 0.0;
        double total = 0.0;
        for (double g : grades) {
            total += g;
        }
        return total / grades.size();
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getAttendance() {
        return attendance;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    // To display student info
    public String toString() {
        return "ID: " + studentId + ", Name: " + name +
               ", Attendance: " + attendance + "%, GPA: " + String.format("%.2f", calculateGPA());
    }
}
