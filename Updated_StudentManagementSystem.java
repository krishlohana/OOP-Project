import java.util.*;
import java.io.*;

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
        System.out.println("✅ Student added successfully.");
    }

    public void addCourseToStudent(String studentId, String courseName, int creditHours, double grade) {
        Student s = findStudentById(studentId);
        if (s != null) {
            Course c = new Course(courseName, creditHours);
            c.setGrade(grade);
            s.addCourse(c);
            System.out.println("✅ Course added to student.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // === READ ===
    public Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠ No students found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // === UPDATE ===
    public void updateAttendance(String id, double newAttendance) {
        Student s = findStudentById(id);
        if (s != null) {
            s.updateAttendance(newAttendance);
            System.out.println("✅ Attendance updated.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // === DELETE ===
    public void deleteStudent(String id) {
        Student s = findStudentById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("✅ Student deleted.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // === TRANSCRIPT ===
    public void generateTranscript(String id) {
        Student s = findStudentById(id);
        if (s != null) {
            Transcript t = new Transcript(s);
            t.printTranscript(s);
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // === FILE HANDLING ===

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.write("STUDENT|" + s.getStudentId() + "|" + s.getName() + "|" + s.getAttendance());
                writer.newLine();
                for (Course c : s.getCourses()) {
                    writer.write("COURSE|" + c.getCourseName() + "|" + c.getCreditHours() + "|" + c.getGrade());
                    writer.newLine();
                }
                writer.write("END");
                writer.newLine();
            }
            System.out.println("✅ Data saved to file.");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Student currentStudent = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                switch (parts[0]) {
                    case "STUDENT":
                        String id = parts[1];
                        String name = parts[2];
                        double attendance = Double.parseDouble(parts[3]);
                        currentStudent = new Student(id, name, attendance);
                        students.add(currentStudent);
                        break;

                    case "COURSE":
                        if (currentStudent != null) {
                            String courseName = parts[1];
                            int creditHours = Integer.parseInt(parts[2]);
                            double grade = Double.parseDouble(parts[3]);
                            Course course = new Course(courseName, creditHours);
                            course.setGrade(grade);
                            currentStudent.addCourse(course);
                        }
                        break;

                    case "END":
                        currentStudent = null;
                        break;
                }
            }

            System.out.println("✅ Data loaded from file.");
        } catch (IOException e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }

    // Getter (optional)
    public ArrayList<Student> getStudents() {
        return students;
    }
}
