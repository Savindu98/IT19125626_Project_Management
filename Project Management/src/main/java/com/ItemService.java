//IT Number - IT19125626
//Name - Dulanjaya L.A.P.S
//Function - Project Management
package com;

import model.Item;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Items") 
public class ItemService {
	
	Item itemObj = new Item(); 
	
	//Read Method
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readItems() 
	{  
		return itemObj.readItems(); 
		
	}
	//Insert method
	
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertItem(@FormParam("itemcode") String itemcode, 
			@FormParam("itemname") String itemname,
			@FormParam("quantity") String quantity, 
			@FormParam("description") String description, 
			@FormParam("supemail") String supemail) 
	{
		String output = itemObj.insertItem(itemcode, itemname, quantity, description,supemail);
		return output; 
	}
	
	//Update method
	
	@PUT 
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateItem(String itemData) 
	{
		
		//Convert the input string to a JSON object 
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		
		//Read the values from the JSON object 
		String itemid = itemObject.get("itemid").getAsString(); 
		String itemcode = itemObject.get("itemcode").getAsString();  
		String itemname = itemObject.get("itemname").getAsString(); 
		String quantity = itemObject.get("quantity").getAsString(); 
		String description = itemObject.get("description").getAsString(); 
		String supemail = itemObject.get("supemail").getAsString(); 
		
		 String output = itemObj.updateItem(itemid, itemcode, itemname, quantity, description , supemail); 
		 return output; 
	}
	
	//Delete Method
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String deleteItem(String itemData) 
	{
		//Convert the input string to an XML document 
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		//Read the value from the element <itemID> 
		String itemid = doc.select("itemid").text(); 
		
		String output = itemObj.deleteItem(itemid); 
		return output; 
		
		
	}

}
