mvn package
echo "Starting the Indexing Process for Standard And English Analyser"
java -jar luceneIndex-0.0.1.jar /cran/cran.all.1400.txt /cran/cran.qry
