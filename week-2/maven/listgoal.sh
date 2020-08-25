# mvn help:describe -Dcmd=<PHASENAME> list all goals bound to a specific phase
# and deploy is last phase so all previous phases will be executed and listed by defualt

# command
mvn help:describe -Dcmd=deploy

# output
# C:\Users\STARK\eclipse-workspace2\articlesender>mvn help:describe -Dcmd=deploy
# [INFO] Scanning for projects...
# [INFO]
# [INFO] ---------------------< com.shashank:articlesender >---------------------
# [INFO] Building articlesender 0.0.1-SNAPSHOT
# [INFO] --------------------------------[ jar ]---------------------------------
# [INFO]
# [INFO] --- maven-help-plugin:3.2.0:describe (default-cli) @ articlesender ---
# [INFO] 'deploy' is a phase corresponding to this plugin:
# org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy

# It is a part of the lifecycle for the POM packaging 'jar'. This lifecycle includes the following phases:
# * validate: Not defined
# * initialize: Not defined
# * generate-sources: Not defined
# * process-sources: Not defined
# * generate-resources: Not defined
# * process-resources: org.apache.maven.plugins:maven-resources-plugin:2.6:resources
# * compile: org.apache.maven.plugins:maven-compiler-plugin:3.1:compile
# * process-classes: Not defined
# * generate-test-sources: Not defined
# * process-test-sources: Not defined
# * generate-test-resources: Not defined
# * process-test-resources: org.apache.maven.plugins:maven-resources-plugin:2.6:testResources
# * test-compile: org.apache.maven.plugins:maven-compiler-plugin:3.1:testCompile
# * process-test-classes: Not defined
# * test: org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
# * prepare-package: Not defined
# * package: org.apache.maven.plugins:maven-jar-plugin:2.4:jar
# * pre-integration-test: Not defined
# * integration-test: Not defined
# * post-integration-test: Not defined
# * verify: Not defined
# * install: org.apache.maven.plugins:maven-install-plugin:2.4:install
# * deploy: org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy

# [INFO] ------------------------------------------------------------------------
# [INFO] BUILD SUCCESS
# [INFO] ------------------------------------------------------------------------
# [INFO] Total time:  1.221 s
# [INFO] Finished at: 2020-08-25T16:09:20+05:30
# [INFO] ------------------------------------------------------------------------


