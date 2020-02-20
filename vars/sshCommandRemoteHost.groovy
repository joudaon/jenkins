#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Executes the given command on a remote node.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        sshCommandRemoteHost ('remotehostcredentials', 'hostname', 'df -h')
 *        sshCommandRemoteHost ('remotehostcredentials', 'hostname', """
 *                               df -h
 *                               tree /home/administrator
 *                               echo "hoooooola"
 *                               """)
 *    }
 *
 * More info at: https://jenkins.io/blog/2019/02/06/ssh-steps-for-jenkins-pipeline/
 *               https://jenkins.io/doc/pipeline/steps/ssh-steps/
 *               https://plugins.jenkins.io/ssh-steps/
 *
 * @param credentials (String) Jenkins provided credentials for selected host.
 * @param hostname (String) hostname or ip of the remote host.
 * @param command (String) command that is going to be run on remote host.
 */
def call(String credentials = '', String hostname = '', String command = '') {
    def logger = new Logger()
    logger.info("Executing the given command [[${command}]] on '${hostname}' host.")
    withCredentials([usernamePassword(credentialsId: "${credentials}", passwordVariable: 'password', usernameVariable: 'userName')]) {
        def remote = [:]
        remote.name = "${hostname}"
        remote.host = "${hostname}"
        remote.user = userName
        remote.password = password
        remote.allowAnyHosts = true
        sshCommand remote: remote, command: "${command}"
    }
}
