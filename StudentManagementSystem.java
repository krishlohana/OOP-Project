import java.util.ArrayList;

public class StudentManagementSystem {
    private ArrayList<Student> students;

    // Constructor
    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    // === CREATE ===
    public void addStudent(String id, String name, double attendance) {
        Student s = new Student(id, name, attendance);
        students.add(s);
        System.out.println("Student added successfully.");
    }

    // === READ ===
    public Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null; // not found
    }

    // === UPDATE ===
    public void updateAttendance(String id, double newAttendance) {
        Student s = findStudentById(id);
        if (s != null) {
            s.updateAttendance(newAttendance);
            System.out.println("Attendance updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void addCourseToStudent(String studentId, String courseName, int creditHours, double grade) {
        Student s = findStudentById(studentId);
        if (s != null) {
            Course c = new Course(courseName, creditHours);
            c.setGrade(grade);
            s.addCourse(c);
            System.out.println("Course added to student.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // === DELETE ===
    public void deleteStudent(String id) {
        Student s = findStudentById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // === TRANSCRIPT ===
    public void generateTranscript(String id) {
        Student s = findStudentById(id);
        if (s != null) {
            Transcript t = new Transcript(s);
            t.printTranscript(s);
        } else {
            System.out.println("Student not found.");
        }
    }

    // === LIST ALL STUDENTS ===
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    // === Getter for use in GUI or file saving ===
    public ArrayList<Student> getStudents() {
        return students;
    }
}
