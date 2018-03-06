package ar.edu.unlp.lifia.ratatoullie.api.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenericValidator {

	protected Boolean isNotEmpty(String aString){
		return !aString.trim().isEmpty();
	}
	protected Boolean noWhite(String aString){
		return !aString.contains(" ");
	}
	protected Boolean isNotNull(Object object){
		return object!=null;
	}
	protected boolean isEmail(String email) {
		String patternEmail= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(patternEmail);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}
	protected boolean latitude(Double latitude){
		return isNotNull(latitude)&latitude<=90&latitude>=-90;
	}
	protected boolean longitude(Double longitude){
		return isNotNull(longitude)&longitude<=180&longitude>=-180;
	}
	protected boolean id(long id){
		return isNotNull(id)&id>0;
	}
	protected boolean date(long date){
		return date>0&isNotNull(date);
	}
	protected boolean quantity(int quantity) {
		return isNotNull(quantity)&quantity>0;
	}
}