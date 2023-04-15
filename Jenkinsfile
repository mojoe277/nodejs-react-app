#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        nodejs 'nodejs-19.9.0'
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
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins-aws-key')
                AWS_SECRET_ACCESS_KEY_ID = credentials('jenkins-aws-key')
            }
            steps {
                script {
                    echo "deploying docker image..."
                    sh 'kubectl create deployment nginx-deployment --image=nginx'
                }
            }
        }
    }
}
