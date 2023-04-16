#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        nodejs 'nodejs-19.9.0'
    }
    environment {
        IMAGE_NAME = 'njr-1.0'
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
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t mojoe277/nodejs-k8s:${IMAGE_NAME} .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push mojoe277/nodejs-k8s:${IMAGE_NAME}'
                    }
                }
            }
        }
        stage('deploy') {
            environment {
                AWS_ACCESS_KEY_ID = credentials('jenkins-aws-key')
                AWS_SECRET_ACCESS_KEY_ID = credentials('jenkins-aws-key')
                APP_NAME = 'k8s-nodejs-app'
            }
            steps {
                script {
                    echo "deploying docker image..."
                    sh 'envsubst < kubernetes/deployment.yaml | kubectl apply -f -'
                    sh 'envsubst < kubernetes/service.yaml | kubectl apply -f -'
                }
            }
        }
    }
}
