# DCSI - Internship Java for Enterprise Security

We document the project in [this wiki](https://github.com/raisercostin/dcsi/wiki).
Ask to be a collaborator if you want to contribute or make a pull request.

# Backlog

- [x] Import 100 users from a csv file.

    - [x] Project built with maven.
    - [x] Make a test with junit that checks user creation.
    - [x] Make test pass.
    - [x] csv library  
        ~ stackoverflow.com > Questions > 10462507 > Any-good-library-to-read-and-write-csv-files

- [x] Export 100 users to a csv file.
## First time running
1. Go to the project path using:  
`cd %PATH%`
2. Be aware that the project was cleaned so you should compile it first.  
`mvn compile exec:java -Dexec.mainClass=ro.dcsi.internship.App`
3. Setup the project to work with your IDE. For eclipse, use:  
`mvn eclipse:eclipse`


