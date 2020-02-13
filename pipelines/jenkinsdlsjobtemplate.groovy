freeStyleJob('jobName') {

  /////////////////////////////////////
  ////////////// General //////////////
  /////////////////////////////////////
  description('jobDescription')

  // Enable project-based security - https://jenkinsci.github.io/job-dsl-plugin/#path/javaposse.jobdsl.dsl.jobs.FreeStyleJob.authorization
  authorization {
    permission('hudson.model.Item.Build:user')
    permission('hudson.model.Item.Configure:user')
    permission('hudson.model.Item.Read:user')
    permission('hudson.model.Item.Workspace:user')
  }

  // Discard old builds - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.jobs.FreeStyleJob.logRotator
  logRotator {
    numToKeep(10)
  }

  //This project is parametrized - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.jobs.FreeStyleJob.parameters
  parameters{
    choiceParam ('branch', ['develop', 'master'], 'Select the branch you want to checkout')
    stringParam ('string', 'defaultvalue', 'stringdescription')
  }

  // Restrict where this project can be run - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.jobs.FreeStyleJob.label
  label('nodename')

  /////////////////////////////////////
  ////// Source code management ///////
  /////////////////////////////////////
  //Git checkout - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.ScmContext.git
  scm {
    git {
      remote {
        url('gitrepourl')
        credentials('credentialsid')
      }
      branch ('*/\${branch}')
      // SparseCheckoutpaths - https://stackoverflow.com/questions/25943651/sparse-checkout-with-jenkins-dsl-plugin
      // configure { git ->
      //   git / 'extensions' / 'hudson.plugins.git.extensions.impl.SparseCheckoutPaths' / 'sparseCheckoutPaths' {
      //     ['/path1/', '/path2/'].each { mypath ->
      //       'hudson.plugins.git.extensions.impl.SparseCheckoutPath' {
      //           path("${mypath}")
      //       }
      //     }
      //   }
      // }
    }
  }

  /////////////////////////////////////
  /////////// Build triggers //////////
  /////////////////////////////////////
  // Build periodically - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.jobs.FreeStyleJob.triggers
  // Crontab generator: https://crontab-generator.org/
  triggers {
    cron('H 1 * * 1-5') // Everyday at 01:00 from M-F
  }

  /////////////////////////////////////
  ///////// Build Environment /////////
  /////////////////////////////////////
  // Build environment - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.jobs.FreeStyleJob.wrappers
  wrappers {
      buildUserVars() // Adds a number of environment variables with information about the current user. https://wiki.jenkins.io/display/JENKINS/Build+User+Vars+Plugin
  }

  /////////////////////////////////////
  /////////////// Build ///////////////
  /////////////////////////////////////
  steps {
    // Execute shell - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.shell
    shell('shell script')
    // Execute powershell - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.powerShell
    powerShell('powerShell script')
    // Execute Windows batch (cmd) - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.batchFile
    batchFile('windows cmd script')
    // Invoke Gralde script - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.gradle
    gradle {
      useWrapper(false)
      gradleName('Gradle_installed')
      tasks('check test jacocoTestReport shadowJar runnableTestJar')
      rootBuildScriptDir('/pathtobuildgradle/')
    }
    // Visual Studio Solution
    // Restore NuGet
    batchFile('''
    cd /pathtosln/
    "C:/Jenkins/tools/NuGet.exe" restore project.sln''')
    // Build a VS project or solution using MSBuild - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.msBuild
    msBuild {
      msBuildInstallation('MSBuildLocal15')
      buildFile('/pathtosln/project.sln')
      args('/p:Configuration=Bin/Release')
    }
    // Executing MSTest unit tests - https://automatetheplanet.com/configure-jenkins-mstest-execution/
    batchFile('''
    "C:\\Program Files (x86)\\Microsoft Visual Studio\\2017\\Professional\\Common7\\IDE\\MSTest.exe" /resultsfile:"Results.trx" /testcontainer:"pathtosolution\\bin\\Release\\unittests.dll" /nologo
    ''')
    // Docker build and publish in docker repositoryName - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.step.StepContext.dockerBuildAndPublish
    dockerBuildAndPublish {
      repositoryName('repositoryName')
      tag('imagetag')
      dockerRegistryURL('https://registryurl/')
      registryCredentials('yourcredentials')
      skipPush(false)
      noCache(false)
      forcePull(true)
      skipBuild(false)
      createFingerprints(true)
      skipDecorate(false)
      skipTagAsLatest(true)
      buildContext('/pathtodockerfile/')
      forceTag(false)
      additionalBuildArgs('')
      dockerfileDirectory('if name is not dockerfile fill this. For example: /pathtofile/Dockerfile_Docker')
    }
  // end build steps
  }

  /////////////////////////////////////
  //////// Post build actions /////////
  /////////////////////////////////////
  publishers {
    // Publish Checkstyle analysis results - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.checkstyle
    checkstyle('project/build/reports/checkstyle/*.xml')
    // Publish FindBus analysis results - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.findbugs
    findbugs('project/build/reports/findbugs/*.xml')
    // Publish PMD anaysis results - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.pmd
    pmd('project/build/reports/pmd/*.xml')
    // Scan workspace for open tasks - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.tasks
    tasks('**/*.java', '', 'FIXME', 'TODO', 'LOW', false)
    // Publish JUnit test result report - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveJunit
    archiveJunit('project/build/test-results/test/TEST-*.xml'){
      healthScaleFactor(0)
    }
    // Record JaCoCO coverage report - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.jacocoCodeCoverage
    jacocoCodeCoverage{
      execPattern('project/build/jacoco/**.exec')
      classPattern('project/build/classes')
      sourcePattern('project/src/main/java')
      inclusionPattern('')
      exclusionPattern('')
    }
    // Editable Email Notification - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.extendedEmail
    extendedEmail {
      recipientList('email1', 'email2')
      replyToList('\$DEFAULT_REPLYTO')
      defaultSubject('\$JOB_NAME - \$BUILD_STATUS')
      defaultContent('\${JELLY_SCRIPT,template="html"}<br/>Please find build url below for checking logs<br/>\$BUILD_URL<br/><br/>See attached "test.html" to see checkstyle warnings (more info at: \$BUILD_URL).')
      attachmentPatterns('/pathtofiletoattach/file.html')
      attachBuildLog(true)
      triggers {
        failure {
          sendTo {
            recipientList()
          }
        }
        unstable {
          sendTo {
            recipientList()
          }
        }
      }
    }
    // Publish xUnit test result report (Results from MSTest) - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveXUnit
    archiveXUnit {
      msTest {
        // If set, deletes temporary JUnit files.
        deleteOutputFiles(true)
        // If set, fails the build if the test results have not been updated.
        failIfNotNew(false)
        // Specifies where to find test results.
        pattern("Results.trx")
        // If set, does not fail the build if no test results have been found.
        skipNoTestFiles(true)
      }
    }
    // Archive the artifacts - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.archiveArtifacts
    archiveArtifacts {
      pattern('*.log')
      onlyIfSuccessful(true)
    }
    // Delete workspace when build is done - https://jenkinsci.github.io/job-dsl-plugin/#method/javaposse.jobdsl.dsl.helpers.publisher.PublisherContext.wsCleanup
    wsCleanup()
  // end Post-build actions
  }

}

