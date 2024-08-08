// vars/buildCProject.groovy
def call() {
    stage('Build') {
        sh 'make clean'
        sh 'make'
    }

    stage('Test') {
        sh 'make test'
    }

    stage('Package') {
        sh 'make install'
    }
}
