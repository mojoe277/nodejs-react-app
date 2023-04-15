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
                AWS_ACCESS_KEY_ID = credentials('jenkins-aws_access_key_id')
                AWS_SECRET_ACCESS_KEY_ID = credentials('jenkins-aws_secret_access_key')
            }
            steps {
                script {
                    echo "deploying docker image..."
                    withKubeConfig([credentialsId: 'kubernetes-config']) {
                    sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.26.2/bin/linux/amd64/kubectl"'  
                    sh 'chmod u+x ./kubectl'  
                    sh './kubectl get pods'
                    sh 'kubectl create deployment nginx-deployment --image=nginx'
                }
            }
        }
    }
}
