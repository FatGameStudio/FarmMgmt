gradle clean build
ssh sotof@192.168.1.2 rm -r /volume1/app/tomcat/apache-tomcat-8.5.27/webapps/FarmMgmt*
scp build/libs/FarmMgmt-0.0.1.war sotof@192.168.1.2:/volume1/app/tomcat/apache-tomcat-8.5.27/webapps/FarmMgmt.war
