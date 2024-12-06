package com.onleetosh.pluralsight.util;

import com.onleetosh.pluralsight.contract.*;
import com.onleetosh.pluralsight.dao.*;
import com.onleetosh.pluralsight.dealership.*;

import java.util.ArrayList;

public class UI {


    public static Dealership currentDealership;
    public static ArrayList<Contract> contracts;

    /**
     * UI Constructor initializes and loads the file content to ensure the program starts with
     * a dealership with inventory and a list of contract (objects)
     */
    public UI(){
        // currentDealership = RequestData.getDealershipFromDatabase();
        // contracts = ContractFileManager.getContractsFromCSV(contractFileName);
    }

    /**
     * Display user request options
     */


    public static void dealershipDisplay(String[] args){

        String choices = """
                Please select from the following choices:
                1 - Find vehicles within a price range
                2 - Find vehicles by make / model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                10 - Sell/Lease a vehicle
                11 - Display lease contracts
                12 - Display sales contracts
           
                0 - Quit

                >>>\s""";

        int command = Console.PromptForInt(choices);

        switch (command){

            case 1 -> VehicleDao.getDaoByPriceRange(args);
            case 2 -> VehicleDao.getDaoByMakeModel(args);
            case 3 -> VehicleDao.getDaoByYearRange(args);
            case 4 -> VehicleDao.getDaoByColor(args);
            case 5 -> VehicleDao.getDaoByMileageRange(args);
            case 6 -> VehicleDao.getDaoByVehicleType(args);
            case 7 -> VehicleDao.getAllVehicleDao(args);
            case 11 -> LeaseDao.getLeaseContract(args);
            case 12 -> SalesDao.getSalesContract(args);
        }

    }

}
