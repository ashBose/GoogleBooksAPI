#To execute

mvn exec:java -Dexec.mainClass="com.books.AppEntryMain" -Dexec.args="-q java -b
\"Head First Java, Java Web Development Illuminated\" -s published_date"

#Persisted File

The file will be present at /tmp. The name will <query-parameter_todays-date>.json
It will be vi /tmp/java_2018_05_12.json