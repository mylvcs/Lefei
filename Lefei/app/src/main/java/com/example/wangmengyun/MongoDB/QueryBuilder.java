package wangmengyun.MongoDB;

public class QueryBuilder {
	
	/**
	 * Specify your database name here
	 * @return
	 */
	public String getDatabaseName() {
		return "flights";
	}

	/**
	 * Specify your MongoLab API here
	 * @return
	 */
	public String getApiKey() {

		return "A9QBlsdZ9-6q5sxDUWBfRZ-w9ZRBdW7L";
	}
	
	/**
	 * This constructs the URL that allows you to manage your database, 
	 * collections and documents
	 * @return
	 */
	public String getBaseUrl()
	{
		return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
	}
	
	/**
	 * Completes the formating of your URL and adds your API key at the end
	 * @return
	 */
	public String docApiKeyUrl()
	{
		return "?apiKey="+getApiKey();
	}
	
	/**
	 * Get a specified document
	 * @param
	 * @return
	 */
	public String docApiKeyUrl(String docid)
	{
		return "/"+docid+"?apiKey="+getApiKey();
	}
	
	/**
	 * Returns the flight collection
	 * @return
	 */
	public String documentRequest()
	{
		return "flight";
	}
	
	/**
	 * Builds a complete URL using the methods specified above
	 * @return
	 */
	public String buildContactsSaveURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * This method is identical to the one above. 
	 * @return
	 */
	public String buildContactsGetURL()
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl();
	}
	
	/**
	 * Get a Mongodb document that corresponds to the given object id
	 * @param doc_id
	 * @return
	 */
	public String buildContactsUpdateURL(String doc_id)
	{
		return getBaseUrl()+documentRequest()+docApiKeyUrl(doc_id);
	}
	
	
	/**
	 * Formats the contact details for MongoHLab Posting
	 * @param contact: Details of the person 
	 * @return
	 */
	public String createContact(MyContact contact)
	{
		return String
		.format("{\"departure_city\": \"%s\", "
				+ "\"arrive_city\": \"%s\", \"depart_date\": \"%s\", "
				+ "\"arrive_date\": \"%s\"}",
				contact.departure_city, contact.arrive_city, contact.depart_date, contact.arrive_date);
	}
	
	/**
	 * Update a given contact record
	 * @param contact
	 * @return
	 */
	public String setContactData(MyContact contact) {
		return String.format("{ \"$set\" : " 
				+ "{\"departure_city\" : \"%s\", "
				+ "\"arrive_city\" : \"%s\", "
				+ "\"depart_date\" : \"%s\", "
				+ "\"arrive_date\" : \"%s\" }" + "}",
				contact.getDeparture_city(),
				contact.getArrive_city(), contact.getDepart_date(),
				contact.getArrive_date());
	}
	
}
