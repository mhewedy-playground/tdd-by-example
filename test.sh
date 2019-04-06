rm -rf out/*.class
javac -d out/ -cp out src/*.java \
    && java -cp out MoneyTest $*
