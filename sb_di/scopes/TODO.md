
* overwrite in tests*
  spring junit No qualifying bean of type available: expected single matching bean but found 2
 
  @Bean
  @Primary
  public RestClientConfiguration restClientConfiguration()
  https://stackoverflow.com/questions/35742920/overriding-beans-in-integration-tests

* [The problem of injecting a shorter-lived scoped bean into a longer-lived scoped bean](https://www.logicbig.com/tutorials/spring-framework/spring-core/injecting-singleton-with-prototype-bean.html)
and possible solutions.
* [Spring Bean Scopes from `baeldung.com`](https://www.baeldung.com/spring-bean-scopes)
* [Spring Accessing Request Scope Beans outside of web request](https://medium.com/@pranav_maniar/spring-accessing-request-scope-beans-outside-of-web-request-faad27b5ed57)