# TruckCalculator

A simple Java command line program that replaces manual math when calculating a load's payment. Enter the line haul and fuel charge, and the program calculates the rest automatically.

## What It Does

The program asks for two numbers:

1. **Line haul amount**
2. **Fuel charge**

It then calculates and prints:

| Step | Result | Formula |
|------|--------|---------|
| 1 | Line haul + fuel charge | `lineHaul + fuelCharge` |
| 2 | Amount after the insurance rate | `(lineHaul + fuelCharge) * 1.5` |
| 3 | Brokerage fee | `lineHaul * 23` |
| 4 | Final total | `afterRate + brokerageFee` |

## Requirements

- Java Development Kit (JDK) 8 or newer

To check whether Java is installed, run:

```
java -version
```

## How to Run

1. Save the code as `truckCalculator.java`.
2. Open a terminal in the folder containing the file.
3. Compile it:

   ```
   javac truckCalculator.java
   ```

4. Run it:

   ```
   java truckCalculator
   ```

## Example

```
Enter line haul amount:
1000
Enter fuel charge:
200
Here is the amount of the line haul + fuel charge: 1200.0
Here is the amount after the insurance rate: 1800.0
Here is the amount after multiplying brokerage fee: 23000.0
Here is the total amount. It is the total of the insurance rate and brokerage fee: 24800.0
```

## Tech Used

- Java
- `java.util.Scanner` for user input
