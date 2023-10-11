package com.amdocs.myproject.entity;

public class Plant extends Object {
		
		private int plantId;
		private String plantName;
		private String originCountryName;
		private boolean sunlightRequired;
		private String waterSupplyFrequency;
		private String pantType;
		private double cost;
		
		
		public int getPlantid() {
			return plantId;
		}
		public void setPlantid(int plantid) {
			this.plantId = plantid;
		}
		public String getPlantName() {
			return plantName;
		}
		public void setPlantName(String plantName) {
			this.plantName = plantName;
		}
		public String getOriginCountryName() {
			return originCountryName;
		}
		public void setOriginCountryName(String originCountryName) {
			this.originCountryName = originCountryName;
		}
		public boolean isSunlightRequired() {
			return sunlightRequired;
		}
		public void setSunlightRequired(boolean sunlightRequired) {
			this.sunlightRequired = sunlightRequired;
		}
		
		public String getWaterSupplyFrequency() {
			return waterSupplyFrequency;
		}
		public void setWaterSupplyFrequency(String waterSupplyFrequency) {
			this.waterSupplyFrequency = waterSupplyFrequency;
		}
		public String getPantType() {
			return pantType;
		}
		public void setPantType(String pantType) {
			this.pantType = pantType;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "Plant [plantid=" + plantId + ", plantName=" + plantName + ", originCountryName=" + originCountryName
					+ ", sunlightRequired=" + sunlightRequired + ", waterSupplyFrequency=" + waterSupplyFrequency
					+ ", pantType=" + pantType + ", cost=" + cost + "]";
		}
		
		
		
	}

