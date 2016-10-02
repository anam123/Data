# Data App
An app to store data through shared preferences, sqlite DB and internal and external storage

# Running The App
Download and run the apk provided on your android device.

# Shared Preferences
The total number of reviews on the home page is stored using shared preferences. It changes when a review is  added or deleted and 
remains the same even after stopping and restarting the app.

# Storage through internal/external storage
Filling the form and pressing ADD button would add the review to both the external storage (both private and public external)
and internal storage. When the app is deleted the files stored in internal storage and private external storage are deleted whereas
the files in public external storage remain.

# Storing through SQLite DataBase
Pressing ADD after filling the review form adds a row of data to the review table. 

The table can be viewed using VIEW Button.

Pressing DELETE would delete the row which contains the mentioned movie title.

Pressing UPDATE would update the row which contains the mentioned movie title.


