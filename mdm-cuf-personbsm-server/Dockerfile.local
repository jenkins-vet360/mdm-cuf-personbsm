FROM vaausappv36901.aac.va.gov:7003/vet360/tomcat:latest

# Environment Variables used to configure that application
ENV SPRING_PROFILES_ACTIVE=ENV-LOCAL-DEV

ADD target/mdm-cuf-personbsm-server.war $TOMCAT_HOME/webapps/ROOT.war
