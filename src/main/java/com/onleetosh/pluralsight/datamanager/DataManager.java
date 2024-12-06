package com.onleetosh.pluralsight.datamanager;


import com.onleetosh.pluralsight.dealership.*;
import com.onleetosh.pluralsight.util.*;
import com.onleetosh.pluralsight.contract.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

import java.util.*;

public class DataManager {



    private final BasicDataSource dataSource;

    public DataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Dealership> getDealershipFromDatabase(){

        return null;
    }


    public static BasicDataSource processConfiguredDataSource(String[] args){
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


    public List<LeaseContract> getLeaseContractsFromDatabase() {
        List<LeaseContract> contracts = new ArrayList<>();

        String query = SQL.queryLeaseContract();

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
                        results.getString("date"),
                        results.getString("name"),
                        results.getString("email"),
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
    public List<SalesContract> getSalesContractsFromDatabase() {
        List<SalesContract> contracts= new ArrayList<>();

        String query = SQL.querySalesContract();

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
                        results.getString("date"),
                        results.getString("name"),
                        results.getString("email"),
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

    // TODO: change name to processColorRequest()

    public List<Vehicle> getVehicleFromDatabaseByColor(String value) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();


        //do the stuff with the datasource here...

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByColor());)
            {
                ps.setString(1, value);
                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  List<Vehicle> getVehicleFromDatabaseByVehicleType(String value) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();


        //do the stuff with the datasource here...

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByType());)
            {
                ps.setString(1, value);
                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  List<Vehicle> getVehicleFromDatabaseByMakeModel(String value) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByMakeModel());)
            {
                ps.setString(1, value);

                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public   List<Vehicle> getVehicleFromDatabaseByPriceRange(double min, double max) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByPriceRange());)
            {
                ps.setDouble(1, min);
                ps.setDouble(2, max);
                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public   List<Vehicle> getVehicleFromDatabaseByYear(int min, int max) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();


        //do the stuff with the datasource here...

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByYear());)
            {
                ps.setInt(1, min);
                ps.setInt(2, max);

                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public   List<Vehicle> getVehicleFromDatabaseByMileage(int min, int max) {
        ArrayList<Vehicle> vehicle = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryVehicleByMileage());)
            {
                ps.setInt(1, min);
                ps.setInt(1, max);

                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public  List<Vehicle> getAllVehicleFromDatabase() {
        ArrayList<Vehicle> vehicle = new ArrayList<>();

        //do the stuff with the datasource here...

        try(Connection connection = dataSource.getConnection();){
            try(PreparedStatement ps = connection.prepareStatement(
                    SQL.queryAllVehicles());)
            {
                try(ResultSet results = ps.executeQuery())
                {
                    //loop and return all results
                    while(results.next()){
                        vehicle.add( new Vehicle(
                                results.getInt(1),
                                results.getInt(2),
                                results.getString(3),
                                results.getString(4),
                                results.getString(5),
                                results.getString(6),
                                results.getInt(7),
                                results.getDouble(8)

                        ));
                    }
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


