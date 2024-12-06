package com.onleetosh.pluralsight.util;

public class SQL {


    public static String querySalesContract(){
        return """
                SELECT
                    SalesContract.DealershipID as id,
                    SalesContract.SalesDate as date,
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
    }
    public static String queryLeaseContract(){
        return """
                SELECT
                    LeaseContract.DealershipID as id,
                    LeaseContract.LeaseDate as date,
                    Vehicle.Vin, Vehicle.Year, Vehicle.Make, Vehicle.Model, Vehicle.VehicleType, Vehicle.Color, Vehicle.Odometer, Vehicle.Price,
                    CONCAT(Customer.FirstName, ' ', Customer.LastName) AS name,
                    Customer.Email,
                    LeaseContract.LeaseContractExpectedFinalValue AS EndValue,
                    LeaseContract.LeaseContractLeaseFee AS LeaseFee
                FROM LeaseContract
                JOIN Customer ON LeaseContract.CustomerID = Customer.CustomerID
                JOIN Vehicle ON LeaseContract.Vin = Vehicle.Vin;
                """;
    }


    public static String queryVehicleByColor(){
        return "SELECT * FROM Vehicle WHERE Color = ?";
    }

    public static String queryVehicleByYear(){
        return "SELECT * FROM Vehicle WHERE Year BETWEEN ? AND ? ORDER BY  Year";
    }

    public static String queryVehicleByType(){
        return "SELECT * FROM Vehicle WHERE VehicleType = ?";
    }

    //TODO: fix query
    public static String queryVehicleByMakeModel(){
        return "SELECT Make, Model FROM Vehicle WHERE Make = ? Or Model = ?";
    }

    public static String queryVehicleByPriceRange(){
        return "SELECT * FROM Vehicle WHERE Price BETWEEN ? AND ? ORDER BY Price";

    }

    public static String queryVehicleByMileage(){
        return "SELECT * FROM Vehicle WHERE Odometer BETWEEN ? AND ? ORDER BY Odometer";

    }


    public static String queryAllVehicles(){
        return "SELECT * FROM Vehicle";
    }

}
