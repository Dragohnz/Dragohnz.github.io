package ClassFiles;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class FindNum {

	public static Object FindNumber(double fl1, double fl2) {
		
		//Get mongo set up
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("market");
		MongoCollection<Document> collection = database.getCollection("stocks");
		
		//Set up the documents
		Document query = new Document("$gte", fl2).append("$lte", fl1);
		Document query2 = new Document("50-Day Simple Moving Average", query);
		
		//Call the count command
		long count = collection.count(query2);

		//Print out the result
	    System.out.println(count);
	    
	    return count;
	}
}
