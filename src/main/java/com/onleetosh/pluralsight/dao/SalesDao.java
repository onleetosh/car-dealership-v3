package com.onleetosh.pluralsight.dao;

import com.onleetosh.pluralsight.contract.SalesContract;
import com.onleetosh.pluralsight.datamanager.DataManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;

import static com.onleetosh.pluralsight.datamanager.DataManager.processConfiguredDataSource;

public class SalesDao {

    public static void getSalesContract(String[] args){
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<SalesContract> salesContracts = dm.getSalesContractsFromDatabase();
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
