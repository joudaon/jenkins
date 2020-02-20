#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Uploads an artifact to Nexus repository.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        uploadArtifactToNexus ('jenkins-artifacts', 'path/to/artifact.zip', 'groupid', '', 'artifact', 'nightly-dev')
 *    }
 *
 * @param repository (String) repository where artifacts are going to be uploaded.
 * @param destfile (String) full path of the file to be uploaded.
 * @param groupid (String) 
 * @param classifier (String)
 * @param artifact (String)
 * @param verstion (String)
 */
def call(String repository = 'jenkins-artifacts', String destfile = 'path/to/artifact.zip', String groupid = 'groupid', String classifier = '', String artifact = 'artifact', String version = 'nightly-dev') {
    def logger = new Logger()
    def type = destfile.tokenize('.')[-1].toLowerCase()
    def file = destfile.tokenize('/')[-1]
    logger.info("Uploading '${file}' artifact to Nexus artifactory ('${repository}/${groupid}/${artifact}').")
    nexusArtifactUploader artifacts: [[artifactId: "${artifact}", classifier: "${classifier}", file: "${destfile}", type: "${type}"]], credentialsId: 'nexus3credentials', groupId: "${groupid}", nexusUrl: 'nexus.ode.local:8080', nexusVersion: 'nexus3', protocol: 'http', repository: "${repository}", version: "v${version}"
}
