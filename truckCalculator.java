import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class truckCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // ----- Required inputs -----
        System.out.println("Enter line haul amount: ");
        double lineHaul = Double.parseDouble(scan.nextLine().trim());
        System.out.println("Enter fuel charge: ");
        double fuelCharge = Double.parseDouble(scan.nextLine().trim());

        // ----- Optional inputs (just press Enter to skip) -----
        System.out.println("Load name or customer (optional, press Enter to skip): ");
        String loadName = scan.nextLine().trim();
        System.out.println("Miles driven (optional, press Enter to skip): ");
        double miles = readOptionalNumber(scan.nextLine());
        System.out.println("Maintenance expense (optional, press Enter to skip): ");
        double maintenance = readOptionalNumber(scan.nextLine());
        System.out.println("Notes (optional, press Enter to skip): ");
        String notes = scan.nextLine().trim();

        // ----- Calculations (same as before) -----
        double lineAndFuel = lineHaul + fuelCharge;
        System.out.println("Here is the amount of the line haul + fuel charge: " + lineAndFuel);
        double afterRate = lineAndFuel * 1.5;
        System.out.println("Here is the amount after the insurance rate: " + afterRate);
        double trailerFee = lineHaul * 23;
        System.out.println("Here is the amount after multiplying brokerage fee: " + trailerFee);
        double total = afterRate + trailerFee;
        System.out.println("Here is the total amount. It is the total of the insurance rate and brokerage fee: " + total);

        if (miles > 0) {
            System.out.println("Total per mile: " + String.format("%.2f", total / miles));
        }

        // ----- Save to file -----
        saveToFile(LocalDate.now().toString(), loadName, lineHaul, fuelCharge,
                afterRate, trailerFee, total, miles, maintenance, notes);
    }

    // Blank (or not a number) counts as 0. A $ sign or commas are okay.
    static double readOptionalNumber(String text) {
        text = text.trim().replace("$", "").replace(",", "");
        if (text.isEmpty()) {
            return 0;
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            System.out.println("That was not a number, so it was left blank.");
            return 0;
        }
    }

    // Adds one line to loads.csv (opens in Excel). Creates the file with a header the first time.
    static void saveToFile(String date, String loadName, double lineHaul, double fuel,
                           double afterRate, double brokerage, double total,
                           double miles, double maintenance, String notes) {
        File file = new File("loads.csv");
        boolean newFile = !file.exists();
        try (PrintWriter out = new PrintWriter(new FileWriter(file, true))) {
            if (newFile) {
                out.println("Date,Load,Line Haul,Fuel,After Insurance,Brokerage,Total,Miles,Maintenance,Notes");
            }
            out.println(date + "," + quote(loadName) + "," + money(lineHaul) + "," + money(fuel) + ","
                    + money(afterRate) + "," + money(brokerage) + "," + money(total) + ","
                    + money(miles) + "," + money(maintenance) + "," + quote(notes));
            System.out.println("Saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not save to file: " + e.getMessage());
        }
    }

    static String money(double d) {
        return String.format(java.util.Locale.US, "%.2f", d);
    }

    // Wraps text in quotes so commas in names/notes don't break the CSV.
    static String quote(String s) {
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}