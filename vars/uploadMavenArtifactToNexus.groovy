#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Uploads a Maven artifact to Nexus Maven Repository.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        uploadMavenArtifactToNexus ('maven-dev', '', 'path/to/artifact.jar', 'groupid', 'artifact', 'nightly')
 *    }
 *
 * @param repository (String) repository where artifacts are going to be uploaded (maven-dev, maven-pre).
 * @param tag (String) 
 * @param filepath (String) full path of the file to be uploaded.
 * @param groupid (String) 
 * @param artifact (String)
 * @param verstion (String)
 */
def call(String repository = 'maven-dev', String tag = '', String filepath = 'path/to/artifact.jar', String groupid = 'groupid', String artifact = 'artifact', String version = 'nightly') {
    def logger = new Logger()
    def packaging = filepath.tokenize('.')[-1].toLowerCase()
    def file = filepath.tokenize('/')[-1]
    logger.info("Uploading '${file}' Maven artifact to Nexus artifactory ('${repository}/${groupid}/${artifact}').")
    nexusPublisher nexusInstanceId: 'SonatypeNexusRepositoryManagerOSS3', nexusRepositoryId: "${repository}", packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${filepath}"]], mavenCoordinate: [artifactId: "${artifact}", groupId: "${groupid}", packaging: "${packaging}", version: "${version}"]]], tagName: "${tag}"
}
