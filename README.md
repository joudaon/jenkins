# Jenkins Jobs

These templates are imported as a DSL job in Jenkins and then new jobs are created from these templates.

## TOC

- [Jenkins Jobs](#jenkins-jobs)
  - [TOC](#toc)
  - [Jenkins Job DSL API](#jenkins-job-dsl-api)
  - [Summary](#summary)
  - [Updating Credentials](#updating-credentials)
  - [Plugins](#plugins)
    - [Active Choices (Official Site)](#active-choices-official-site)
    - [AnsiColor (Official Site)](#ansicolor-official-site)
    - [CloudBees Docker Build and Publish (Official Site)](#cloudbees-docker-build-and-publish-official-site)
    - [Docker Plugin (Official Site)](#docker-plugin-official-site)
    - [Email Extension (Official Site)](#email-extension-official-site)
    - [Environment Injector (EnvInject) (Official Site)](#environment-injector-envinject-official-site)
    - [Git Parameter (Official Site)](#git-parameter-official-site)
    - [InfluxDB (Official Site)](#influxdb-official-site)
    - [JaCoCo (Official Site)](#jacoco-official-site)
    - [Jenkins user build vars (Official Site)](#jenkins-user-build-vars-official-site)
    - [Job Configuration History (Official Site)](#job-configuration-history-official-site)
    - [Job DSL (Official Site)](#job-dsl-official-site)
    - [JUnit (Official Site)](#junit-official-site)
    - [Locale (Official Site)](#locale-official-site)
    - [MSBuild (Official Site)](#msbuild-official-site)
    - [MSTest (Official Site)](#mstest-official-site)
    - [MSTestRunner (Official Site)](#mstestrunner-official-site)
    - [Test Results Analyzer (Official Site)](#test-results-analyzer-official-site)
    - [Parameterized Scheduler plugin (Official Site)](#parameterized-scheduler-plugin-official-site)
    - [Pipeline Utility Steps(Official Site)](#pipeline-utility-stepsofficial-site)
    - [PostBuildScript (Official Site)](#postbuildscript-official-site)
    - [PowerShell (Official Site)](#powershell-official-site)
    - [Prometheus metrics (Official Site)](#prometheus-metrics-official-site)
    - [Warnings Next Generation (Official Site)](#warnings-next-generation-official-site)
    - [Sidebar Link (Official Site)](#sidebar-link-official-site)
    - [Simple Theme (Official Site)](#simple-theme-official-site)
    - [Slack Notification (Official Site)](#slack-notification-official-site)
    - [SonarQube Scanner (Official Site)](#sonarqube-scanner-official-site)
    - [/userContent in Git (Official Site)](#usercontent-in-git-official-site)
    - [xUnit (Official Site)](#xunit-official-site)
  - [Delete Jenkins workspace @tmp files using crontab and scheduler](#delete-jenkins-workspace-tmp-files-using-crontab-and-scheduler)
    - [Linux](#linux)
    - [Windows](#windows)
  - [Custom scripts:](#custom-scripts)
    - [(Linux) Display all characters between 'Version' and ']' characters](#linux-display-all-characters-between-version-and--characters)
    - [(Powershell) Display version number from a file](#powershell-display-version-number-from-a-file)
    - [(Linux) Date format](#linux-date-format)
    - [(Windows) Date format](#windows-date-format)
  - [Additional links:](#additional-links)
  - [Shared libraries links](#shared-libraries-links)

## Jenkins Job DSL API

The job-dsl-plugin allows the programmatic creation of projects using a DSL. Pushing job creation into a script allows you to automate and standardize your Jenkins installation, unlike anything possible before.

## Summary

Jenkins is a wonderful system for managing builds, and people love using its UI to configure jobs. Unfortunately, as the number of jobs grows, maintaining them becomes tedious, and the paradigm of using a UI falls apart. Additionally, the common pattern in this situation is to copy jobs to create new ones, these "children" have a habit of diverging from their original "template" and consequently it becomes difficult to maintain consistency between these jobs.

The Jenkins job-dsl-plugin attempts to solve this problem by allowing jobs to be defined with the absolute minimum necessary in a programmatic form, with the help of templates that are synced with the generated jobs. The goal is for your project to be able to define all the jobs they want to be related to their project, declaring their intent for the jobs, leaving the common stuff up to a template that were defined earlier or hidden behind the DSL.

More info at: [Jenkins Job DSL API](https://jenkinsci.github.io/job-dsl-plugin/) and [Job DSL Plugin](https://wiki.jenkins.io/display/JENKINS/Job+DSL+Plugin).

## Updating Credentials

In some cases we will find **yourcredentials** inside our Groovy files. These credentials need to be updated with a new ones. For that we will go to Jenkins.

* Go to credentials
* Click **Jenkins** (under 'Stores scoped to Jenkins')
* Click **Global credentials (unrestricted)** (under 'System')
* On the left side click on **Add Credentials**

Once our credentials are created a new ID will be generated. This **ID** should be replaced on **yourcredentials**.

## Plugins

### Active Choices ([Official Site](https://plugins.jenkins.io/uno-choice))
The Active Choices plugin allows the creation of dynamic and interactive parameters for freestyle Jenkins jobs. Active Choice parameters can be dynamically updated and can be rendered as combo-boxes, check-boxes, radio-buttons or rich HTML.

### AnsiColor ([Official Site](https://plugins.jenkins.io/ansicolor))
This plugin adds support for ANSI escape sequences, including color, to Console Output

### CloudBees Docker Build and Publish ([Official Site](https://plugins.jenkins.io/docker-build-publish))
This plugin provides the ability to build projects with a Dockerfile, and publish the resultant tagged image (repo) to the docker registry.

### Docker Plugin ([Official Site](https://plugins.jenkins.io/docker-plugin))
This plugin allows slaves to be dynamically provisioned using Docker.

### Email Extension ([Official Site](https://plugins.jenkins.io/email-ext))
This plugin allows you to configure every aspect of email notifications. You can customize when an email is sent, who should receive it, and what the email says.

### Environment Injector (EnvInject) ([Official Site](https://plugins.jenkins.io/envinject))
EnvInject plugin provides the following features:

- Removes inherited environment variables by the Jenkins Java process
- Injects environment variables at node (master/slave) startup
- Executes a setup script before or/and after a SCM checkout for a run
- Injects environment variables before or/and after a SCM checkout for a run
- Injects environment variables as a build step for a run
- Injects password values for a run
- Exports environment variables at the end of the build in order to to know the set of environment variables used for each build

- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.environmentVariables)

### Git Parameter ([Official Site](https://plugins.jenkins.io/git-parameter))
Adds ability to choose branches, tags or revisions from git repository configured in project.
- [DSL information](http://localhost:8080/plugin/job-dsl/api-viewer/index.html#method/javaposse.jobdsl.dsl.helpers.BuildParametersContext.gitParameter)
- [More information](https://issues.jenkins-ci.org/browse/JENKINS-49438)

### InfluxDB ([Official Site](https://plugins.jenkins.io/influxdb))
Sends Jenkins build metrics into InfluxDB.

- [DSL information](http://localhost:8080/plugin/job-dsl/api-viewer/index.html#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.influxDbPublisher)

### JaCoCo ([Official Site](https://plugins.jenkins.io/jacoco))
This plugin allows you to capture code coverage report from JaCoCo. Jenkins will generate the trend report of coverage and some other statistics.
It also includes functionality to include columns in Dashboards which displays the latest overall coverage numbers and links to the coverage report.

### Jenkins user build vars ([Official Site](https://plugins.jenkins.io/build-user-vars-plugin))
This plugin provides a set of environment variables that describe the user who started the build.

### Job Configuration History ([Official Site](https://plugins.jenkins.io/jobConfigHistory))
Saves copies of all job and system configurations.

### Job DSL ([Official Site](https://plugins.jenkins.io/job-dsl))
The job-dsl-plugin allows the programmatic creation of projects using a DSL. Pushing job creation into a script allows you to automate and standardize your Jenkins installation, unlike anything possible before.

### JUnit ([Official Site](https://plugins.jenkins.io/junit))
Allows JUnit-format test results to be published.

- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveJunit)

### Locale ([Official Site](https://plugins.jenkins.io/locale))
This plugin controls the language of JenkinsNormally, Jenkins honors the browser's language preference if a translation is available for the preferred language, and uses the system default locale for messages during a build. 

### MSBuild ([Official Site](https://plugins.jenkins.io/msbuild))
This plugin allows you to use MSBuild to build .NET projects.

- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.msBuild)

### MSTest ([Official Site](https://plugins.jenkins.io/mstest))
This plugin converts MSTest TRX test reports into JUnit XML reports so it can be integrated with Jenkin's JUnit features. This plugin converts the .coveragexml files found in the project workspace to the EMMA format.

### MSTestRunner [(Official Site)](https://plugins.jenkins.io/mstestrunner)
This plugin allow you to execute test using MSTest command line tool.

Furter information: https://www.automatetheplanet.com/configure-jenkins-mstest-execution/

### Test Results Analyzer ([Official Site](https://plugins.jenkins.io/test-results-analyzer))
A plugin that shows history of test execution results in a tabular format.

### Parameterized Scheduler plugin ([Official Site](https://plugins.jenkins.io/parameterized-scheduler))
This plugin is for configuring a cron style timer schedule for parameterized builds. 

Further information:
- https://issues.jenkins-ci.org/browse/JENKINS-42893

### Pipeline Utility Steps([Official Site](https://plugins.jenkins.io/pipeline-utility-steps))
Small, miscellaneous, cross platform utility steps for Pipeline Plugin jobs

### PostBuildScript ([Official Site](https://plugins.jenkins.io/postbuildscript))
This plugin allows you to run the following actions after a build:

- Batch or shell scripts
- Groovy scripts
- Build steps

You can configure these actions depending on the build status (i.e., only run when build is successful). Scripts can also be executed on the master, on slaves or both. On matrix projects, the build can be executed on each axis.

Please refer to the plugin description for further information.

### PowerShell ([Official Site](https://plugins.jenkins.io/powershell))
Provides Jenkins integration with Windows PowerShell
- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.powerShell)

### Prometheus metrics ([Official Site](https://plugins.jenkins.io/prometheus))
Exposes a Prometheus metrics endpoint.

### Warnings Next Generation ([Official Site](https://plugins.jenkins.io/warnings-ng))
This plugin collects compiler warnings or issues reported by static analysis tools and visualizes the results.
Jenkins' Warnings Next Generation Plugin collects compiler warnings or issues reported by static analysis tools and visualizes the results. It has built-in support for almost hundred static analysis tools (including several compilers), see the list of supported report formats. It replaces the Jenkins Static Analysis Suite that has been part of the Jenkins plugin eco-system for more than ten years now. I.e. it makes the following Jenkins plugins obsolete: Android Lint, CheckStyle, Dry, FindBugs, PMD, Warnings, Static Analysis Utilities, Static Analysis Collector Plugins, Task Scanner, etc. 
- [DSL information](http://localhost:8080/plugin/job-dsl/api-viewer/index.html#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.recordIssues)
- [More information](https://issues.jenkins-ci.org/browse/JENKINS-55379)

### Sidebar Link ([Official Site](https://plugins.jenkins.io/sidebar-link))
Add links in the sidebar of the Jenkins main page, view tabs and project pages.This simple plugin adds an Additional Sidebar Links section in the main Jenkins configuration page, with settings for link URLs, texts and icons. These links will be shown in the top-level Jenkins pages (main page, user list, build history, My Projects and other project view tabs). Sidebar links for particular jobs may also be added in the job configuration pages.

- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.properties.PropertiesContext.sidebarLinks)

### Simple Theme ([Official Site](https://plugins.jenkins.io/simple-theme-plugin))
This plugin allows to customize Jenkin's appearance with custom CSS and JavaScript. It also allows to replace the Favicon. 

### Slack Notification ([Official Site](https://plugins.jenkins.io/slack))
Provides Jenkins notification integration with Slack or Slack compatible applications like RocketChat and Mattermost.

### SonarQube Scanner ([Official Site](https://plugins.jenkins.io/sonar))
This plugin allow easy integration of SonarQube™, the open source platform for Continuous Inspection of code quality.

- [Information](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Jenkins)

### /userContent in Git ([Official Site](https://plugins.jenkins.io/git-userContent))
Jenkins has a mechanism known as "User Content", where administrators can place files inside $JENKINS_HOME/userContent, and these files are served from http://yourhost/jenkins/userContent. This can be thought of as a mini HTTP server to serve images, stylesheets, and other static resources that you can use from various description fields inside Jenkins.

This plugin exposes this this $JENKINS_HOME/userContent directory as a Git repository, allowing administrators to use git to push/pull changes and manage them with history.

Once this plugin is installed, see http://yourserver/jenkins/userContent.git in your browser for how to access this repository.

### xUnit ([Official Site](https://plugins.jenkins.io/xunit))
This plugin makes it possible to publish the test results of an execution of a testing tool in Jenkins. 
- [DSL information](https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveXUnit)

## Delete Jenkins workspace @tmp files using crontab and scheduler

### Linux

To edit or create your own crontab file (for specific user), type the following command at the UNIX / Linux shell prompt:

    $ crontab -e

Then type in the file:

    # Delete Jenkins @tmp folders in /var/lib/jenkins/workspace. From mon-fri at 09:00
    0 9 * * 1-5 rm -rf /var/lib/jenkins/workspace/*@tmp

After saving a new file (username) will be created in: /var/spool/cron/crontabs/

To check if the cron has been run we perform the following command:

    $> tailf -n 100 /var/log/syslog | grep CRON

### Windows

Open "Task Scheduler" and add the "delete_jenkins" task.

We will start Powershell program with arguments:

    Program: C:\Windows\System32\WindowsPowerShell\v1.0\powershell.exe
    Arguments: Remove-Item -Path C:\Jenkins\workspace\* -Filter *@tmp

We add the desired trigger, for example everyday at 09:00 am.

## Custom scripts:

### (Linux) Display all characters between 'Version' and ']' characters
    version=$(sed -n '1p' changelog.txt | sed 's/.*Version \(.*\)].*/\1/')
    # ------------------------------- #
    Example:
    changelog.txt
    [Version 1.0.0] 2018/01/01
    Output: 1.0.0

### (Powershell) Display version number from a file

    # --- Getting service version --- #
    $file = "$env:WORKSPACE/changelog.txt"
    $version=Get-Content $file -TotalCount 1
    $version -Match '(?s)\[.*?]+' | Out-Null
    $version1 = $Matches.Values
    $version2=$version1.Remove(0,9)
    $finalversion=$version2 -replace ".{1}$"
    echo $finalversion
    # ------------------------------- #
    Example:
    changelog.txt
    [Version 1.0.0]
    Output: 1.0.0

### (Linux) Date format

    echo Date: $(date +"%Y/%m/%d at %T")

### (Windows) Date format

    Get-Date -UFormat "%Y/%m/%d at %H:%M:%S"

## Additional links:
- [Install Powershell 5 in Windows Server 2008 R2](https://www.rootusers.com/install-powershell-5-windows-server-2008-r2/)
- [Download WMF 5.0 (Win8.1AndW2K12R2-KB3134758-x64.msu)](https://www.microsoft.com/en-ie/download/details.aspx?id=50395)
- [Script is not digitally signed](http://tritoneco.com/2014/02/21/fix-for-powershell-script-not-digitally-signed/)
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
- [SparseCheckoutpaths](https://stackoverflow.com/questions/25943651/sparse-checkout-with-jenkins-dsl-plugin)
- [Crontab generator](https://crontab-generator.org/)
- [Installing Hyper-V on WindowsServer on VMWware (Hyper-V cannot be installed. A hypervisor is already running)](https://blogs.technet.microsoft.com/gbanin/2013/06/25/how-to-install-hyper-v-on-a-virtual-machine-in-hyper-v/)
- [Install OpenSSH](https://www.server-world.info/en/note?os=Windows_Server_2016&p=openssh)
- [How to Enable Support for Nested 64bit & Hyper-V VMs in vSphere 5](https://www.virtuallyghetto.com/2011/07/how-to-enable-support-for-nested-64bit.html)
- [Nested ESXi Enhancements in vSphere 6.5](https://www.virtuallyghetto.com/2016/10/nested-esxi-enhancements-in-vsphere-6-5.html)
- [Error 1067- on start OpenSSH by net start opensshd in windows cmd - ESTA OPCION ME FUNCIONA (hago ssh por cmd)](https://stackoverflow.com/questions/39319140/error-1067-on-start-openssh-by-net-start-opensshd-in-windows-cmd)
- [stop and delete docker container if its running without errors](https://stackoverflow.com/questions/34228864/stop-and-delete-docker-container-if-its-running)
- [Configure Jenkins MSTest Tests Execution](https://www.automatetheplanet.com/configure-jenkins-mstest-execution/)
- [How do I display all the characters between two specific strings?](https://unix.stackexchange.com/questions/273496/how-do-i-display-all-the-characters-between-two-specific-strings)
- [How-to-pass-variables-from-jenkinsfile-to-shell-command](https://stackoverflow.com/questions/41539076/how-to-pass-variables-from-jenkinsfile-to-shell-command)
- [Using Email-ext plugin in Jenkins pipeline](http://ikeptwalking.com/using-email-ext-plugin-in-jenkins-pipeline/)
- [Github - email-ext-plugin templates](https://github.com/jenkinsci/email-ext-plugin/tree/master/src/main/resources/hudson/plugins/emailext/templates)
- [Publish pretty cucumber reports on Jenkins](https://github.com/jenkinsci/cucumber-reports-plugin)
- [Pattern to get string between two specific words/characters using grep [duplicate]](https://stackoverflow.com/questions/21077882/pattern-to-get-string-between-two-specific-words-characters-using-grep)
- [Where is the cron / crontab log?](https://askubuntu.com/questions/56683/where-is-the-cron-crontab-log)
- [Verify if crontab works](https://askubuntu.com/questions/85558/verify-if-crontab-works)
- [How to redirect output to a file from within cron?](https://unix.stackexchange.com/questions/52330/how-to-redirect-output-to-a-file-from-within-cron)
- [how to run cron jobs as a specific user other than root in linux](http://www.lostsaloon.com/technology/how-to-run-cron-jobs-as-a-specific-user/)
- [Gray filled box icon](https://www.iconsdb.com/gray-icons/filled-box-icon.html)
- [Error: Error applying plan: 1 error(s) occurred: * vsphere_virtual_machine.vm_3: cannot locate virtual machine or template with UUID "423908f8-54b3-432c-5f2b-ff6b8aee5a32": ServerFaultCode: The session is not authenticated](https://github.com/terraform-providers/terraform-provider-vsphere/issues/664)
- [How do I display all the characters between two specific strings?](https://unix.stackexchange.com/questions/273496/how-do-i-display-all-the-characters-between-two-specific-strings)
- [Jenkins Pipeline](https://santoshbandage.wordpress.com/2017/06/12/why-pipeline/)
- [How to check if a string contains a substring in Bash](https://stackoverflow.com/questions/229551/how-to-check-if-a-string-contains-a-substring-in-bash)
- [How to determine if a string is a substring of another in bash?](https://askubuntu.com/questions/299710/how-to-determine-if-a-string-is-a-substring-of-another-in-bash)
- [<string> contains <substring> in bash](https://timmurphy.org/2013/05/13/string-contains-substring-in-bash/)
- [Git asks for username every time I push](https://stackoverflow.com/questions/11403407/git-asks-for-username-every-time-i-push)
- [git-credential-store (1) - Linux Man Pages](https://www.systutorials.com/docs/linux/man/1-git-credential-store/)
- [How to configure automatic password handling for git commands:](https://superuser.com/questions/812931/how-to-configure-automatic-password-handling-for-git-commands)
- [How to setup Git Credential store in Windows](https://agilewarrior.wordpress.com/2017/09/25/how-to-setup-git-credential-store-in-windows/)
- [How to set current build result in Pipeline?](https://support.cloudbees.com/hc/en-us/articles/218554077-How-to-set-current-build-result-in-Pipeline-)
- [Compiling .Net project - NET Framework Developer Pack](https://www.microsoft.com/en-us/download/details.aspx?id=56119)
- [Jenkins Master and Slaves as Docker Containers](https://rafaelnexus.com/tutorials/jenkins-master-and-slaves-as-docker-containers/)
- [How To Setup Docker Containers As Build Slaves For Jenkins](https://devopscube.com/docker-containers-as-build-slaves-jenkins/])
- [Setup Jenkins Master And Build Slaves As Docker Container](https://devopscube.com/jenkins-master-build-slaves-docker-container/)
- [Monitoring Jenkins with Grafana and Prometheus:](https://medium.com/@eng.mohamed.m.saeed/monitoring-jenkins-with-grafana-and-prometheus-a7e037cbb376)
- [Delivery Pipelines, with Jenkins 2, SonarQube, and Artifactory](https://jenkins.io/blog/2017/04/18/continuousdelivery-devops-sonarqube/)
- [Analyzing with SonarQube Scanner for Jenkins](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner+for+Jenkins)
- [Changing Jenkins build number](https://stackoverflow.com/questions/19645430/changing-jenkins-build-number)
- [Jenkins pipeline plugin: set the build description](https://stackoverflow.com/questions/36501203/jenkins-pipeline-plugin-set-the-build-description)
- [HTML in job descriptions on jenkins](https://www.edureka.co/community/13612/html-in-job-descriptions-on-jenkins)

## Shared libraries links
- [Jenkins DevOps Shared Libraries](https://github.com/mschuchard/jenkins-devops-libs)
- [jenkins-shared-library-framework](https://github.com/devopscube/jenkins-shared-library-framework)
- [Project Piper Repository](https://github.com/SAP/jenkins-library)
- [CFPB Jenkins Pipeline Shared Libraries](https://github.com/cfpb/jenkins-shared-libraries)
- [jenkins-pipeline-shared-lib](https://github.com/BCDevOps/jenkins-pipeline-shared-lib)
- [Jenkins Pipeline Shared Library Sample](https://github.com/AndreyVMarkelov/jenkins-pipeline-shared-lib-sample)
- [Jenkins Pipeline Shared Library Gradle Plugin](https://github.com/mkobit/jenkins-pipeline-shared-library-example)