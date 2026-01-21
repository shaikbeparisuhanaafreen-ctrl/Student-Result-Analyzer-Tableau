public class ResultCalculator {

    public static int calculateTotal(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total;
    }

    public static double calculatePercentage(int total, int subjects) {
        return (double) total / subjects;
    }

    public static double calculateCGPA(double percentage) {
        return percentage / 9.5;
    }

    public static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else return "Fail";
    }
}
