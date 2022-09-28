package com.GEA.Scraping.Util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import com.GEA.Scraping.Model.Recipe;

public class ScrapingUtil {

	public Recipe getRecipe(String url) {
		Recipe recipe = new Recipe();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements ingredients = doc.body().getElementsByClass("ingredient-section");
		
		for (Element e : ingredients) {
			Element ingredientList = e.getElementsByTag("ul").get(0);
			for (Element i : ingredientList.getAllElements()) {
				if (!ingredientList.equals(i) && !i.hasAttr("href"))
					recipe.ingredients.add(i.text());
			}
		}

		Element instructions = doc.body().getElementsByClass("field field--recipe-steps").get(0);
		Element instructionsList = instructions.getElementsByTag("ol").get(0);

		for (Element i : instructionsList.getAllElements()) {
			if (!instructionsList.equals(i) && i.hasClass("field__item"))
				recipe.instructions.add(i.text());
		}

		return recipe;
	}

}
