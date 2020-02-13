#!/usr/bin/groovy

package com.mycompany;

def info(message) {
    echo "INFO: ${message}"
}

def warning(message) {
    echo "WARNING: ${message}"
}

def error(message) {
    echo "ERROR: ${message}"
}