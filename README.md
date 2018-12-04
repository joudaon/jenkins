# Jenkins

Templates are imported as a DSL job in Jenkins and then new jobs or views are created from these templates.

## TOC

- [Jenkins](#jenkins)
    - [TOC](#toc)
    - [Jenkins Job DSL API](#jenkins-job-dsl-api)
        - [Summary](#summary)
    - [Updating Credentials](#updating-credentials)
    - [Plugins](#plugins)
        - [JUnit (Official Site)](#junit-official-site)
        - [MSBuild (Official Site)](#msbuild-official-site)
        - [MSTestRunner (Official Site)](#mstestrunner-official-site)
    - [Useful links](#useful-links)

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

## Plugins

### JUnit [(Official Site)](https://plugins.jenkins.io/junit)
Allows JUnit-format test results to be published.

- [DSL configuration](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveJunit)

### MSBuild [(Official Site)](https://plugins.jenkins.io/msbuild)
This plugin allows you to use MSBuild to build .NET projects.

- [DSL configuration](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.msBuild)

### MSTestRunner [(Official Site)](https://plugins.jenkins.io/mstestrunner)
This plugin allow you to execute test using MSTest command line tool.

Furter information: https://www.automatetheplanet.com/configure-jenkins-mstest-execution/

## Useful links

- [Download WMF 5.0 (Win8.1AndW2K12R2-KB3134758-x64.msu)](https://www.microsoft.com/en-ie/download/details.aspx?id=50395)
- [Script is not digitally signed:](http://tritoneco.com/2014/02/21/fix-for-powershell-script-not-digitally-signed/)
- [How to copy a local file to remote machines](https://blogs.msdn.microsoft.com/luisdem/2016/08/31/powershell-how-to-copy-a-local-file-to-remote-machines/)
- [Create a PSCredential object](https://blogs.msdn.microsoft.com/koteshb/2010/02/12/powershell-how-to-create-a-pscredential-object/)
- [Creating a PS Credential from a Clear Text Password in Powershell](https://blogs.technet.microsoft.com/gary/2009/07/23/creating-a-ps-credential-from-a-clear-text-password-in-powershell/)
- [Working with Passwords, Secure Strings and Credentials in Windows PowerShell](https://social.technet.microsoft.com/wiki/contents/articles/4546.working-with-passwords-secure-strings-and-credentials-in-windows-powershell.aspx)
- [How to copy files to and from Nano Server](https://msdn.microsoft.com/en-us/library/mt708806%28v=vs.85%29.aspx?f=255&MSPPError=-2147217396)
- [The WinRM client cannot process the request. If the authentication scheme is different](http://technico.qnownow.com/the-winrm-client-cannot-process-the-request-if-the-authentication-scheme-is-different/)
- [Powershell version 5 Copy-Item -FromSession cannot be found](https://stackoverflow.com/questions/40840269/powershell-version-5-copy-item-fromsession-cannot-be-found)
- [Error with PowerShell command for copying file to remote server with credential](https://stackoverflow.com/questions/39224609/error-with-powershell-command-for-copying-file-to-remote-server-with-credential)
- [Bug: Copy-Item -FromSession Fails if Local Machine Doesn't Have the Drive Being Copied From](https://windowsserver.uservoice.com/forums/301869-powershell/suggestions/11306682-bug-copy-item-fromsession-fails-if-local-machine)
- [Ejecutar comandos remotos](https://docs.microsoft.com/es-es/powershell/scripting/core-powershell/running-remote-commands?view=powershell-5.1)
- [Use PowerShell Invoke-Command to run scripts on remote computers](https://4sysops.com/archives/use-powershell-invoke-command-to-run-scripts-on-remote-computers/)
- [Using the Remove-Item Cmdlet](https://technet.microsoft.com/en-us/library/ee176938.aspx?f=255&MSPPError=-2147217396)
- [Using the Stop-Service Cmdlet](https://technet.microsoft.com/en-us/library/ee177005.aspx?f=255&MSPPError=-2147217396)