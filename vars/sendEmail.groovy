#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Sends an email with the defined state and to selected email account.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        sendEmail ('failed', 'myemail@myemail.com')
 *    }
 *
 * @param state (String) state of the build (success, unstable, failed).
 * @param emailaccount (String) email account to which the email will be sent.
 */
def call(String state = 'failed', String emailaccount = 'myemail@myemail.com') {
    def logger = new Logger()
    logger.info("Sending email to: ${emailaccount}")
    emailext (
        subject: "${env.JOB_NAME} (${env.BUILD_NUMBER}) ${state}",
        body: '''${SCRIPT, template="groovy-html.template"}''',
        replyTo: 'jenkins-noreply@myemail.com',
        to: emailaccount,
        attachLog: false
    )
}
