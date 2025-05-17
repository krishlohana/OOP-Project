public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String studentId, String name, double attendance) {
        super(studentId, name, attendance);
    }

    // Override GPA calculation if needed (same for now)
    @Override
    public double calculateGPA() {
        return super.calculateGPA(); // customize logic for undergraduates here
    }
}
