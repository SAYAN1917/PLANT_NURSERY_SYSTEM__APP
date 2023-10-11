package com.amdocs.myproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amdocs.myproject.util.DbUtil;
import com.amdocs.myproject.dao.PlantDao;
import com.amdocs.myproject.entity.Plant;
import com.amdocs.myproject.exceptions.SystemException;


public class PlantNurseryDaoImpl implements PlantDao {

	@Override
	public int addNewPlant(Plant plant) throws SQLException {
	final String INSERT_PLANT="insert into Plants (Plant_Name, OriginCountry_Name, Sunlight_Required, "
			+ "Plant_Type,waterSupply_Frequency,Plant_Cost) "
			+ "values(?,?,?,?,?,?)";
	Connection connection = DbUtil.getConnection();
		
		PreparedStatement ps=connection.prepareStatement(INSERT_PLANT);
		// Set The value
		ps.setString(1, plant.getPlantName());
		ps.setString(2, plant.getOriginCountryName());
		ps.setBoolean(3,plant.isSunlightRequired());
		ps.setString(4, plant.getPantType());
		ps.setString(5, plant.getWaterSupplyFrequency());
		ps.setDouble(6, plant.getCost());
		
		int executeUpdate = ps.executeUpdate();
		ps.close();
		if(executeUpdate>0) {
			return 1;
		}
	
		return 0;
	}

	@Override
	public int delete(int plantId) throws SQLException {
		Connection connection=DbUtil.getConnection();
		String DELETE_PLANTS="delete from Plants where Plant_id=?";
		PreparedStatement ps=connection.prepareStatement(DELETE_PLANTS);
		// Set The value
		ps.setInt(1, plantId);
		int result = ps.executeUpdate();
		ps.close();
		return result;
	}

	@Override
	public boolean updatePlantCost(int plantId, double Plant_Cost) throws SQLException {
		System.out.println("entered in update");
		Connection connection=DbUtil.getConnection();
		String UPDATE_PLANTS="update Plants set Plant_Cost=? where Plant_id=?";
		PreparedStatement ps=connection.prepareStatement(UPDATE_PLANTS);
		// Set The value
		ps.setDouble(1, Plant_Cost);
		ps.setInt(2, plantId);
		connection.setAutoCommit(false);
		int result = ps.executeUpdate();
		System.out.println("after update query");
		connection.commit();
		if(result>0) {
			return true;
		}else {
			return false;
		}
		
	
	}

