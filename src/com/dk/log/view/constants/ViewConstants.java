package com.dk.log.view.constants;

/**
 * Created by dev on 19. 7. 8..
 */
public interface ViewConstants {

	public static final String DP_TYPE_TOP = "TOP";
	public static final String DP_TYPE_RANK = "RANK";
	public static final String DP_TYPE_PERCENT = "PERCENT";

	// dp field
	public enum DP_FILED {

		APIKEY("APIKEY"),
		SERVICEID("Service ID"),
		BROWSER("웹브라우저");


		private String fieldName;

		DP_FILED(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getFieldName() { return fieldName; }
	}
}
