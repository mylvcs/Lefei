//package wangmengyun.MongoDB;
//
//import android.os.AsyncTask;
//
//import com.mongodb.BasicDBList;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
///**
// * Async Task to retrieve your stored contacts from mongolab
// *
// */
//public class GetContactsAsyncTask extends AsyncTask<MyContact, Void, ArrayList<MyContact>> {
//	static BasicDBObject user = null;
//	static String OriginalObject = "";
//	static String server_output = null;
//	static String temp_output = null;
//
//	@Override
//	protected ArrayList<MyContact> doInBackground(MyContact... arg0) {
//
//		ArrayList<MyContact> mycontacts = new ArrayList<MyContact>();
//		try
//		{
//
//			QueryBuilder qb = new QueryBuilder();
//	        URL url = new URL(qb.buildContactsGetURL());
//	        HttpURLConnection conn = (HttpURLConnection) url
//					.openConnection();
//	        conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					(conn.getInputStream())));
//
//			while ((temp_output = br.readLine()) != null) {
//				server_output = temp_output;
//			}
//
//            // create a basic db list
//			String mongoarray = "{ artificial_basicdb_list: "+server_output+"}";
//			Object o = com.mongodb.util.JSON.parse(mongoarray);
//
//
//			DBObject dbObj = (DBObject) o;
//			BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");
//
//		  for (Object obj : contacts) {
//			DBObject userObj = (DBObject) obj;
//
//			MyContact temp = new MyContact();
//			temp.setDoc_id(userObj.get("_id").toString());
//			temp.setDeparture_city(userObj.get("departure_city").toString());
//			temp.setArrive_city(userObj.get("arrive_city").toString());
//			temp.setDepart_date(userObj.get("depart_date").toString());
//			temp.setArrive_date(userObj.get("arrive_date").toString());
//			mycontacts.add(temp);
//
//			}
//
//		}catch (Exception e) {
//			e.getMessage();
//		}
//
//		return mycontacts;
//	}
//}
