import json
from bson import json_util
from pymongo import MongoClient
import pprint

connection = MongoClient('localhost', 27017)
db = connection['market']
collection = db['stocks']

def insert_document(document):
  try:
   result = collection.insert_one(document)
  except ValidationError as ve:
   abort(400, str(ve))
  
  return result
  
def find_document(search):
  try:
   result = collection.find_one(search)
  
  except ValidationError as ve:
   abort(400, str(ve))
  
  return result
  
def update_document(uquery, update):
  
  result = collection.update(uquery, {"$set" : update}, upsert=False,multi=False)
  
  if not result:
    abort(404, 'No document with %s' % uquery)
      
  return json.loads(json.dumps(result, indent=4, default=json_util.default))

def delete_document(deleted):
  #try:
  result = collection.delete_many(deleted)
  
  #except ValidationError as ve:
   #abort(400, str(ve))
  
  return result
  
def main():
  myDocument = { "keyName" : "milestone 1", "user" : "Alex"}
  search = {u'user' : "Alex"}
  uquery = {"keyName" : "milestone 1"}
  update = {"class" : "CS-340"}
  
  insert_document(myDocument)
  if collection.find(search).count() > 0:
    pprint.pprint("True")
  else:
    pprint.pprint("False")
  pprint.pprint("")
  
  pprint.pprint(find_document(search))
  pprint.pprint("")
  
  update_document(uquery, update)
  pprint.pprint(find_document(search))
  pprint.pprint("")
  
  delete_document(search)
  pprint.pprint(find_document(search))
  
#main()