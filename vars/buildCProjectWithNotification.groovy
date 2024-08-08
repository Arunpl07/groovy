// vars/buildCProjectWithNotification.groovy
def call() {
    try {
        

        stage('Build') {
            // sh 'make clean'
            sh 'make'
        }

        stage('Test') {
            sh 'make test'
        }

        stage('Package') {
            sh 'make install'
        }

        notifySuccess() // Send success email
    } catch (Exception e) {
        notifyFailure(e) // Send failure email
        throw e
    }
}

def notifySuccess() {
    emailext(
        subject: "Build Successful: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: "Good news! The build was successful.",
        to: 'arunpl07@gmail.com'
    )
}

def notifyFailure(Exception e) {
    emailext(
        subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: "The build failed with the following error: ${e.getMessage()}",
        to: 'arunpl07@gmail.com'
    )
}
