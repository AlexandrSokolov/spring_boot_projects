### Rest request:

```bash
$ curl -i -X GET -w "\n" http://localhost:8080/rest/items/scope
```

Injection issues:
* [You cannot inject bean with `request` scope into a singleton](#injection-of-bean-with-request-scope-into-a-singleton)
* [Accessing request-related data in the component, being injected into singleton bean](#accessing-request-related-data-in-the-component-being-injected-into-singleton-bean)
* [Multiple implementations of the injectable interface](#multiple-implementations-of-the-injectable-interface)

### Injection of bean with `request` scope into a singleton

Spring boot will not be loaded successfully. You will get the following exception:

```text
ScopeNotActiveException: Error creating bean with name 'request': 
Scope 'request' is not active for the current thread; 
```

Note:
As a consequence, if you inject beans with `request` scope into rest controllers, 
you must explicitly mark those controllers with: `@Scope("request")`

### Accessing request-related data in the component, being injected into singleton bean

If you work in the web context, you can get it done via `RequestAttribute` interface.
You must define a bean without `request`, and get `HttpServletRequest` as:

```java
Optional.ofNullable(RequestContextHolder.getRequestAttributes())
  .filter(a -> a instanceof ServletRequestAttributes)
  .map(a -> (ServletRequestAttributes) a)
  .map(ServletRequestAttributes::getRequest)
  .map(httpServletRequest -> "PathInfo from http request: `" + httpServletRequest.getPathInfo() + "`")
  .orElse("No http request data");
```

See [`SingetonRequestAttributes`](src/main/java/com/example/scopes/service/SingetonRequestAttributes.java)

If the thread was instantiated outside web request, 
it does not have the required attributes in the threadlocal variables 
and hence it throws the exception.

The main reason spring does not expose the Request Scope outside web request is that 
there is no definite way to determine when the request is started and when it is completed. 
Because for e.g. same thread can be reused across multiple request processing. 
And all thread local request scoped variables needs to be cleared before another request starts.

### Multiple implementations of the injectable interface

Example:

You define [`ServiceApi`](src/main/java/com/example/scopes/api/ServiceApi.java)

You inject it as:

```java
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
private ServiceApi api;
```

You have 2 implementation:
[`RequestServiceApi`](src/main/java/com/example/scopes/service/RequestServiceApi.java)
And one provided via congiguration as:
```java
@Configuration
@ComponentScan("com.example")
public class AppConfig {

  @Bean 
  public ServiceApi serviceApi() {
    ...
  }
}
```
See [`AppConfig#serviceApi`](src/main/java/com/example/scopes/AppConfig.java)

The bean provided by configuration has a higher priority and will be injected.