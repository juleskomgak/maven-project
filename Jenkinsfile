pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                     echo 'Now Archiving ...'
                     archiveArtifacts artifacts: '**/target/*.war'
                }  

            }
        }
        stage(' Deploy To Staging ')  {
            steps {
                Build Job : 'deploy-to-staging'
            }

        }
       
    }
}