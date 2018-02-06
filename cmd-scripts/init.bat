@REM FOR /F "tokens=* USEBACKQ" %F IN (`where /R "C:\Program Files" neo4j-ce.exe`) DO (SET var=%F)
@REM ----------------------------------------------------------------------------
@REM Init Batch script

set MONGO_HOME="C:\Program Files\MongoDB\Server\3.4\bin"
set MONGO_STORAGE="C:\data\db"
set MONGO_CLIENT="C:\Program Files\MongoDB\Server\3.4\bin\mongo.exe"
@REM set NEO4J_HOME="C:\Program Files\Neo4j CE 3.2.6\bin"

@REM start /D %NEO4J_HOME% neo4j-ce start
start /D %MONGO_HOME% mongod.exe --dbpath %MONGO_STORAGE%
timeout 5
%MONGO_CLIENT% < init-mongo-script/init.js

echo 'Init script executed.'