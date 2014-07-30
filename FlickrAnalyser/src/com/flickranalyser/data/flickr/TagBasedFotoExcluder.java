package com.flickranalyser.data.flickr;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.pojo.PointOfInterest;

public class TagBasedFotoExcluder implements IFotoExcluder {

	private static final Logger LOGGER = Logger.getLogger(TagBasedFotoExcluder.class.getName());
	private final Set<String> unwantedTags;
	private static int filterCounter = 0;
	private boolean onlyExcludedTags;

	public TagBasedFotoExcluder(boolean onlyExcludedTags) {
		this.onlyExcludedTags = onlyExcludedTags;
		unwantedTags = new HashSet<String>();
		unwantedTags.add("WOMEN");
		unwantedTags.add("WOMAN");
		unwantedTags.add("MAN");
		unwantedTags.add("MEN");
		unwantedTags.add("SEXY");
		unwantedTags.add("GAY");
		unwantedTags.add("KISS");
		unwantedTags.add("LESBIAN");
		unwantedTags.add("FLOWER");
		unwantedTags.add("FACE");
		unwantedTags.add("ROOM");
		unwantedTags.add("FURNITURE");
		unwantedTags.add("CAR");
		unwantedTags.add("MUJER");
		unwantedTags.add("CATS");
		unwantedTags.add("CAT");

		unwantedTags.add("PEDICUREDTOES");
		unwantedTags.add("PEDICURE");
		unwantedTags.add("FEET");
		unwantedTags.add("NAILS");
		unwantedTags.add("FINGER");
		unwantedTags.add("FACE");
		unwantedTags.add("FETISH");
		unwantedTags.add("NYLON");
		unwantedTags.add("STOCKINGS");
		unwantedTags.add("LINGERIE");
		unwantedTags.add("FOOTFETISH");
		unwantedTags.add("TOES");
		unwantedTags.add("TOENAILS");
		unwantedTags.add("FEETINHEELS");
		unwantedTags.add("FEETINHIGHHEELS");
		unwantedTags.add("THONGS");
		unwantedTags.add("SEXYLEGS");
		unwantedTags.add("PUSSY");
		unwantedTags.add("SHOWGIRLS");
		unwantedTags.add("PROMOGIRLS");
		unwantedTags.add("MODELS");
		unwantedTags.add("PROMOTIONALMODELS");
		unwantedTags.add("HEELS");
		unwantedTags.add("GIRL");
		unwantedTags.add("BOY");
		unwantedTags.add("CHILDREN");
		unwantedTags.add("ADULT");
		unwantedTags.add("MILF");
		/** LOL I learned something today - Big Beautiful Women HAHAHHA */
		unwantedTags.add("BBW");
		unwantedTags.add("FAT");
		unwantedTags.add("BLONDE");
		unwantedTags.add("TIT");
		unwantedTags.add("TITS");
		unwantedTags.add("BOOBS");
		unwantedTags.add("SKIN");
		unwantedTags.add("BRA");
		unwantedTags.add("NAKED");
		unwantedTags.add("JENNIFERLOPEZ");
		unwantedTags.add("MODEL");
		unwantedTags.add("FEMALE");
		unwantedTags.add("MALE");
		unwantedTags.add("PENIS");
		unwantedTags.add("VAGINA");
		unwantedTags.add("CHEERLEADERS");
		unwantedTags.add("CHEERLEADER");
		unwantedTags.add("PET");
		unwantedTags.add("DOG");
		unwantedTags.add("KITTY");
		unwantedTags.add("KID");
		unwantedTags.add("KIDS");


		//TODO COS DVV: TO DISCUSS
		unwantedTags.add("HOTEL");
		unwantedTags.add("CAKE");
		unwantedTags.add("MUFFIN");
		unwantedTags.add("LOVE");

		unwantedTags.add("BIRD");

		unwantedTags.add("FASHION");
		unwantedTags.add("WEDDING");
		unwantedTags.add("BEAUTY");
		unwantedTags.add("MAKEUP");
		unwantedTags.add("DRESS");
		unwantedTags.add("SKIRT");
		unwantedTags.add("UPSKIRT");
		unwantedTags.add("BODY");
		unwantedTags.add("MUSCLE");
		unwantedTags.add("NIPPLE");
		unwantedTags.add("PORTRAIT");

		unwantedTags.add("BABY");
		unwantedTags.add("BABYS");
		unwantedTags.add("BABIES");

		unwantedTags.add("HIGHHEEL");
		unwantedTags.add("HIGHHEELS");
		unwantedTags.add("CAMERA");

	}


	private boolean isFreeOfUnwantedTags(PointOfInterest poi) {
		return Collections.disjoint(unwantedTags, poi.getTags());
	}

	private boolean containsUnwantedTags(PointOfInterest poi) {
		return !Collections.disjoint(unwantedTags, poi.getTags());
	}

	@Override
	public boolean isFotoToExclude(PointOfInterest poi) {
		if (onlyExcludedTags){
			if (containsUnwantedTags(poi)) {	
				LOGGER.log(Level.INFO, "CONTAINS UNWANTED TAGS");
				return false;
			}
			return true;
		}else{

			if (isFreeOfUnwantedTags(poi)) {	
				return false;
			}else{
				filterCounter++;
				LOGGER.log(Level.INFO, "I FILTERED: " + filterCounter + " out of the List");
			}
			return true;
		}


	}
}
