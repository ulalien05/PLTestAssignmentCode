import java.io.*;

public class Solution {

    public static void main(String[] args) {
        File circleParams = new File(args[0]);
        File pointsParams = new File(args[1]);
        double x1;
        double y1;
        double radius;
        double x2;
        double y2;
        String point;
        try (BufferedReader circleReader = new BufferedReader(new FileReader(circleParams));
             BufferedReader pointReader = new BufferedReader(new FileReader(pointsParams))) {
            String[] circleCenter = circleReader.readLine().split(" ");
            radius = Double.parseDouble(circleReader.readLine());
            x1 = Double.parseDouble(circleCenter[0]);
            y1 = Double.parseDouble(circleCenter[1]);
            while ((point = pointReader.readLine()) != null) {
                String[] pointCoordinates = point.split(" ");
                x2 = Double.parseDouble(pointCoordinates[0]);
                y2 = Double.parseDouble(pointCoordinates[1]);
                System.out.println(positionRelatingToCircle(x1, y1, radius, x2, y2));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No file found, check the file path.");
            System.out.println(e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Failed to read the file content.");
            System.out.println(e.getMessage());
        }
    }

    public static int positionRelatingToCircle(double x1, double y1, double r, double x2, double y2){
        double r2 = Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
        if (r == r2)
            return 0;
        else if (r > r2)
            return 1;
        else
            return 2;
    }
}
