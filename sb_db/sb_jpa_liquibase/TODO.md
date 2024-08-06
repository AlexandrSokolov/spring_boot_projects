### integration tests with mysql container and initial dataset

### liquibase actuator

### jpa logging

https://vladmihalcea.com/log-sql-spring-boot/

### move all properties from persistence.xml into application.yaml with the description

https://vladmihalcea.com/spring-boot-application-properties/
https://medium.com/@adrikaroy/caching-in-hibernate-54840c25cafd

```xml
<persistence xmlns="https://jakarta.ee/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="3.0" xsi:schemalocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd">
  <persistence-unit name="items-persistence-unit">
    <!-- Activate the 2nd level cache:
    ALL – To cache all entities
    NONE – To cache none of your entities (default)
    ENABLE_SELECTIVE – To cache only the entities annotated with @Cacheable or @Cacheable(true)
    DISABLE_SELECTIVE – To cache all entities not annotated with @Cacheable(false)-->
    <shared-cache-mode>ENABLE_SELECTIVE</shared-cache-mode>
    <!-- Automatic validation of your entities
    AUTO – Perform the validation if a bean validation implementation is available (default)
    CALLBACK– Activate the validation and throw an exception if no bean validation implementation is available
    NONE – Do not perform any validation
    -->
    <validation-mode>AUTO</validation-mode>
    <properties>
      <!-- Configure timeouts -->
      <property name="jakarta.persistence.lock.timeout" value="100"/>
      <property name="jakarta.persistence.query.timeout" value="100"/>
      <!--The `retrieveMode` hint supports the values `USE` and `BYPASS` and tells Hibernate
      if it shall USE the second-level cache to retrieve an entity
      or if it shall BYPASS it and get it directly from the database.-->
      <property name="jakarta.persistence.cache.retrieveMode" value="USE"/>
      <!-- How Hibernate shall write changed entities to the second-level cache
      USE - add entities to the cache and update existing ones
      BYPASS- bypass it for entities that are not already stored in the cache and only update the existing ones
      REFRESH - refresh the entities located in the cache before they get retrieved from it
      -->
      <property name="jakarta.persistence.cache.storeMode" value="USE"/>
<!--      <property name="jakarta.persistence.loadgraph" value="TODO"/>-->
<!--      <property name="jakarta.persistence.fetchgraph" value="TODO"/>-->

      <!--  Specify Validation Groups -->
<!--      <property name="jakarta.persistence.validation.group.pre-persist" value="jakarta.validation.groups.MyPersistValidation"/>-->
<!--      <property name="jakarta.persistence.validation.group.pre-update" value="jakarta.validation.groups.MyUpdateValidation"/>-->
<!--      <property name="jakarta.persistence.validation.group.pre-remove" value="jakarta.validation.groups.MyRemovetValidation"/>-->

      <!-- #####  Hibernate Hints ##### -->

      <!-- If you modify an entity, Hibernate keeps these changes in the first-level cache until it gets flushed.
      When and how Hibernate performs a flush operation depends on your configured FlushMode.
      AUTO = Hibernate decides if the changes have to be written to the database,
      ALWAYS = The Session gets flushed before every query,
      COMMIT = Hibernate will not write any changes to the database until the transaction gets committed,
      MANUAL = You have to flush the Session yourself. -->
      <property name="org.hibernate.flushMode" value="AUTO"/>

      <!--
      If you do not apply any changes to the selected entities, you can set the `org.hibernate.readOnly` hint to true.
      This allows Hibernate to deactivate dirty checking for these entities, which can provide a performance benefit.
      -->
      <property name="org.hibernate.readOnly" value="false"/>

     <!-- hint to the JDBC driver to define the number of rows the driver shall receive in one batch.-->
      <property name="org.hibernate.fetchSize" value="100"/>

      <!-- if set to true, Hibernate generates a comment for each query and writes it to the log file.
      This can be useful if you have to analyze huge or complex SQL logs.
      You can use the org.hibernate.comment hint to provide your own comment for a query.-->
      <property name="org.hibernate.comment" value="false"/>

      <!--If you want to use Hibernate’s query cache, you have to activate it in the persistence.xml file
      and enable it for a specific query by setting the org.hibernate.cacheable hint to true.-->
      <property name="org.hibernate.cacheable" value="true"/>

    </properties>
  </persistence-unit>
</persistence>
```


### consider:

<property name="hibernate.show_sql" value="true" />
        <property name="hibernate.format_sql" value="true" />

    <property name="initialPoolSize" value="5"/>
    <property name="minPoolSize" value="5"/>
<property name="hibernate.c3p0.min_size" value="5"/>


### todo how to check that persistence.xml is applied
### liquibase integration
### add db connection (probably via docker image, with test mysql container)
### jpa integration
### integration tests for db layer

