#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Clone the selected git repositories.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        gitCheckoutMultipleRepositories (["http://myrepo1.git": "branch1", "http://myrepo2.git": "branch2"])
 *    }
 *
 * @param repositories (Map) git url and branch of the git project to be downloaded.
 */
def call(Map repositories = ["http://myrepo1.git": "branch1", "http://myrepo2.git": "branch2"]) {
    def logger = new Logger()
    repositories.each{ repository, branch -> 
        def foldername = repository.tokenize('/')[-1].toLowerCase()
        logger.info("Clonning '${repository} - (${branch} branch)' repository.")
        println("Foldername: " + foldername)
        println("Repository: " + repository)
        println("Branch: " + branch)
        checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], 
                    doGenerateSubmoduleConfigurations: false, 
                    extensions: [
                        [$class: 'CloneOption', depth: 1, noTags: false, reference: '', shallow: true], 
                        [$class: 'RelativeTargetDirectory', relativeTargetDir: "${foldername}"]
                    ], 
                    submoduleCfg: [], 
                    userRemoteConfigs: [[credentialsId: 'gittfscredentials', 
                                    url: "${repository}"]]])
    }

}
