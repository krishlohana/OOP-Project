import java.util.ArrayList;

public class Student {
    private String studentId;
    private String name;
    private double attendance;
    private ArrayList<Course> courses;

    public Student(String studentId, String name, double attendance) {
        this.studentId = studentId;
        this.name = name;
        this.attendance = attendance;
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void updateAttendance(double newAttendance) {
        if (newAttendance >= 0 && newAttendance <= 100) {
            this.attendance = newAttendance;
        } else {
            System.out.println("Invalid attendance! Must be between 0 and 100.");
        }
    }

    public double calculateGPA() {
        if (courses.isEmpty()) return 0.0;
        double totalPoints = 0.0;
        int totalCredits = 0;
        for (Course c : courses) {
            totalPoints += c.getGrade() * c.getCreditHours();
            totalCredits += c.getCreditHours();
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public double getAttendance() { return attendance; }
    public ArrayList<Course> getCourses() { return courses; }

    public String toString() {
        return "ID: " + studentId + ", Name: " + name + ", Attendance: " + attendance + "%, GPA: " + String.format("%.2f", calculateGPA());
    }
}
