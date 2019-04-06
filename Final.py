#Set up imports
import json
from bson import json_util
import bottle
from bottle import route, run, request, abort
from pymongo import MongoClient
import pprint
import Milestone1

#Set up the mongo connection
connection = MongoClient('localhost', 27017)
db = connection['market']
collection = db['stocks']

def insert(document):
  #Call Milestone1.py for insert code
  Milestone1.insert_document(document)
  #{"Ticker":"ZYX","Volume":"1"}
  
  #Check to see if the insert was successful
  if collection.find(document).count() > 0:
    result = "Success"
  else:
    result = "Fail"
  
  return result

def update(uquery, updated):
  #Call Milestone1.py for update code
  Milestone1.update_document(uquery, updated)
  #{"Ticker":"ZYX"}
  #{"Volume":"12"}
  
  #Check to see if the update was successfull
  result = Milestone1.find_document(uquery)
  
  return result

def delete(deleted):
  #Call Milestone1.py for delete code
  Milestone1.delete_document(deleted)
  #{"Ticker":"ZYX"}
  
  #Check to see if the delete was successfull
  if collection.find(deleted).count() > 0:
    result = "Fail"
  else:
    result = "Success"
  
  return result

def find_Number(low, high):
  #Find command to look for how many documents between the high and low
  result = collection.find({"50-Day Simple Moving Average":{"$gte":low, "$lte":high}}).count()
  return result

def find_String(findStr):
  #Find Command to look for the Tickers for the industry of the input string
  result = list(collection.find({"Industry":findStr},{"Ticker":1}))
  #"Medical Laboratories & Research"
  
  return result

def aggregate(agg):
  #Aggregate the given input for Sector and return total outstanding shares grouped by Industry
  result = list(collection.aggregate([{"$match":{"Sector":agg}},{"$project":{"_id":1,"Shares Outstanding":1,"Industry":1,"Sector":1}},{"$group":{"_id":"$Industry","Total Outstanding Shares":{"$sum":"$Shares Outstanding"}}}]))
  #"Healthcare"
  
  return result

#Main code
def main():
  #Just set up the variable
  selection = ""
  
  #Loop for the Menu (Everything will be in here)
  while selection != "e":
    #Main Menu print out
    print("""
      Main Menu
      
      i. Insert
      u. Update
      d. Delete
      n. Find Number
      s. Find String
      a. Aggregate
      e. Exit
    """)
    
    #User input option
    selection = raw_input("Type in the letter of the desired action: ")
    
    #Insert
    if selection == "i":
      print("\nYou chose Input\n")
      
      #Enter the keys and values to insert, or go back
      inserts = raw_input("Enter in a key-value pairs to insert, enter it in json format, or b to go back: ")
      
      if inserts == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        document = json.loads(inserts)
        print(insert(document))
        
    #Update
    elif selection == "u":
      print("\nYou chose Update\n")
      
      #Enter in the key, or go back
      insert1 = raw_input("Enter in the key to update, enter it in json format, or b to go back: ")
      
      if insert1 == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        #Now enter the update
        insert2 = raw_input("Enter in the update, enter it in json format: ")
        uquery = json.loads(insert1)
        updated = json.loads(insert2)
        
        #Print out the update
        pprint.pprint(update(uquery, updated))
    
    #Delete
    elif selection == "d":
      print("\nYou chose Delete\n")
      
      #What key-value pair to delete or go back
      inserts = raw_input("Enter in a key-value pair to delete, enter it in json format, or b to go back: ")
      
      if inserts == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        #delete the document with the input
        deleted = json.loads(inserts)
        print delete(deleted)
    
    #Find a Number
    elif selection == "n":
      print("\nYou chose Find Number\n")
      
      #start with high number or go back
      inputs = raw_input("Enter in a high numerical value, or b to go back: ")
      
      if inputs == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        #Make the high number an int, then ask for the low
        high = int(inputs)
        low = int(raw_input("Enter in a low numerical value: "))
        
        #Print out the number of results
        print("\nThe total number of documents found is: ")
        print(find_Number(low, high))
    
    #Fina a String
    elif selection == "s":
      print("\nYou chose Find String\n")
      
      #Enter the string to search for or go back
      inserts = raw_input("Enter in a string, or b to go back: ")
      
      if inserts == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        #Print out the results
        findStr = json.loads(inserts)
        pprint.pprint(find_String(findStr))
    
    #Aggregate
    elif selection == "a":
      print("\nYou chose Aggregate\n")
      
      #What value to aggregate or go back
      ag = raw_input("Enter in a value for key Sector to aggregate, enter it in quotes, or b to go back: ")
      
      if ag == "b":
        #Go back
        print("Back to the Main Menu")
      
      else:
        #Print out the aggregated results
        agg = json.loads(ag)
        pprint.pprint(aggregate(agg))
    
    #Exit
    elif selection == "e":
      print("Good Bye")
    
    #If user input something that is not a selection
    else:
      print("\nPlease make a selection above.\n")
 
main()