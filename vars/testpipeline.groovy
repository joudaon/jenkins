#!/usr/bin/env groovy

def call () {
    pipeline {
        agent any

        libraries {
            lib('jon@master')
        }

        stages {
            stage ('test') {
                steps {
                    hello('jon')
                }
            }
        }
    }
}
