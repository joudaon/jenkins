#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Gets service version from a changelog file and creates a 'service.properties' file with 
 * service name and version information.
 *
 * To read the first line of the changelog. This line MUST follow this format:
 * [Version 1.0.0]
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        getServiceVersion ('mypath', 'mychangelog', 'myservice')
 *    }
 *
 * @param changelogpath (String) location of changelog file.
 * @param changelogfilename (String) name of changelog file.
 * @param servicename (String) name of the service we are working on (lowercase).
 */
def call(String changelogpath = '.', String changelogfilename = 'changelog.txt', String servicename = 'servicename') {
    def logger = new Logger()
    logger.info("Reading log file: ${changelogpath}/${changelogfilename} ")
    try {
        if ( isUnix() ) {
            sh """
            echo "<-- Displaying changelog file... -->"
            cd ${changelogpath}
            cat ${changelogfilename}
            echo "<-- End displaying changelog file -->"
            version=\$(sed -n '1p' ${changelogfilename} | sed 's/.*Version \\(.*\\)].*/\\1/')
            cd ${WORKSPACE}
            rm -rf service.properties
            echo "servicename=${servicename}" | tee -a service.properties
            echo "serviceversion=\$version" | tee -a service.properties
            echo "<-- The version of the ${servicename} service is: \$version -->"
            """
        } else {
            powershell """
            cd ${changelogpath}
            if (Test-Path  ${changelogfilename}) {
                echo "<-- Displaying changelog file... -->"
                cat ${changelogfilename}
                echo "<-- End displaying changelog file -->"
                \$version=Get-Content ${changelogfilename} -TotalCount 1
                \$version -Match '(?s)\\[.*?]+' | Out-Null
                \$version1 = \$Matches.Values
                \$version2 = \$version1.Remove(0,9)
                \$finalversion= \$version2 -replace ".{1}\$"
                echo "<<< Appending properties to service.properties file... >>>"
                \$propertiesfile = "service.properties"
                cd ${WORKSPACE}
                if (Test-Path \$propertiesfile )
                {
                    Remove-Item -Path \$propertiesfile -Force -Recurse
                }
                Add-Content -Path \$propertiesfile -Value "service=${servicename}" -Force
                Add-Content -Path \$propertiesfile -Value "serviceversion=\$finalversion" -Force
                echo "<< Displaying service.properties file>>"
                Get-Content ${WORKSPACE}/service.properties
                echo "<-- The version of the ${servicename} service is: \$finalversion -->"
            } else {
                Write-Error "ERROR: ${changelogfilename} file does not exist!!"
                exit 1
            }
            """
        }
    }
    catch (err) {
        echo "The changelog file was not found."
        error('Aborting because the changelog file was not found.')
    }

}
