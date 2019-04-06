package ClassFiles;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Insert {
	
	public static Object InsertDocument(String input, String input2, String input3, String input4) {
		
		//Get mongo set up
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("market");
		MongoCollection<Document> collection = database.getCollection("stocks");
		
		 //Put the strings into document format
	    Document docI = new Document(input, input2).append(input3, input4);
	    
		//Call the insert command
		collection.insertOne(docI);
		
		//Call a find command to make sure the inserted document can be found
		var myDocument = collection.find(docI).first();

		//Print out the result
	    System.out.println(myDocument);
	    
	    return myDocument;
	}

}
