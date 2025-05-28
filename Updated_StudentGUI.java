import javax.swing.*;
import java.awt.event.*;

public class StudentGUI {
    private StudentManagementSystem sms;

    public StudentGUI() {
        sms = new StudentManagementSystem();
        createMainMenu();
    }

    private void createMainMenu() {
        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JButton addStudentBtn = new JButton("Add Student");
        JButton viewStudentsBtn = new JButton("View All Students");
        JButton addCourseBtn = new JButton("Add Course to Student");
        JButton transcriptBtn = new JButton("Generate Transcript");
        JButton deleteStudentBtn = new JButton("Delete Student");
        JButton updateAttendanceBtn = new JButton("Update Attendance");
        JButton saveBtn = new JButton("Save to File");
        JButton loadBtn = new JButton("Load from File");
        JButton exitBtn = new JButton("Exit");

        panel.add(addStudentBtn);
        panel.add(viewStudentsBtn);
        panel.add(addCourseBtn);
        panel.add(transcriptBtn);
        panel.add(deleteStudentBtn);
        panel.add(updateAttendanceBtn);
        panel.add(saveBtn);
        panel.add(loadBtn);
        panel.add(exitBtn);

        addStudentBtn.addActionListener(e -> addStudent());
        viewStudentsBtn.addActionListener(e -> viewStudents());
        addCourseBtn.addActionListener(e -> addCourse());
        transcriptBtn.addActionListener(e -> generateTranscript());
        deleteStudentBtn.addActionListener(e -> deleteStudent());
        updateAttendanceBtn.addActionListener(e -> updateAttendance());
        saveBtn.addActionListener(e -> sms.saveToFile("students.txt"));
        loadBtn.addActionListener(e -> sms.loadFromFile("students.txt"));
        exitBtn.addActionListener(e -> System.exit(0));

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addStudent() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        String name = JOptionPane.showInputDialog("Enter Student Name:");
        double attendance;

        try {
            attendance = Double.parseDouble(JOptionPane.showInputDialog("Enter Attendance % (0-100):"));
            if (attendance < 0 || attendance > 100) {
                JOptionPane.showMessageDialog(null, "❌ Invalid attendance. Must be between 0 and 100.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid attendance input.");
            return;
        }

        sms.addStudent(id, name, attendance);
        JOptionPane.showMessageDialog(null, "✅ Student added successfully.");
    }

    private void viewStudents() {
        StringBuilder info = new StringBuilder();
        for (Student s : sms.getStudents()) {
            info.append(s).append("\n");
        }
        JOptionPane.showMessageDialog(null, info.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addCourse() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        String courseName = JOptionPane.showInputDialog("Enter Course Name:");
        int creditHours;
        double grade;

        try {
            creditHours = Integer.parseInt(JOptionPane.showInputDialog("Enter Credit Hours (1-3):"));
            if (creditHours <= 0 || creditHours > 3) {
                JOptionPane.showMessageDialog(null, "❌ Credit hours must be between 1 and 3.");
                return;
            }
            grade = Double.parseDouble(JOptionPane.showInputDialog("Enter Grade (0.0 - 4.0):"));
            if (grade < 0.0 || grade > 4.0) {
                JOptionPane.showMessageDialog(null, "❌ Grade must be between 0.0 and 4.0.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "❌ Invalid input for credit hours or grade.");
            return;
        }

        sms.addCourseToStudent(id, courseName, creditHours, grade);
        JOptionPane.showMessageDialog(null, "✅ Course added to student (if student exists).");
    }

    private void generateTranscript() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        Student s = sms.findStudentById(id);
        if (s != null) {
            Transcript t = new Transcript(s);
            JOptionPane.showMessageDialog(null, t.generateTranscript(s), "Transcript", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "❌ Student not found.");
        }
    }

    private void deleteStudent() {
        String id = JOptionPane.showInputDialog("Enter Student ID to delete:");
        Student s = sms.findStudentById(id);
        if (s != null) {
            sms.deleteStudent(id);
            JOptionPane.showMessageDialog(null, "✅ Student '" + s.getName() + "' deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "❌ Student ID not found.");
        }
    }

    private void updateAttendance() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        Student s = sms.findStudentById(id);
        if (s != null) {
            try {
                double newAttendance = Double.parseDouble(JOptionPane.showInputDialog("Enter New Attendance % (0-100):"));
                if (newAttendance < 0 || newAttendance > 100) {
                    JOptionPane.showMessageDialog(null, "❌ Attendance must be between 0 and 100.");
                    return;
                }
                sms.updateAttendance(id, newAttendance);
                JOptionPane.showMessageDialog(null, "✅ Attendance updated for " + s.getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "❌ Invalid input for attendance.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "❌ Student ID not found.");
        }
    }

    public static void main(String[] args) {
        new StudentGUI();
    }
}
