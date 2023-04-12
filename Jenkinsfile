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
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins-aws_access_key_id')
                AWS_SECRET_ACCESS_KEY_ID = credentials('jenkins-aws_secret_access_key')
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
