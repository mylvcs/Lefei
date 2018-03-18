package wangmengyun.MongoDB;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class SaveAsyncTask extends AsyncTask<MyContact, Void, Boolean> {

	@Override
	protected Boolean doInBackground(MyContact... arg0) {
		try 
		{			
			MyContact contact = arg0[0];
			
			QueryBuilder qb = new QueryBuilder();						
			
			HttpClient httpClient = new DefaultHttpClient();
	        HttpPost request = new HttpPost(qb.buildContactsSaveURL());

	        StringEntity params =new StringEntity(qb.createContact(contact));
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        
	        if(response.getStatusLine().getStatusCode()<205)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
		} catch (Exception e) {
			//e.getCause();
			String val = e.getMessage();
			String val2 = val;
			return false;
		}		
	}

}
