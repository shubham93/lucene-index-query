# lucene-index-query

## Command to build project

mvn package

## Command to run project

java -jar target/luceneIndex-0.0.1.jar /cran/cran.all.1400.txt /cran/cran.qry


## Command to test trec eval
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-no-similarity.txt
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-vsm-similarity.txt
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-bm25-similarity.txt
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-multi-similarity.txt

./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-no-similarity.txt
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-vsm-similarity.txt  
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-bm25-similarity.txt 
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-multi-similarity.txt 

## Command to execute process end to end
sh ./index-query.sh