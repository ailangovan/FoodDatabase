Overview:

I generated a webapp that was deployed as a WAR file to AWS for interacting with a MySQL based Database to help track food and diets. 

From my Databases course, I learned the tools used to implement the database functionality. The database was also hosted on AWS. The design of the data tables can be found in the Wiki.

For the frontend, it was left to user discretion what tools to use. I do not have much experience with front end so I found a resource online for a front end, and modified it to suit the purpose of demonstrating functionality provided by the database. Also, authentication was not strictly necessary so it is implemented very weakly here. It is the first area I would seek to improve when revisiting this project.

Problem

In our fast-paced lifestyle, it is easy to forget to maintain a healthy diet. There are many times where it is difficult to find nutritional information about the food we eat. Additionally, there is not a stress to make this information readily available to the consumer. 

An important step to improving this aspect is tracking the food consumed. 
-	A food list could be generated that would contain all the foods that are available for an individual to choose from on a day to day basis. 
-	In addition, a food consumed list could be maintained for every week to track the overall nutritional health of a user.
-	Food nutritional information would also have to be tracked to gather information on the calories, sugar, fat and other information.

Domain Objects:
- Food, Diets, Weekly Food Eaten


Potential Users:
-	 User: A user that logs in and has their information saved.
-	Nutritionist: Can assist users in identifying the gaps in their food lists and improving the balance in their diet.
-	Admins: Assist in maintaining the service.

 User Goals:
-	Login and maintain their current food list
-	Track their food consumption
-	Connect with a nutritionist

Nutritionist Goals:
-	Connect with client users
-	Identify weaknesses in the consumed food
-	Implement changes in food list

Admin:
-	Maintain the userbase, ie.
-	Remove items and lists that are no longer relevant
-	Remove or update users

User Relations:
-	Users: 
o	Follow other user, enlist a nutritionist
o	Create a food list, track food consumed with weekly consumed list
-	Nutritionist: 
o	Message user, recruit user for consult
o	Modify user food lists, summarize consumed foods
-	Admin: 
o	remove user, add nutritionist status
o	add food, remove Diets 
 
Domain Objects:
-	Diets: Contains food, 
-	Food: In Diets, counted by WeeklyFoodEaten
-	Weekly Food Lists: Track food

The API I chose to use was the NDB API: 

https://ndb.nal.usda.gov/ndb/doc/index

It allows me to get searches for food to add to my database. Also, For each food item, I can perform a search to get all associated nutrioninal information that can be useful to track in this use case.

Resources:

Majority of Front End of this project was modified and learned from:
https://github.com/spring-petclinic/spring-petclinic-angular
