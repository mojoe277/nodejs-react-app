#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        nodejs 'my-nodejs'
    }
    stages {
        stage('build') {
            steps {
                script {
                    echo "building the application..."
                    sh 'npm install' 
                }
            }
        }
        stage('build image') {
            steps {
                script {
                    echo "building the docker image..."
                }
            }
        }
        stage('deploy') {
            steps {
                withAWS(credentials: '<AWS_CREDENTIALS_ID>', region: '<AWS_REGION>')
                  script {
                    echo "deploying docker image..."
                    sh 'kubectl create deployment nginx-deployment --image=nginx'
                }
            }
        }
    }
}
