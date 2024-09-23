-Dspring.profiles.active=staging -Dspring.config.location=C:\Config
vs
--spring.profiles.active=staging --spring.config.location=C:\Config

both are correct, but the location is different:
java -Dspring.profiles.active=staging -Dspring.config.location=C:\Config your-spring-boot-app.jar 

vs

java your-spring-boot.jar --spring.profiles.active=staging --spring.config.location=C:\Config

