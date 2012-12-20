package hdm.stuttgart.esell.errors;


import com.google.gson.Gson;

/*
 * ESellException
 * Führt einen Katalog mit Fehler- und Erfolgsmeldungen und stellt ein Objekt mit den Feldern flag und message.
 * Je nach errorCode, mit dem instanziiert wird, wird die message mit 
 * der entsprechenden Fehlermeldung belegt. 
 * 
 */

public class ESellException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}



	/*
	 * Konstruktor
	 * Params: int flag, String errorCode
	 * 
	 */
	public ESellException(ErrorCode errorCode) {
		super(errorCode.message);
		
		this.errorCode = errorCode;
//		this.flag = flag;
//		
//		//HashMap für Meldungen erzeugen
//		HashMap<String, String> errorMap = new HashMap<String, String>();
//		//HashMap mit Einträgen füllen
//		errorMap = fillErrorMap(errorMap);
//		
//		//überprüfen, ob der übergebene Fehlercode in der HashMap liegt.
//		//Wenn ja, entsprechende Meldung ausgeben
//		if (errorMap.containsKey(errorCode)) {
//			this.message = errorMap.get(errorCode).toString();
//		} 
//		//Wenn nein, Standardfehlermeldung
//		else {
//			this.message = "Fehler";
//		}
	}
	

	
	public static enum ErrorCode {
		DB_ERR("Fehler beim Verbinden zur Datenbank"),
		DELETE_ERR("Fehler beim Löschen"),
		INSERT_ERR("Fehler beim Speichern in der Datenbank"),
		NO_ENTRY_ERR("Kein DB-Eintrag vorhanden"),
		UPDATE_ERR("Fehler beim Aktualisieren"),
		USR_HAS_PET_ERR("Der Benutzer kann nicht gelöscht werden, weil ihm noch Kaufgesuche zugewiesen sind"),
		USR_EXISTS("Username oder Email schon vorhanden"),
		ERR("Ein unbekannter Fehler ist aufgetreten")
        ;
        public String message;
        ErrorCode(String message) {
            this.message = message;
        }
}
	
	
	//Json ausgeben
	public String getJson(){
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json; 
	}
	
//	//KAtalog für Fehlermeldungen
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private HashMap<String, String> fillErrorMap(HashMap errorMap) {
//		errorMap.put("DB_ERR", "Fehler beim Verbinden zur Datenbank");
//		errorMap.put("DELETE_ERR", "Fehler beim Löschen");
//		errorMap.put("INSERT_ERR", "Fehler beim Speichern in der Datenbank");
//		errorMap.put("NO_ENTRY_ERR", "Kein DB-Eintrag vorhanden");
//		errorMap.put("PET_SAVED", "Dein Kaufgesuch wurde gespeichert");
//		errorMap.put("PET_UPDATED", "Dein Kaufgesuch wurde aktualisiert");
//		errorMap.put("PET_DELETED", "Das Kaufgesuch wurde gelöscht");
//		errorMap.put("UPDATE_ERR", "Fehler beim Aktualisieren");
//		errorMap.put("USR_EXISTS", "Username oder Email schon vorhanden");
//		errorMap.put("USR_SAVED", "Dein Account wurde erfolgreich angelegt");
//		errorMap.put("USR_UPDATED", "Dein Profil wurde aktualisiert");
//		errorMap.put("USR_DELETED", "Der Benutzer wurde erfolgreich gelöscht");
//		errorMap.put("USR_HAS_PET_ERR", "Der Benutzer kann nicht gelöscht werden, weil ihm noch Kaufgesuche zugewiesen sind");
//
//		return errorMap;
//	}
	
//	//Getter und Setter
//	public boolean getFlag() {
//		return this.flag;
//	}
//
//	public void setFlag(boolean flag) {
//		this.flag = flag;
//	}
//
//	public String getMessage() {
//		return this.message;
//	}
//
//	public void setMessage(String message) {
//		this.message = message;
//	}
}
