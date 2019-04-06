package ClassFiles;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class FindStr {
	
	public static Object FindString(String input) {
		
		//Get mongo set up
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("market");
		MongoCollection<Document> collection = database.getCollection("stocks");
		
		//Get the documents set up 
		Document doc1 = new Document("Industry", input);
		Document doc2 = new Document("Ticker", "1");
		
		//Set up the find() command
		MongoCursor<Document> cursor = collection.find(doc1).projection(doc2).noCursorTimeout(true).iterator();
		
		//Print out the results
		while (cursor.hasNext()) {
	        System.out.println(cursor.next());
		}
		
	    
	    return cursor;
	}

}
