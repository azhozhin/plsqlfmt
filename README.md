# plsqlfmt

PL/SQL formatter that uses Oracle SQL Developer libraries to format your code.

## Compilation

You need two libraries from sqldeveloper installation:
* SQLinForm.jar
* oracle.dbtools-common.jar

put these jars into libs folder

assemle with gradle
```
./gradlew fatJar
```

get fatjar in build/libs/plsqlfmt-all-1.0-SNAPSHOT.jar

## Usage

```
java -jar plsqlfmt-all.jar -style codestyle.xml -input query_factoring07.sql -output query_factoring07_f.sql
```

