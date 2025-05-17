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
        JButton saveBtn = new JButton("Save to File");
        JButton loadBtn = new JButton("Load from File");
        JButton exitBtn = new JButton("Exit");

        // Add all buttons
        panel.add(addStudentBtn);
        panel.add(viewStudentsBtn);
        panel.add(addCourseBtn);
        panel.add(transcriptBtn);
        panel.add(saveBtn);
        panel.add(loadBtn);
        panel.add(exitBtn);

        // Add listeners
        addStudentBtn.addActionListener(e -> addStudent());
        viewStudentsBtn.addActionListener(e -> viewStudents());
        addCourseBtn.addActionListener(e -> addCourse());
        transcriptBtn.addActionListener(e -> generateTranscript());
        saveBtn.addActionListener(e -> sms.saveToFile("students.txt"));
        loadBtn.addActionListener(e -> sms.loadFromFile("students.txt"));
        exitBtn.addActionListener(e -> System.exit(0));

        frame.add(panel);
        frame.setVisible(true);
    }

    // Function to add student using input dialogs
    private void addStudent() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        String name = JOptionPane.showInputDialog("Enter Student Name:");
        double attendance = Double.parseDouble(JOptionPane.showInputDialog("Enter Attendance %:"));
        sms.addStudent(id, name, attendance);
    }

    // Function to view students in a dialog
    private void viewStudents() {
        StringBuilder info = new StringBuilder();
        for (Student s : sms.getStudents()) {
            info.append(s).append("\n");
        }
        JOptionPane.showMessageDialog(null, info.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    // Function to add a course to a student
    private void addCourse() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        String courseName = JOptionPane.showInputDialog("Enter Course Name:");
        int creditHours = Integer.parseInt(JOptionPane.showInputDialog("Enter Credit Hours:"));
        double grade = Double.parseDouble(JOptionPane.showInputDialog("Enter Grade (0.0 - 4.0):"));
        sms.addCourseToStudent(id, courseName, creditHours, grade);
    }

    // Function to generate transcript
    private void generateTranscript() {
        String id = JOptionPane.showInputDialog("Enter Student ID:");
        Student s = sms.findStudentById(id);
        if (s != null) {
            Transcript t = new Transcript(s);
            JOptionPane.showMessageDialog(null, t.generateTranscript(s), "Transcript", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    // Main method to run GUI
    public static void main(String[] args) {
        new StudentGUI();
    }
}