	@Override
	public List<String> showAllPlants() throws SQLException,SystemException {
		Connection connection=DbUtil.getConnection();
		try {
			
		String SHOW_ALL_PLANTS="select * from Plants";
		PreparedStatement ps=connection.prepareStatement(SHOW_ALL_PLANTS);
	
		//Execute Statement
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			int    PlantId= result.getInt("Plant_ID");
			String PlantName= result.getString("Plant_Name");
			String showCountryByOrigin= result.getString("OriginCountry_Name");
			String sunlightRequired= result.getString("Sunlight_Required");
			String waterSupplyFrequency= result.getString("waterSupply_Frequency");
			String cost= result.getString("Plant_Cost");
			
			System.out.println(" \n ");
			System.out.println("Plant ID: " +" " + PlantId + "\n" + "Plant Name: " + PlantName + "\n" +
			           "Origin Country: " + showCountryByOrigin + "\n" +
			           "Sunlight Required: " + sunlightRequired + "\n" +
			           "Water Supply Frequency: " + waterSupplyFrequency + "\n" +
			           "Plant Cost: " + cost  );
				}
		ps.close();
		}  catch (SQLException e) {
           throw new SystemException ("Error retrieving plants from the database: " + e.getMessage());
        }finally {
            //  To close the connection in the finally block to ensure proper resource management
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle any potential SQLException during closing, or log it if needed
                e.printStackTrace();
            }
        }

		
		return null;
	}


	@Override
	public List<String> searchByOriginCountryName(String originCountryName) throws SQLException,SystemException {
		Connection connection=DbUtil.getConnection();
		try {
			
		String SearchBy_By_Country="select * from Plants Where OriginCountry_Name=?";
		PreparedStatement ps=connection.prepareStatement(SearchBy_By_Country);
	
		ps.setString(1,originCountryName );
		//Execute Statement
		ResultSet result = ps.executeQuery();
		boolean found = false; // Flag to check if any results are found
		
		while(result.next()) {
			found = true;
			int    PlantId= result.getInt("Plant_ID");
			String PlantName= result.getString("Plant_Name");
			String showCountryByOrigin= result.getString("OriginCountry_Name");
			String sunlightRequired= result.getString("Sunlight_Required");
			String waterSupplyFrequency= result.getString("waterSupply_Frequency");
			String cost= result.getString("Plant_Cost");
			
			System.out.println(" \n ");
			System.out.println("Plant ID: " +" " + PlantId + "\n" + "Plant Name: " + PlantName + "\n" +
			           "Origin Country: " + showCountryByOrigin + "\n" +
			           "Sunlight Required: " + sunlightRequired + "\n" +
			           "Water Supply Frequency: " + waterSupplyFrequency + "\n" +
			           "Plant Cost: " + cost  );
				}
		
		ps.close();
		// If no results were found, throw a custom exception
        if (!found) {
            //throw new SystemException("No plants found for the specified origin country: " + originCountryName);
        	System.out.println("No Plant found from the country: "+ originCountryName);
        }
        
		}
		 catch (SQLException e) {
	           throw new SystemException ("Error searching for plants by origin country name: " + e.getMessage());
	        }finally {
	            //  To close the connection in the finally block to ensure proper resource management
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                // Handle any potential SQLException during closing, or log it if needed
	                e.printStackTrace();
	            }
	        }

		return null;
		
	}

	@Override
	public List<String> searchOutdoorPlantsWithSunlight() throws SQLException, SystemException {
		Connection connection=DbUtil.getConnection();
		try {
			
		String SearchBy_By_Country="SELECT * FROM Plants WHERE plant_type = 'Outdoor' AND sunlight_required = 1";
		PreparedStatement ps=connection.prepareStatement(SearchBy_By_Country);

		//Execute Statement
		ResultSet result = ps.executeQuery();
		boolean found = false;
		
		while(result.next()) {
			found=true;
			int    PlantId= result.getInt("Plant_ID");
			String PlantName= result.getString("Plant_Name");
			String showCountryByOrigin= result.getString("OriginCountry_Name");
			String sunlightRequired= result.getString("Sunlight_Required");
			String waterSupplyFrequency= result.getString("waterSupply_Frequency");
			String cost= result.getString("Plant_Cost");
			
			System.out.println(" \n ");
			System.out.println("Plant ID: " +" " + PlantId + "\n" + "Plant Name: " + PlantName + "\n" +
			           "Origin Country: " + showCountryByOrigin + "\n" +
			           "Sunlight Required: " + sunlightRequired + "\n" +
			           "Water Supply Frequency: " + waterSupplyFrequency + "\n" +
			           "Plant Cost: " + cost  );
		
		}
		ps.close();
		
		  if (!found) {
	            throw new SystemException("No outdoor plants with the specified sunlight requirement found.");
	        }

	    } catch (SQLException e) {
	        // Wrap the SQLException in your custom exception and throw it
	        throw new SystemException("Error searching for outdoor plants with sunlight requirement: " + e.getMessage());
	    } finally {
	        try {
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		return null;

		
	}

	@Override
	public int countryPlantsByWaterSupplyFrequency(String waterSupplyFrequency) throws SQLException, SystemException {
	    Connection connection = DbUtil.getConnection();
//	    String query = "SELECT WaterSupply_Frequency, COUNT(*) AS FrequencyCount " +
//	                   "FROM Plants " +
//	                   "WHERE WaterSupply_Frequency IN ('Daily', 'Weekly', 'Alternate Days') " +
//	                   "GROUP BY WaterSupply_Frequency " +
//	                   "ORDER BY WaterSupply_Frequency";
	    
	    int count=0;
	    try {
	    String query = "Select count(*) from plants where watersupply_frequency=?";
	    PreparedStatement ps=connection.prepareStatement(query);
	    
			ps.setString(1,waterSupplyFrequency);
	         ResultSet result = ps.executeQuery();
	          result.next();
	         
	          count=result.getInt(1);
	          if(count>0) {
	          System.out.println("Counted: " + count);}

	         
	         else {
	             // If no results were found, throw a custom exception

	             throw new SystemException("Water supply frequency not found in the database: " + waterSupplyFrequency);
	         }

		}  catch (SQLException e) {
            System.err.println("Error retrieving plants from the database: " + e.getMessage());
        }

		
		return count;
		
	}
}


