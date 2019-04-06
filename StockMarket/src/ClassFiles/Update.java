package ClassFiles;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Update {
	
	public static Object UpdateDocument(String input, String input2, String input3, String input4) {
		
		//Get mongo set up
		@SuppressWarnings("resource")
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("market");
		MongoCollection<Document> collection = database.getCollection("stocks");
		
		//Put the inputs into document format
	    Document docU = new Document("$set", new Document(input3, input4));
	    Document docU1 = new Document().append(input, input2);
		
		//Call the update command
		collection.updateOne(docU1, docU);
		
		//Check to make sure that the document was updated
		var myDocument = collection.find(docU1).first();

		//Print out the result
	    System.out.println(myDocument);
	    
	    return myDocument;
	}
}
