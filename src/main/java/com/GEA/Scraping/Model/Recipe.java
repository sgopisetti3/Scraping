package com.GEA.Scraping.Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	public List<String> ingredients;
	public List<String> instructions;
	
	public Recipe() {
		ingredients = new ArrayList<String>();
		instructions = new ArrayList<String>();
	}

}
