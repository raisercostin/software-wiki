# DCSI - Internship Java for Enterprise Security

We document the project in [this wiki](https://github.com/raisercostin/dcsi/wiki).
Ask to be a collaborator if you want to contribute or make a pull request.

#  usersync
Add instructions for project developers here.

# Design
- design changes - https://github.com/raisercostin/dcsi/commit/de94b0a04589716424836661bd8b259635c6410a?diff=unified
- current design
  ![current](https://github.com/raisercostin/dcsi/blob/user/costin/all.png)
- design2
  ![design2](https://github.com/raisercostin/dcsi/raw/de94b0a04589716424836661bd8b259635c6410a/all.png)
- design1
  ![design1](https://github.com/raisercostin/dcsi/raw/22c2be6f032539e29764df3c6ca945048a613af8/all.png)

# Backlog 
See [wiki](https://github.com/raisercostin/dcsi/wiki/UserSync-App-Backlog)

# Backlog

- prereq 
    - java installed 
        - to start idm server 

    - maven installed 
        - to have a prj with maven 

    - cmder installed for windows 
        - to be able to use curl like in bash 

    - git installed 
        - to have curl 

- check server 
    - install idm 
        - [~ https://www.forgerock.com/downloads/download-openidm-trial-downloading/ ](https://www.forgerock.com/downloads/download-openidm-trial-downloading/)
        - [~ https://backstage.forgerock.com/docs/idm/5/install-guide ](https://backstage.forgerock.com/docs/idm/5/install-guide)

    - check connection 
        - server 
            - [~ http://localhost:8080 ](http://localhost:8080/)
            - [~ https://localhost:8443 ](https://localhost:8443/)

        - user 
            - openidm-admin 

        - pass 
            - openidm-admin 

- What can be fixed from a security point of view? 
    - change the admin password 
    - change the admin username 
    - remove access via http 
    - use a secure connection 
        - add access via https 

- start a maven project 
    - [~ https://start.spring.io/ ](https://start.spring.io/)
    - maven-archetype-quickstart 
    - mvn archetype:generate 
        - options 
            - java8 
                - java8 
                - 7: remote -&gt; org.spilth:java8-junit4-quickstart 
                - 2 

            - quickstart 
                - maven-archetype-quickstart 

        - config 
            - groupId: ro.roweb.dcsi 
            - artifactId: intern 
            - version: 1.0-SNAPSHOT 
            - package: ro.roweb.dcsi 

    - choose the best generated one 
        - compare via totalcmd 
            - folders 
            - files 
            - change some 

    - generate eclipse project for maven 
    - import project in eclipse 
    - stake on ide wars 
        - eclipse 
        - IntelliJ IDEA 
        - netbeans 

- start adding git versioning 
- Import 100 users from a csv file. 
    - Project built with maven. 
    - Make a test with junit that checks user creation. 
    - Make test pass. 
    - csv library 
        - [~ stackoverflow.com &gt; Questions &gt; 10462507 &gt; Any-good-library-to-read-and-write-csv-files ](http://stackoverflow.com/questions/10462507/any-good-library-to-read-and-write-csv-files)

- Export 100 users to a csv file. 
- What if we have 1 000 000 users? 
- Add cli client. A console with some parameters. 
- steps 
    - GET http 
        - +HTTP 
            - app security 
                - user 
                    - identity 
                    - authentication 
                    - authorization 

            - infrastructure security 
                - PKI arhitecture 
                - SSL 
                - TLS 

    - GET REST json 
        - +JSON 
        - +REST 

    - PUT/CREATE 
        - +refactor 

    - CRUD SWAGGER 
        - YML 
        - as document 
        - as client 
        - as server 

    - HATEOAS 
        - [~ https://spring.io/understanding/HATEOAS ](https://spring.io/understanding/HATEOAS)
        - [~ https://martinfowler.com/articles/richardsonMaturityModel.html ](https://martinfowler.com/articles/richardsonMaturityModel.html)
        
        
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
    - [ ] read users from a file
	- [ ] write users to a file

- [x] Export 100 users to a csv file.
## First time running
1. Go to the project path using:  
`cd %PATH%`
2. Be aware that the project was cleaned so you should compile it first.  
`mvn compile exec:java -Dexec.mainClass=ro.dcsi.internship.App`
3. Setup the project to work with your IDE. For eclipse, use:  
`mvn eclipse:eclipse`
