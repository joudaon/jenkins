#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Builds a Docker Image and uploads it to a Docker Registry.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        dockerBuildAndPushImage ('harbor', 'harborcredentials', 'base', 'test', '2.4', 'path/to/dockerfile')
 *    }
 *
 * @param registry (String) Docker Registry to upload the image.
 * @param credentialsid (String) credentials of Docker Registry (in Jenkins).
 * @param registryproject (String) project inside Docker Registry to which image will be uploaded.
 * @param servicename (String) name of the service that will be uploaded.
 * @param version (String) version of the service that will be uploaded.
 * @param dockerfiledirectorypath (String) directory path containing a Dockerfile.
 */
def call(String registry = 'harbor', String credentialsid = '', String registryproject = 'base', String servicename = 'servicename', String version = '1.0.0', String dockerfiledirectorypath = '.') {
    def logger = new Logger()
    logger.info("Uploading '${servicename}:${version}' image to '${registry}/${registryproject}' Docker Registry from '${dockerfiledirectorypath}' context.")
    // If we want to login explicity on the fly.
    if ( credentialsid ) {
        docker.withRegistry("https://${registry}", "${credentialsid}") {
            docker.withTool('docker_local_installation') {
                docker_img = docker.build("${registry}/${registryproject}/${servicename}:${version}", "${dockerfiledirectorypath}")
                docker_img.push()
                sh "docker rmi ${docker_img.id}"
            }
        }
    // We do not login in Repository on the fly because we are already logged in the system.
    } else {
        docker.withRegistry("https://${registry}") {
            docker.withTool('docker_local_installation') {
                docker_img = docker.build("${registry}/${registryproject}/${servicename}:${version}", "${dockerfiledirectorypath}")
                docker_img.push()
                sh "docker rmi ${docker_img.id}"
            }
        }
    }

}
