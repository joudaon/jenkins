# Jenkins

Templates are imported as a DSL job in Jenkins and then new jobs or views are created from these templates.

## TOC

- [Jenkins Job DSL API](#jenkins-job-dsl-api)
- [Updating Credentials](#updating-credentials)

## Jenkins Job DSL API

The job-dsl-plugin allows the programmatic creation of projects using a DSL. Pushing job creation into a script allows you to automate and standardize your Jenkins installation, unlike anything possible before.

### Summary

Jenkins is a wonderful system for managing builds, and people love using its UI to configure jobs. Unfortunately, as the number of jobs grows, maintaining them becomes tedious, and the paradigm of using a UI falls apart. Additionally, the common pattern in this situation is to copy jobs to create new ones, these "children" have a habit of diverging from their original "template" and consequently it becomes difficult to maintain consistency between these jobs.
The Jenkins job-dsl-plugin attempts to solve this problem by allowing jobs to be defined with the absolute minimum necessary in a programmatic form, with the help of templates that are synced with the generated jobs. The goal is for your project to be able to define all the jobs they want to be related to their project, declaring their intent for the jobs, leaving the common stuff up to a template that were defined earlier or hidden behind the DSL.

More info at: [Jenkins Job DSL API](https://jenkinsci.github.io/job-dsl-plugin/) and [Job DSL Plugin](https://wiki.jenkins.io/display/JENKINS/Job+DSL+Plugin)

## Updating Credentials

In some cases we will find **credentialsid** inside our Groovy files. These credentials need to be updated with a new ones. For that we will go to Jenkins.

* Go to credentials
* Click **Jenkins** (under 'Stores scoped to Jenkins')
* Click **Global credentials (unrestricted)** (under 'System')
* On the left side click on **Add Credentials**

Once our credentials are created a new ID will be generated. This **ID** should be replaced on **credentialsid**.
