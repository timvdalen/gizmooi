package com.timvdalen.gizmooi;

/**
 * Represents a Photo's license
 * 
 * Currently, all licenses are CC 2.0
 */
public enum License{
	BY_NC_SA,
	BY_NC,
	BY_NC_ND,
	BY,
	BY_SA,
	BY_ND,
	INVALID;

	/**
	 * Parses the int value of a license into a License
	 * @param intVal as defined here <http://www.flickr.com/services/api/flickr.photos.licenses.getInfo.html>
	 * @return Corresponding License
	 */
	public static License parseInt(int intVal){
		switch(intVal){
		case 1:
			return BY_NC_SA;
		case 2:
			return BY_NC;
		case 3:
			return BY_NC_ND;
		case 4:
			return BY;
		case 5:
			return BY_SA;
		case 6:
			return BY_ND;
		default:
			return INVALID;
		}
	}
	
	/**
	 * Returns a user-facing description of the License
	 * @return user-facing description of the License
	 */
	public String toString(){
		switch(this){
		case BY_NC_SA:
			return "CC BY-NC-SA 2.0";
		case BY_NC:
			return "CC BY-NC 2.0";
		case BY_NC_ND:
			return "CC BY-NC-ND 2.0";
		case BY:
			return "CC BY 2.0";
		case BY_SA:
			return "CC BY-SA 2.0";
		case BY_ND:
			return "CC BY-ND 2.0";
		default:
			return "";
		}
	}
}
