#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        nodejs 'my-nodejs'
    }
    environment {
        IMAGE_NAME = 'mojoe277/nodejs-app:njr-1.0'
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
                        sh 'docker build -t ${IMAGE_NAME} .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push ${IMAGE_NAME}'
    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo "deploying docker image to EC2..."
                    def shellCmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                    sshagent(['ec2-server-key']) {
                        sh "scp server-cmds.sh ec2-user@52.58.160.9:/home/ec2-user "
                        sh "docker-compose.yaml ec2-user@52.58.160.9:/home/ec2-user "
                       sh "ssh -o StrictHostKeyChecking=no ec2-user@52.58.160.9 ${dockerCmd}"
                    }  
                }
            }
        }
    }
}
