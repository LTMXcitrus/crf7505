See Tutorial here: https://cloud.google.com/community/tutorials/kotlin-springboot-app-engine-java8

1) Install gloud sdk: https://cloud.google.com/sdk/docs/downloads-interactive

2) run gloud init and pick project (you must have access)

3) install Java app engine:
``$gcloud components install app-engine-java``

### To run app locally
- ``gcloud auth application-default login``
- ``mvn clean appengine:run``
 
### To deploy app to GAE
- ``mvn clean appengine:deploy`` to deploy app
