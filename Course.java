public class Course {
    private String courseName;
    private int creditHours;
    private double grade;

    public Course(String courseName, int creditHours) {
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.grade = 0.0;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 4.0) {
            this.grade = grade;
        } else {
            System.out.println("Invalid grade! Must be between 0.0 and 4.0.");
        }
    }

    public String getCourseInfo() {
        return "Course: " + courseName + ", Credit Hours: " + creditHours + ", Grade: " + grade;
    }

    public String getCourseName() { return courseName; }
    public int getCreditHours() { return creditHours; }
    public double getGrade() { return grade; }
}
