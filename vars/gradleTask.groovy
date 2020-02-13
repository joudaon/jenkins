#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Runs gradle tasks on the selected project.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        gradleTask ('mypath', 'build shadowJar')
 *    }
 *
 * @param gradlefilepath (String) path were build.gradle file is.
 * @param gradletasks (String) tasks to be run. If more than one use empty space
          "build check test shadowJar"
 */
def call(String gradlefilepath = '.', String gradletasks = 'build') {
    def logger = new Logger()
    logger.info("Executing the following tasks: '${gradletasks}' on ${gradlefilepath}/build.gradle file")
    try {
        sh """
        #!/bin/bash
        cd ${gradlefilepath}
        gradle --version
        gradle ${gradletasks}
        """
    }
    catch (err) {
        echo "The gradle task was not found or gradle tasks failed."
        error('Aborting because the gradle task was not found or gradle tasks failed.')
    }

}
