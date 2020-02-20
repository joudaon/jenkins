#!/usr/bin/env groovy

import com.mycompany.Logger

/**
 * Restores NuGet packages for a given .NET project.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        restoreNugetPackages ('mypath', 'mysolution.sln')
 *    }
 *
 * @param slnpath (String) path were project.sln file is.
 * @param slnname (String) name of the solution.
 */
def call(String slnpath = '.', String slnname = 'myproject.sln') {
    def logger = new Logger()
    logger.info("Restoring NuGet packages in ${slnpath}/${slnname}")
    try {
        powershell """
        cd ${slnpath}
        C:/Jenkins/tools/NuGet.exe restore ${slnname}
        """
    }
    catch (err) {
        echo 'Nuget packages restoration failed.'
        error('Aborting because Nuget packages restoration failed.')
    }

}