/////////////////////////////////////
////// Environment variables ////////
/////////////////////////////////////
// Shell -> ${variable}
// batch -> %variable%
// powerShell -> $env:variable

/////////////////////////////////////
/// build.gradle for code quality ///
/////////////////////////////////////
//// Plugins to be used in Jenkins (Source code quality)
// apply plugin: "findbugs"
// apply plugin: "jacoco"
// apply plugin: 'checkstyle'
// apply plugin: 'pmd'
//// Plugins to be used in jenkins configuration
// findbugs {
//     ignoreFailures = true
//     toolVersion = "3.0.1"
//     sourceSets = [sourceSets.main]
//     reportsDir = file("$project.buildDir/reports/findbugs")
//     effort = "max"
// }
//
// jacoco {
//     toolVersion = '0.7.9'
// }
// jacocoTestReport {
//     reports {
//         html.enabled = true
//         xml.enabled = true
//         csv.enabled = true
//     }
// }
//
// checkstyle {
//     toolVersion = '8.1'
//     configFile = '/pathto/checkstyle.xml' as File
// }
//
// pmd {
//     toolVersion = '5.8.1'
//     ignoreFailures = true
//     ruleSetFiles = files("/pathto/ruleset.xml")
// }
//// End Jenkins Plugins configuration

////////////////////////////////////
/// Gitlab webhook configuration ///
////////////////////////////////////
// https://docs.bitnami.com/aws/how-to/create-ci-pipeline/
