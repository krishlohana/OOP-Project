public class Course {
    private String courseName;
    private int creditHours;
    private double grade;  // This can represent the student's grade in this course

    // Constructor
    public Course(String courseName, int creditHours) {
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.grade = 0.0; // default until set
    }

    // Set the grade
    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 4.0) {
            this.grade = grade;
        } else {
            System.out.println("Invalid grade! Must be between 0.0 and 4.0.");
        }
    }

    // Get course details
    public String getCourseInfo() {
        return "Course: " + courseName + ", Credit Hours: " + creditHours + ", Grade: " + grade;
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public double getGrade() {
        return grade;
    }
}
