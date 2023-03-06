def build() {
    echo "building the application..."
    sh 'npm install' 
} 

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t mojoe277/nodejs-app:njr-1.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push mojoe277/nodejs-app:njr-1.0'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this
