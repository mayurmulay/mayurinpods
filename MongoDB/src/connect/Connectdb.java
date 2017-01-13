package connect;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import java.util.Arrays;

public class Connectdb {
	 static MongoClient mongoClient;
	 static DB db;
	 
	public static void main(String[] args) {
		 try{
				
	         // To connect to mongodb server
	          mongoClient = new MongoClient( "localhost" , 27017 );
				
	         // Now connect to your databases
	          db = mongoClient.getDB( "test" );
	         System.out.println("Connect to database successfully");
	        // boolean auth = db.authenticate(myUserName, myPassword);
	        // System.out.println("Authentication: ");
	         createCollection("personal_details");
	         InsertDocument("personal_details");
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	

	}
    public static void createCollection(String name)
    {
    	try
    	{
    		 DBCollection coll = db.createCollection(name, null);
             System.out.println("Collection created successfully");
    	}
        catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }

    }
    
    public static void InsertDocument(String Collectionname)
    {
    	try
    	{
    		 DBCollection coll = db.getCollection(Collectionname);
    		 BasicDBObject doc = new BasicDBObject("title", "MongoDB").
    		            append("description", "database").
    		            append("likes", 100).
    		            append("url", "http://www.tutorialspoint.com/mongodb/").
    		            append("by", "tutorials point");
    						
    		         coll.insert(doc);  //db.Collectionname.insert({description:"database,likes: 100,....})
             System.out.println("Docement inserted created successfully");
    	}
        catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }

    }
    	
    
}

