package com.onleetosh.pluralsight.dao;

import com.onleetosh.pluralsight.datamanager.*;
import com.onleetosh.pluralsight.dealership.*;
import com.onleetosh.pluralsight.util.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;

import static com.onleetosh.pluralsight.datamanager.DataManager.processConfiguredDataSource;


public class VehicleDao {

    /**
     * method designed to print all vehicle database objects
     */
    public static void getAllVehicleDao(String[] args){

        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getAllVehicleFromDatabase();
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }
    public static void getDaoByColor(String[] args){
        String input = Console.PromptForString("Select a color ");
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByColor(input);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }


    public static void getDaoByVehicleType(String[] args){

        String input = Console.PromptForString("Enter Vehicle Type ");
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByVehicleType(input);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }

    }

    public static void getDaoByPriceRange(String[] args){
        double  min = Console.PromptForDouble("Enter min ");
        double max = Console.PromptForDouble("Enter Max");
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByPriceRange(min, max);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }

    }

    public static void getDaoByMileageRange(String[] args){
        int min = Console.PromptForInt("Select a min ");
        int max = Console.PromptForInt("Select a max ");
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByMileage(min, max);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }

    }
    public static void getDaoByYearRange(String[] args){

        int min = Console.PromptForInt("Enter min ");
        int max = Console.PromptForInt("Enter max");
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByYear(min, max);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }

    }

    public static void getDaoByMakeModel(String[] args) {
        String input = Console.PromptForString("Enter Make/Model Type ");

        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<Vehicle> vehicles = dm.getVehicleFromDatabaseByMakeModel(input);
            if (!vehicles.isEmpty()) {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }


    }






}
