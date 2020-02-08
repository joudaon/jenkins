#!/usr/bin/env groovy

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