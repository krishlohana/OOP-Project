public class Transcript {
    private String studentName;
    private String studentId;
    private double GPA;

    // Constructor that accepts a Student object
    public Transcript(Student student) {
        this.studentName = student.getName();
        this.studentId = student.getStudentId();
        this.GPA = student.calculateGPA(); // GPA is calculated from student's courses
    }

    // Method to generate transcript info as String
    public String generateTranscript(Student student) {
        StringBuilder transcript = new StringBuilder();
        transcript.append("----------- TRANSCRIPT -----------\n");
        transcript.append("Student ID: ").append(studentId).append("\n");
        transcript.append("Student Name: ").append(studentName).append("\n");
        transcript.append("Attendance: ").append(student.getAttendance()).append("%\n");
        transcript.append("\nCourses:\n");

        for (Course course : student.getCourses()) {
            transcript.append("- ")
                      .append(course.getCourseName())
                      .append(" (")
                      .append(course.getCreditHours())
                      .append(" credit hrs): Grade = ")
                      .append(course.getGrade())
                      .append("\n");
        }

        transcript.append("\nGPA: ").append(String.format("%.2f", GPA));
        return transcript.toString();
    }

    // Print the transcript to console
    public void printTranscript(Student student) {
        String fullTranscript = generateTranscript(student);
        System.out.println(fullTranscript);
    }
}
