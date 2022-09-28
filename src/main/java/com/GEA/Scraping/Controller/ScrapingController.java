package com.GEA.Scraping.Controller;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.GEA.Scraping.Model.Recipe;
import com.GEA.Scraping.Model.RecipeDTO;
import com.GEA.Scraping.Util.ScrapingUtil;



@Controller
public class ScrapingController {
	
	@RequestMapping(value = "scrape", method = RequestMethod.POST)
	@ResponseBody
	public RecipeDTO getUrl(@RequestBody String body) {
		String url = getUrlFromBody(body);
		ScrapingUtil sUtil = new ScrapingUtil();
		Recipe recipe = sUtil.getRecipe(url);
		RecipeDTO recipeDTO = new RecipeDTO();
		recipeDTO.recipe = recipe;
		return recipeDTO;
	}
	
	
	private String getUrlFromBody(String body) {
		JSONParser parser = new JSONParser();  
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(body);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
		String url = (String)json.get("url");
		
		return url;
	}

}
