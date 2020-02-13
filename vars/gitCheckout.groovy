#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Clone the selected git repository.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        gitCheckout ('htpp://myrepo.git', '10')
 *    }
 *
 * @param giturl (String) git url of the git project.
 * @param shallowclonedepth (Integer) number of latest commits that will be downloaded.
 */
def call(String giturl = 'htpp://myrepo.git', Integer shallowclonedepth = '0') {
    def logger = new Logger()
    if ( shallowclonedepth == 0 ) {
        logger.info("Checking out ${giturl} repository without Shallow clone")
        checkout([$class: 'GitSCM', branches: [[name: "$params.branch"]], 
            doGenerateSubmoduleConfigurations: false,
            extensions: [], 
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: 'gittfscredentials',
                                url: "${giturl}"]]])
    } else {
        logger.info("Checking out ${giturl} repository with Shallow clone = ${shallowclonedepth}")
        checkout([$class: 'GitSCM', branches: [[name: "$params.branch"]], 
            doGenerateSubmoduleConfigurations: false,
            extensions: [[$class: 'CloneOption', depth: "${shallowclonedepth}", noTags: false, reference: '', shallow: true]],
            submoduleCfg: [],
            userRemoteConfigs: [[credentialsId: 'gittfscredentials',
                                url: "${giturl}"]]])
    }

}



