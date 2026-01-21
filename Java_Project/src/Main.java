import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {
            // ---------- INPUT USING POPUPS ----------
            String id = JOptionPane.showInputDialog("Enter Student ID:");
            String name = JOptionPane.showInputDialog("Enter Student Name:");

            // Capitalize name
            name = name.substring(0,1).toUpperCase() + name.substring(1);

            int subjects = 5;
            int[] marks = new int[subjects];

            for (int i = 0; i < subjects; i++) {
                String input = JOptionPane.showInputDialog(
                        "Enter marks for Subject " + (i + 1) + ":");
                marks[i] = Integer.parseInt(input);
            }

            Student student = new Student(id, name, marks);

            // ---------- CALCULATIONS ----------
            int total = ResultCalculator.calculateTotal(marks);
            double percentage = ResultCalculator.calculatePercentage(total, subjects);
            double cgpa = ResultCalculator.calculateCGPA(percentage);
            String grade = ResultCalculator.calculateGrade(percentage);

            // ---------- SAVE DATA TO CSV ----------
            saveToCSV(student, total, percentage, cgpa, grade);

            // ---------- RESULT POPUP ----------
            String result =
                    "----- STUDENT RESULT -----\n" +
                    "Student ID   : " + student.id + "\n" +
                    "Name         : " + student.name + "\n" +
                    "Total Marks  : " + total + "\n" +
                    "Percentage   : " + String.format("%.2f", percentage) + "\n" +
                    "CGPA         : " + String.format("%.2f", cgpa) + "\n" +
                    "Grade        : " + grade;

            JOptionPane.showMessageDialog(null, result,
                    "Result", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid input! Please enter numeric marks only.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------- CSV METHOD ----------
    static void saveToCSV(Student s, int total,
                          double percentage, double cgpa, String grade) {

        try {
            File file = new File("student_results.csv");
            boolean fileExists = file.exists();

            FileWriter fw = new FileWriter(file, true);

            // Write header only once
            if (!fileExists) {
                fw.write("StudentID,Name,Total,Percentage,CGPA,Grade\n");
            }

            fw.write(
                    s.id + "," +
                    s.name + "," +
                    total + "," +
                    String.format("%.2f", percentage) + "," +
                    String.format("%.2f", cgpa) + "," +
                    grade + "\n"
            );

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
