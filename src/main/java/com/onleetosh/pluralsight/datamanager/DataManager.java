package com.onleetosh.pluralsight.datamanager;
import com.onleetosh.pluralsight.Vehicle;

import com.onleetosh.pluralsight.contract.*;
import com.onleetosh.pluralsight.util.Console;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {



    private final BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) {




        System.out.println("\nOptions " +
                "\n 1) View Lease Contracts " +
                "\n 2) View Sales Contracts ");

       int command = Console.PromptForInt("\n Enter 1 or 2");


        if (command == 1) {

            try (BasicDataSource dataSource = getConfiguredDataSource(args)) {
                DataManager dm = new DataManager(dataSource);
                List<LeaseContract> leaseContracts = dm.fetchLeaseContracts();
                if (!leaseContracts.isEmpty()) {
                    for (LeaseContract lease : leaseContracts) {
                        System.out.println(lease);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Database error: ", e);
            }
        }

        if(command == 2) {

            try (BasicDataSource dataSource = getConfiguredDataSource(args)) {
                DataManager dm = new DataManager(dataSource);
                List<SalesContract> salesContracts = dm.fetchSalesContracts();
                if (!salesContracts.isEmpty()) {
                    for (SalesContract sales : salesContracts) {
                        System.out.println(sales);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException("Database error: ", e);
            }

        }


    }


    public static BasicDataSource getConfiguredDataSource(String[] args){
        if (args.length != 3) {
            System.out.println(
                    "Application needs three arguments to run: " +
                            " <username> <password> <url>");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String sqlServerUrl = args[2];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(sqlServerUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

    }


    public List<LeaseContract> fetchLeaseContracts() {
        List<LeaseContract> contracts = new ArrayList<>();

        String query = """
                SELECT
                    LeaseContract.LeaseDate,
                    Vehicle.Vin, Vehicle.Year, Vehicle.Make, Vehicle.Model, Vehicle.VehicleType, Vehicle.Color, Vehicle.Odometer, Vehicle.Price,
                    CONCAT(Customer.FirstName, ' ', Customer.LastName) AS name,
                    Customer.Email,
                    LeaseContract.LeaseContractExpectedFinalValue AS EndValue,
                    LeaseContract.LeaseContractLeaseFee AS LeaseFee
                FROM LeaseContract
                JOIN Customer ON LeaseContract.CustomerID = Customer.CustomerID
                JOIN Vehicle ON LeaseContract.Vin = Vehicle.Vin;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet results = ps.executeQuery()) {

            while (results.next()) {
                // Create Vehicle object
                Vehicle vehicle = new Vehicle(
                        results.getInt("Vin"),
                        results.getInt("Year"),
                        results.getString("Make"),
                        results.getString("Model"),
                        results.getString("VehicleType"),
                        results.getString("Color"),
                        results.getInt("Odometer"),
                        results.getDouble("Price")
                );

                // Create LeaseContract object
                contracts.add(new LeaseContract(
                        results.getString("LeaseDate"),
                        results.getString("name"),
                        results.getString("Email"),
                        vehicle,
                        results.getDouble("EndValue"),
                        results.getDouble("LeaseFee")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching lease contracts: ", e);
        }

        return contracts;
    }

    public List<SalesContract> fetchSalesContracts() {
        List<SalesContract> contracts= new ArrayList<>();

        String query = """
                SELECT
                    SalesContract.SalesDate,
                    Vehicle.Vin, Vehicle.Year, Vehicle.Make, Vehicle.Model, Vehicle.VehicleType, Vehicle.Color, Vehicle.Odometer, Vehicle.Price,
                    CONCAT(Customer.FirstName, ' ', Customer.LastName) AS name,
                    Customer.Email,
                    SalesContract.SalesContractTaxes AS tax,
                    SalesContract.SalesContractRecordingFee AS recording,
                    SalesContract.SalesContractProcessingFee AS processing,
                    SalesContract.isFinance as finance
                FROM SalesContract
                JOIN Customer ON SalesContract.CustomerID = Customer.CustomerID
                JOIN Vehicle ON SalesContract.Vin = Vehicle.Vin;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet results = ps.executeQuery()) {

            while (results.next()) {
                // Create Vehicle object
                Vehicle vehicle = new Vehicle(
                        results.getInt("Vin"),
                        results.getInt("Year"),
                        results.getString("Make"),
                        results.getString("Model"),
                        results.getString("VehicleType"),
                        results.getString("Color"),
                        results.getInt("Odometer"),
                        results.getDouble("Price")
                );

                // Create LeaseContract object
               contracts.add(new SalesContract(
                       results.getString("SalesDate"),
                       results.getString("name"),
                       results.getString("Email"),
                       vehicle,
                       results.getDouble("tax"),
                       results.getDouble("recording"),
                       results.getDouble("processing"),
                       results.getBoolean("finance")


                       ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching sales contracts: ", e);
        }

        return contracts;
    }
}


