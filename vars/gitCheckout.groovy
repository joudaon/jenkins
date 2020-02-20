#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Clone the selected git repository.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        gitCheckout ("htpp://myrepo.git", "integration", 10)
 *    }
 *
 * @param giturl (String) git url of the git project.
 * @param branch (String) branch that is going to be checked out.
 * @param shallowclonedepth (Integer) number of latest commits that will be downloaded.
 *        0 - all commits will be downloaded (no shallow clone).
 *        > 0 - 'n' commits will be downloaded (with shallow clone).
 */
def call(String giturl = 'htpp://myrepo.git', String branch = 'integration', Integer shallowclonedepth = 0) {
    def logger = new Logger()
    if ( shallowclonedepth == 0 ) {
        logger.info("Checking out ${giturl} ('${branch}' branch) repository without Shallow clone")
        checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [], 
            submoduleCfg: [], 
            userRemoteConfigs: [[credentialsId: 'gittfscredentials', 
                                url: "${giturl}"]]])
    } else {
        logger.info("Checking out ${giturl} ('${branch}' branch) repository with Shallow clone = ${shallowclonedepth}")
        checkout([$class: 'GitSCM', branches: [[name: "${branch}"]], 
            doGenerateSubmoduleConfigurations: false, 
            extensions: [[$class: 'CloneOption', depth: "${shallowclonedepth}", noTags: false, reference: '', shallow: true]], 
            submoduleCfg: [], 
            userRemoteConfigs: [[credentialsId: 'gittfscredentials', 
                                url: "${giturl}"]]])
    }
    
}
