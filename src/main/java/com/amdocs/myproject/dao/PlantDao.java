package com.amdocs.myproject.dao;

import java.sql.SQLException;
import java.util.List;

import com.amdocs.myproject.entity.Plant;
import com.amdocs.myproject.exceptions.SystemException;


public interface PlantDao {
	
    int addNewPlant(Plant plant) throws SQLException,SystemException ;
    int delete(int plantId) throws SQLException,SystemException;
    boolean updatePlantCost(int plantId,double cost) throws SQLException,SystemException;
    List<String> showAllPlants() throws SQLException,SystemException;
    List<String> searchByOriginCountryName(String originCountryName) throws SQLException, SystemException;
    List<String> searchOutdoorPlantsWithSunlight() throws SQLException, SystemException;
    int countryPlantsByWaterSupplyFrequency(String waterSupplyFrequency) throws SQLException, SystemException;
    
    
    
}


