mvn package
echo "Starting the Indexing Process for Standard And English Analyser"
java -jar target/luceneIndex-0.0.1.jar /cran/cran.all.1400.txt /cran/cran.qry

echo "Trec eval process For Standard Analyser"
cd trec_eval-9.0.7

echo "Trec eval for no similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-no-similarity.txt
echo "Trec eval for vsm similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-vsm-similarity.txt
echo "Trec eval for BM25 Similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-bm25-similarity.txt
echo "Trec eval for Multi Similarity using VSM and BM25"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-standard-multi-similarity.txt

echo "Trec eval process For English Analyser"

echo "Trec eval for no similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-no-similarity.txt
echo "Trec eval for vsm similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-vsm-similarity.txt  
echo "Trec eval for BM25 Similarity"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-bm25-similarity.txt 
echo "Trec eval for Multi Similarity using VSM and BM25"
./trec_eval ../src/main/resources/cran/QRelsCorrectedforTRECeval ../../result-english-multi-similarity.txt    
