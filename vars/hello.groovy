#!/usr/bin/env groovy

/**
 * Prints on console Hello and the name that is given as a parameter.
 *
 * Example usage within a declarative pipeline:
 *
 *    steps {
 *        hello('myname')
 *    }
 *
 * @param name (String) a name to be printed on console.
 */
def call (String name = 'world!') {
    echo 'Hello ' + name
}