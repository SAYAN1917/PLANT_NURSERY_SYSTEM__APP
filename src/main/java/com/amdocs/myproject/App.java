package com.amdocs.myproject;

import java.sql.SQLException;

import java.util.Scanner;

import com.amdocs.myproject.exceptions.*;
import com.amdocs.myproject.entity.Plant;
import com.amdocs.myproject.dao.*;
import com.amdocs.myproject.dao.impl.*;


public class App 
{
		private static String jdbcurl = "oracle.jdbc.driver.OracleDriver";
		
    	private static Scanner scanner = new Scanner(System.in);

    	public static void main(String[] args) throws SystemException {
    		try {
    			Class.forName(jdbcurl);
    			while (true) {
    				System.out.println("\n 1. Add new plant");
    				System.out.println("\n 2. Update plant cost");
    				System.out.println("\n 3. Delete plant ");
    				System.out.println("\n 4. View all plants");
    				System.out.println("\n 5. Find plant by origin country name");
    				System.out.println("\n 6. Find outdoor plants which requires sunlight");
    				System.out.println("\n 7. Count plants by water supply frequency");
    				System.out.println("\n 8. Exit");
    				int ch = Integer.parseInt(scanner.nextLine());
    		
    			    switch (ch) {
    			
    			    	case 1:
    						addNewplant();
    						break;
    				    case 2:
    				    	modifyCost();
    				    	break;
    				    case 3:
    				    	deletePlant();
    				    	break;
    				    case 4:
    				    	viewAllPlants();
    				    	break;
    				    case 5:
    				    	viewByOriginCountryName();
    				    	break;
    				    case 6:
    				    	viewOutdoorPlantsWithSunlight();
    				    	break;
    				    case 7:
    				    	countPlantByWaterSupplyfreq();
    				    	break;
    				    case 8:
    				    	System.exit(0);
    				    	break;
    				    	
    				    }
    					
    					
    			}
    				    				
            } catch (SystemException e) {
    			System.out.println("The error is: " + e.getMessage()); 
    		} catch (Exception e) {

    			System.out.println("Error : " + e.getMessage());
    		}
    	}

    		private static Plant createPlant() throws SystemException  {
    			Plant obj = new Plant();
    			try {
    				System.out.println(" Enter the plant Name: ");
    				String plantName = scanner.nextLine();
    				obj.setPlantName(plantName);
    				
    				System.out.println(" Enter The Origin Country Name: ");
    				String countryName = scanner.nextLine();
    				obj.setOriginCountryName(countryName);
    				
    				System.out.println(" Sunlight required or Not: Y/N ");
    				String sunlight = scanner.nextLine();
    				if (sunlight.equalsIgnoreCase("Y"))
    				{
    					obj.setSunlightRequired(true);
    				}
    				else if(sunlight.equalsIgnoreCase("N"))
    				{
    				obj.setSunlightRequired(false);
    				}
    				else 
    				{
    					System.out.println(" Invalid user Input ");
    				}
    				
    				System.out.println("Enter the Plant Type : Indoor/Outdoor ");
    				String plantType = scanner.nextLine();
    				obj.setPantType(plantType);
    				
    				System.out.println("Enter Water Supply Frequency: daily/weekly/alternate days ");
    				String waterSupply = scanner.nextLine();
    				obj.setWaterSupplyFrequency(waterSupply);
    				
    				System.out.println("Enter the Plant Cost ");
    				double plantCost = scanner.nextDouble();
    				obj.setCost(plantCost);
    				
    			} catch (NumberFormatException e) {
    				throw new SystemException("Please Provide a number value\n " + e.getMessage());
    			}

    			return obj;
    		}

    		private static PlantDao dao = new PlantNurseryDaoImpl();

    		private static void addNewplant() {
    			Plant createPlant;
    			try {
    				createPlant = createPlant();
    				dao.addNewPlant(createPlant);
    			} catch (SQLException e) {
    				System.out.println(e);
    			}catch (SystemException e) {
    				System.out.println(e);
    			}

    		}    	
    		

    		private static void modifyCost() throws SystemException {
    			//Plant updateCost;
    			try {
    				System.out.println("Enter the Plant ID: ");
    				int ID = scanner.nextInt();
    				//obj.getPlantid(ID);
    				scanner.nextLine();
    				
    				System.out.println("Enter the New Cost: ");
    				double cost = scanner.nextInt();
    				 
    				scanner.nextLine();
    		
    				if(dao.updatePlantCost(ID,cost)) {
    					System.out.println("updated");
    				}else {
    					System.out.println("not updated");
    				}
    				
    			} catch (SQLException e) {
    				System.out.println(e);
    			}catch (NumberFormatException e) {
    					throw new SystemException("Please Provide a number value\n " + e.getMessage());
    				}


    		}    
    		
    		
    		private static void deletePlant() throws SystemException {
    			
    			try {
    				System.out.println("Enter your Plant ID: ");
    				int ID = scanner.nextInt();
    		       
    				dao.delete(ID);
    			} catch (SQLException e) {
    				System.out.println(e);
    			}
    			catch (NumberFormatException e) {
    				throw new SystemException("Please Provide a number value\n " + e.getMessage());
    			}

    		}

    		private static void viewAllPlants() throws SQLException, SystemException {
    			
    		
    			dao.showAllPlants();
    			
    	        
    			}
    		

    		private static void viewByOriginCountryName() throws SystemException, SQLException {
    			try {
    				System.out.println("Enter country name ");
    				String countryname = scanner.nextLine();

    				//scanner.nextLine();
    				dao.searchByOriginCountryName(countryname);
    			} catch (NumberFormatException e) {
    				throw new SystemException("Please Provide a number value\n " + e.getMessage());
    					
    					
    				}
    		}
    		
			
    		private static void viewOutdoorPlantsWithSunlight() throws SQLException, SystemException {
    			
    			
    			dao.searchOutdoorPlantsWithSunlight();

    		
    				
    			}
		
    		private static void countPlantByWaterSupplyfreq() throws SystemException, SQLException {
    			try {
    				System.out.println("Enter Water Supply Frequency for:Daily/Weekly/Alternate Days ");
    				String frequency = scanner.nextLine();

    				dao.countryPlantsByWaterSupplyFrequency(frequency);
    			}catch (NumberFormatException e) {
    				throw new SystemException("Please Provide a number value\n " + e.getMessage());
    					
    					
    				}
    		}

}

