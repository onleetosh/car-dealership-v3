package com.onleetosh.pluralsight.dao;

import com.onleetosh.pluralsight.contract.*;
import com.onleetosh.pluralsight.datamanager.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;

import static com.onleetosh.pluralsight.datamanager.DataManager.processConfiguredDataSource;

public class LeaseDao {

    public static void getLeaseContract(String[] args){
        try (BasicDataSource dataSource = processConfiguredDataSource(args)) {
            DataManager dm = new DataManager(dataSource);
            List<LeaseContract> leaseContracts = dm.getLeaseContractsFromDatabase();
            if (!leaseContracts.isEmpty()) {
                for (LeaseContract lease : leaseContracts) {
                    System.out.println(lease);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: ", e);
        }
    }
}
