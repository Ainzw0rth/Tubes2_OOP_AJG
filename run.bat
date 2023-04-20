@echo off
call mvn clean package
java -jar target/BNMOStore_AJG.jar